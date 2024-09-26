package com.dmforu.domain.schedule

data class Schedule(
    val date: String,
    val content: String
) {
    constructor(dateArray: Array<String>, content: String) : this(
        dateArray.joinToString(
            prefix = "[",
            separator = ", ",
            postfix = "]"
        ), content
    )

    data class Month(
        val month: Int,
        val monthSchedule: List<Schedule>
    )

    data class Year(
        val year: Int,
        val yearSchedule: List<Month>
    )
}