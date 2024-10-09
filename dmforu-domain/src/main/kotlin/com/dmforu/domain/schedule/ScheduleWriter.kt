package com.dmforu.domain.schedule

import org.springframework.stereotype.Component

@Component
class ScheduleWriter(
    private val scheduleRepository: ScheduleRepository
) {
    fun overwrite(schedules: List<Schedule.Year>) {
        scheduleRepository.write(schedules)
    }
}