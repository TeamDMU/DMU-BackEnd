package com.dmforu.domain.schedule

import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val scheduleReader: ScheduleReader,
    private val scheduleWriter: ScheduleWriter
) {
    @Deprecated("Use read() instead.", ReplaceWith("read()"))
    fun readOld(): List<YearSchedule> {
        return scheduleReader.readOld()
    }

    fun read(): List<Schedule.Year> {
        return scheduleReader.read()
    }

    @Deprecated("Use write() instead.", ReplaceWith("write()"))
    fun writeOld(schedules: List<YearSchedule>) {
        return scheduleWriter.writeOld(schedules)
    }

    fun write(schedules: List<Schedule.Year>) {
        return scheduleWriter.write(schedules)
    }
}