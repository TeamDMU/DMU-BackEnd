package com.dmforu.crawling.schedule

import com.dmforu.domain.schedule.ScheduleService

class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val scheduleService: ScheduleService
) {
    fun rewriteToRedis() {
        scheduleService.write(scheduleParser.parse())
    }
}