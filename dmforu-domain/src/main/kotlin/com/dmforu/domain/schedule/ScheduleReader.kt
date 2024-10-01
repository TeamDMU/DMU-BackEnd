package com.dmforu.domain.schedule

import org.springframework.stereotype.Component

@Component
class ScheduleReader(
    private val scheduleRepository: ScheduleRepository
) {
    fun read(): List<Schedule.Year> {
        return scheduleRepository.read().orEmpty()
    }
}