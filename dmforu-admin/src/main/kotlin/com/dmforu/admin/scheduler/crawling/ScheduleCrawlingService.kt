package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.ScheduleParser
import com.dmforu.domain.schedule.ScheduleWriter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val scheduleWriter: ScheduleWriter,
) {
    fun updateToRecentSchedule() {
        val log: Logger = LoggerFactory.getLogger(DietCrawlingService::class.java)
        log.info("학사 일정 스크래핑 시작")
        scheduleWriter.overwrite(scheduleParser.parse(LocalDate.now().year))
        log.info("학사 일정 스크래핑 종료")
    }
}