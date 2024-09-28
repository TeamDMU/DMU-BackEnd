package com.dmforu.domain.schedule.legacy;

data class MonthSchedule(
    val month: Int,
    val scheduleEntries: List<LegacySchedule>
) {
    private constructor() : this(0, emptyList())
}