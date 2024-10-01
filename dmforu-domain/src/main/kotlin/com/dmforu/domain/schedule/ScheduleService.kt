package com.dmforu.domain.schedule

import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val scheduleReader: ScheduleReader,
    private val scheduleWriter: ScheduleWriter
) {
    fun read(): List<Schedule.Year> {
        return scheduleReader.read()
    }

    fun write(schedules: List<Schedule.Year>) {
        return scheduleWriter.write(schedules)
    }
}