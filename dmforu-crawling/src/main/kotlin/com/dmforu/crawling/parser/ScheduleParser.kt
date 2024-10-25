package com.dmforu.crawling.parser

import com.dmforu.crawling.exception.ScheduleParseException
import com.dmforu.crawling.loader.HtmlLoader
import com.dmforu.domain.schedule.Schedule
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class ScheduleParser(
    private val htmlLoader: HtmlLoader<Document>,
) {
    fun parse(currentYear: Int): List<Schedule.Year> {
        return (currentYear - 1..currentYear + 1)
            .map { year ->
                val yearSchedule = fetchYearSchedule(year)
                Schedule.Year.of(year, yearSchedule)
            }
    }

    private fun fetchYearSchedule(year: Int): List<Schedule.Month> {
        val document = htmlLoader.get(DMU_SCHEDULE_URL + year)
        val monthTables = document.select(YEAR_SCHEDULE_SELECTOR)

        return monthTables.mapNotNull { monthTable ->
            val monthSchedule = fetchMonthSchedule(monthTable)

            // 일정 정보가 업로드 되지 않는 달은 제외한다. (다음 년도 3월 이후의 정보)
            if (monthSchedule.monthSchedule.isEmpty()) null else monthSchedule
        }
    }

    private fun fetchMonthSchedule(monthTable: Element): Schedule.Month {
        // <p id="yearmonth20241">2024.1</p> p태그에서 "2024.1"을 문자열로 가져온다.
        // 이때, 월 정보만 필요하기 때문에 문자열 인덱스 5부터의 정보만을 가져온다.
        val monthText = monthTable.select(MONTH_DATE_SELECTOR).first()?.text()
            ?: throw ScheduleParseException("학사 일정 월 정보를 찾을 수 없습니다.")

        val month = extractMonth(monthText)

        val scheduleTable = monthTable.select(MONTH_SCHEDULE_SELECTOR)
        val monthSchedule = scheduleTable.map { schedule -> parseSchedule(schedule) }

        return Schedule.Month.of(month, monthSchedule)
    }

    private fun extractMonth(monthText: String): Int {
        return monthText.split(MONTH_SEPARATOR).last().toIntOrNull()
            ?: throw ScheduleParseException("학사 일정 월 정보를 숫자로 변경할 수 없습니다.")
    }

    /**
     * 다음과 같이 일정을 파싱한다.
     *
     * 01.01 (월)	신정
     * -> ["01.01 (월)", "01.01 (월)"], "신정"
     *
     * 01.03 (수) ~ 01.15 (월)	정시모집 원서 접수
     * -> ["01.03 (수)", "01.15 (월)"], "정시모집 원서 접수"
     */
    private fun parseSchedule(schedule: Element): Schedule {
        val dateText = schedule.select(SCHEDULE_DATE_SELECTOR).text()
            .replace(SPACE_CHAR, EMPTY_CHAR)
            .replace(DOUBLE_QUQATATION_CHAR, EMPTY_CHAR)
        val dates = if (dateText.contains(DATE_SEPARATOR)) {
            dateText.split(DATE_SEPARATOR)
        } else {
            listOf(dateText, dateText)
        }

        val content = schedule.select(SCHEDULE_CONTENT_SELECTOR).text()

        return Schedule.of(dates, content)
    }

    companion object {
        private const val DMU_SCHEDULE_URL = "https://www.dongyang.ac.kr/dongyang/71/subview.do?year="
        private const val YEAR_SCHEDULE_SELECTOR = ".yearSchdulWrap"
        private const val MONTH_DATE_SELECTOR = "p"
        private const val MONTH_SCHEDULE_SELECTOR = ".scheList li"
        private const val MONTH_SEPARATOR = "."
        private const val SCHEDULE_DATE_SELECTOR = "dt span"
        private const val SCHEDULE_CONTENT_SELECTOR = "dd span"
        private const val DATE_SEPARATOR = "~"
        private const val DOUBLE_QUQATATION_CHAR = "\""
        private const val EMPTY_CHAR = ""
        private const val SPACE_CHAR = " "
    }
}
