package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.ScheduleParser
import com.dmforu.domain.schedule.ScheduleService

class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val scheduleService: ScheduleService
) {
    fun rewriteToRedis() {
        scheduleService.write(scheduleParser.parse())
    }
}