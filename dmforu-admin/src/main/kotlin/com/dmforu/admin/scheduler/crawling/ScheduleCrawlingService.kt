package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.ScheduleParser
import com.dmforu.domain.schedule.ScheduleWriter
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val scheduleWriter: ScheduleWriter
) {
    @Scheduled(cron = "0 0 8,20 * * *")
    fun overwriteToRedis() {
        scheduleWriter.overwrite(scheduleParser.parse())
    }
}