package com.dmforu.domain.schedule

class Schedule private constructor(
    dateArray: Array<String>,
    val content: String,
) {
    val date: String = dateConverter(dateArray)

    companion object {
        fun of(dateArray: Array<String>, content: String): Schedule {
            return Schedule(dateArray, content)
        }
    }

    class Month private constructor(
        val month: Int,
        val monthSchedule: List<Schedule>,
    ) {
        companion object {
            fun of(month: Int, monthSchedule: List<Schedule>): Month {
                return Month(month, monthSchedule)
            }
        }
    }

    class Year private constructor(
        val year: Int,
        val yearSchedule: List<Month>,
    ) {
        companion object {
            fun of(year: Int, yearSchedule: List<Month>): Year {
                return Year(year, yearSchedule)
            }
        }
    }

    private fun dateConverter(dateArray: Array<String>): String {
        return dateArray.joinToString(
            prefix = "[",
            separator = ", ",
            postfix = "]"
        )
    }
}