package com.dmforu.domain.schedule

import org.springframework.stereotype.Component

@Component
class ScheduleWriter(
    private val scheduleRepository: ScheduleRepository
) {
    fun write(schedules: List<Schedule.Year>) {
        scheduleRepository.write(schedules)
    }
}