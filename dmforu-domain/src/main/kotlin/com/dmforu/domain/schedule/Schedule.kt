package com.dmforu.domain.schedule

class Schedule private constructor(
    val dates: List<String>,
    val content: String,
) {
    companion object {
        fun of(dateArray: List<String>, content: String): Schedule {
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

}