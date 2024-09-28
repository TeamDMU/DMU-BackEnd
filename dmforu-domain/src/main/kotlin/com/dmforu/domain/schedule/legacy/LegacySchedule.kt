package com.dmforu.domain.schedule.legacy;

data class LegacySchedule(
    val date: String,
    val content: String
) {
    private constructor() : this("", "")

    constructor(dateArray: Array<String>, content: String) : this(
        dateArray.joinToString(
            prefix = "[",
            separator = ", ",
            postfix = "]"
        ), content
    )
}