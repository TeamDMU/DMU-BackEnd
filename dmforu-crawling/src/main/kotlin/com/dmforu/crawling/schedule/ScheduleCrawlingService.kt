package com.dmforu.crawling.schedule

import com.dmforu.domain.schedule.ScheduleService

class ScheduleCrawlingService(
    private val scheduleParser: ScheduleParser,
    private val oldScheduleParser: LegacyScheduleParser,
    private val scheduleService: ScheduleService
) {
    fun refreshAllSchedules() {
        rewriteScheduleToRedis()
        rewriteOldScheduleToRedis()
    }

    private fun rewriteScheduleToRedis() {
        scheduleService.write(scheduleParser.parse())
    }

    private fun rewriteOldScheduleToRedis() {
        scheduleService.writeOld(oldScheduleParser.parse())
    }
}