package com.dmforu.crawling.schedule

import com.dmforu.crawling.Parser
import com.dmforu.crawling.WebPageLoader
import com.dmforu.domain.schedule.legacy.LegacySchedule
import com.dmforu.domain.schedule.legacy.MonthSchedule
import com.dmforu.domain.schedule.legacy.YearSchedule
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneId

@Service
class LegacyScheduleParser : Parser<YearSchedule?> {
    override fun parse(): List<YearSchedule> {
        val yearSchedules: MutableList<YearSchedule> = ArrayList<YearSchedule>()

        val currentYear: Int = LocalDate.now(ZoneId.of(TIME_ZONE)).getYear()

        // 작년부터 내년의 일정을 가져온다.
        for (year in currentYear - 1..currentYear + 1) {
            val schedules: List<MonthSchedule> = fetchYearSchedule(year)
            yearSchedules.add(YearSchedule(year, schedules))
        }

        return yearSchedules
    }

    private fun fetchYearSchedule(year: Int): List<MonthSchedule> {
        val yearSchedules: MutableList<MonthSchedule> = ArrayList<MonthSchedule>()
        val document: Document = WebPageLoader.getHTML(DMU_SCHEDULER_URL + year)

        val monthTables = document.select(".yearSchdulWrap")

        for (monthTable in monthTables) {
            val monthSchedule: MonthSchedule = fetchMonthSchedule(monthTable)

            // 일정 정보가 업로드 되지 않는 달은 제외한다. (다음 년도 3월 이후의 정보)
            if (monthSchedule.scheduleEntries.isEmpty()) break

            yearSchedules.add(monthSchedule)
        }

        return yearSchedules
    }

    private fun fetchMonthSchedule(monthTable: Element): MonthSchedule {
        val monthEntries: MutableList<LegacySchedule> = ArrayList<LegacySchedule>()

        // <p id="yearmonth20241">2024.1</p> p태그에서 "2024.1"을 문자열로 가져온다.
        // 이때, 월 정보만 필요하기 때문에 문자열 인덱스 5부터의 정보만을 가져온다.
        val monthText = monthTable.select("p").first().text().substring(5)
        val month = monthText.toInt()

        val scheduleList = monthTable.select(".scheList li")
        for (schedule in scheduleList) {
            val scheduleEntry: LegacySchedule = parseSchedule(schedule)
            monthEntries.add(scheduleEntry)
        }

        return MonthSchedule(month, monthEntries)
    }

    private fun parseSchedule(schedule: Element): LegacySchedule {
        val dateText = schedule.select("dt span").text().replace(" ".toRegex(), "")
        val dates = if (dateText.contains("~")) dateText.split("~".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray() else arrayOf(dateText, dateText)

        val content = schedule.select("dd span").text()

        return LegacySchedule(dates, content)
    }

    companion object {
        private const val TIME_ZONE = "Asia/Seoul"
        private const val DMU_SCHEDULER_URL = "https://www.dongyang.ac.kr/dongyang/71/subview.do?year="
    }
}