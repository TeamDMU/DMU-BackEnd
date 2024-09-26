package com.dmforu.domain.schedule

import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val scheduleReader: ScheduleReader
) {
    @Deprecated("Use read() instead.", ReplaceWith("read()"))
    fun readOld(): List<YearSchedule> {
        return scheduleReader.readOld()
    }

    fun read(): List<Schedule.Year> {
        return scheduleReader.read()
    }
}