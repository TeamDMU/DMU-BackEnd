package com.dmforu.domain.schedule

interface ScheduleRepository {
    fun write(schedules: List<Schedule.Year>)
    fun read(): List<Schedule.Year>?
}