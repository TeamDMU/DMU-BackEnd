package com.dmforu.domain.schedule

class ScheduleWriter(
    private val scheduleRepository: ScheduleRepository
) {
    fun overwrite(schedules: List<Schedule.Year>) {
        scheduleRepository.write(schedules)
    }
}