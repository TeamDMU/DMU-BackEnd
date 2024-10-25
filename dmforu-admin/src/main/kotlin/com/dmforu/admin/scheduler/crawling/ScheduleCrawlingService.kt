package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.ScheduleParser
import com.dmforu.domain.schedule.ScheduleWriter
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val scheduleWriter: ScheduleWriter,
) {
    fun overwriteToRedis() {
        scheduleWriter.overwrite(scheduleParser.parse(LocalDate.now().year))
    }
}