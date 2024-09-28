package com.dmforu.domain.schedule

import com.dmforu.domain.schedule.legacy.YearSchedule

interface ScheduleRepository {
    fun writeOld(schedules: List<YearSchedule>)
    fun write(schedules: List<Schedule.Year>)
    fun readOld(): List<YearSchedule>?
    fun read(): List<Schedule.Year>?
}