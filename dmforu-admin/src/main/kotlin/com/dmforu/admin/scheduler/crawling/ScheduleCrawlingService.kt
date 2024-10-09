package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.ScheduleParser
import com.dmforu.domain.schedule.ScheduleWriter

class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val scheduleWriter: ScheduleWriter
) {
    fun overwriteToRedis() {
        scheduleWriter.overwrite(scheduleParser.parse())
    }
}