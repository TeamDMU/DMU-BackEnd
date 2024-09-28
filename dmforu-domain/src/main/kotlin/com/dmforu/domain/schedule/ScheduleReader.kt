package com.dmforu.domain.schedule

import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.stereotype.Component

@Component
class ScheduleReader(
    private val scheduleRepository: ScheduleRepository
) {
    @Deprecated("Use read() instead.", ReplaceWith("read()"))
    fun readOld(): List<YearSchedule> {
        return scheduleRepository.readOld().orEmpty()
    }

    fun read(): List<Schedule.Year> {
        return scheduleRepository.read().orEmpty()
    }
}