package com.dmforu.domain.schedule.legacy;

data class YearSchedule (
    val year: Int,
    val yearSchedule: List<MonthSchedule>
) {
    private constructor() : this(0, emptyList())
}