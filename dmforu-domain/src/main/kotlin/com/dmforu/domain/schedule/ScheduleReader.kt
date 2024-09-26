package com.dmforu.domain.schedule

import com.dmforu.domain.schedule.legacy.LegacySchedule
import com.dmforu.domain.schedule.legacy.MonthSchedule
import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.stereotype.Component

@Component
class ScheduleReader {
    @Deprecated("Use read() instead.", ReplaceWith("read()"))
    fun readOld(): List<YearSchedule> {
        val legacySchedule = LegacySchedule(arrayOf("01.01(일)", "01.01(일)"), "신정")
        val monthSchedule = MonthSchedule(1, listOf(legacySchedule))
        val yearSchedule = YearSchedule(2024, listOf(monthSchedule))

        return listOf(yearSchedule)
    }

    fun read(): List<Schedule.Year> {
        val schedule = Schedule(arrayOf("01.01(일)", "01.01(일)"), "신정")
        val monthSchedule = Schedule.Month(1, listOf(schedule))
        val yearSchedule = Schedule.Year(2024, listOf(monthSchedule))

        return listOf(yearSchedule)
    }
}