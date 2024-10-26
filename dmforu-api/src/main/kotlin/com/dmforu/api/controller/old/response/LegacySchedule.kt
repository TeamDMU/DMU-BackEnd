package com.dmforu.api.controller.old.response

import com.dmforu.domain.schedule.Schedule

data class LegacySchedule(
    val date: List<String>,
    val content: String
) {
    private constructor() : this(emptyList(), "")

    data class MonthSchedule(
        val month: Int,
        val scheduleEntries: List<LegacySchedule>
    ) {
        private constructor() : this(0, emptyList())
    }

    data class YearSchedule(
        val year: Int,
        val yearSchedule: List<MonthSchedule>
    ) {
        private constructor() : this(0, emptyList())
    }

    companion object {
        fun fromScheduleYears(scheduleYears: List<Schedule.Year>): List<YearSchedule> {
            return scheduleYears.map { it.toYearSchedule() }
        }

        private fun Schedule.Year.toYearSchedule(): YearSchedule {
            return YearSchedule(
                year = this.year,
                yearSchedule = this.yearSchedule.map { it.toMonthSchedule() }
            )
        }

        private fun Schedule.Month.toMonthSchedule(): MonthSchedule {
            return MonthSchedule(
                month = this.month,
                scheduleEntries = this.monthSchedule.map { it.toLegacySchedule() }
            )
        }

        private fun Schedule.toLegacySchedule(): LegacySchedule {
            return LegacySchedule(
                date = this.dates,
                content = this.content
            )
        }
    }
}