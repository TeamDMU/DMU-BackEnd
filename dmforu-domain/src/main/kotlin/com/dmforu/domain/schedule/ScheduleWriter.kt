package com.dmforu.domain.schedule

import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.stereotype.Component

@Component
class ScheduleWriter(
    private val scheduleRepository: ScheduleRepository
) {
    @Deprecated("Use write() instead.", ReplaceWith("write()"))
    fun writeOld(schedules: List<YearSchedule>) {
        scheduleRepository.writeOld(schedules)
    }

    fun write(schedules: List<Schedule.Year>) {
        scheduleRepository.write(schedules)
    }
}