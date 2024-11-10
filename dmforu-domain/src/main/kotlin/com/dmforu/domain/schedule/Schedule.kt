package com.dmforu.domain.schedule

import com.dmforu.domain.Generated

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

        @Generated
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Month

            if (month != other.month) return false
            if (monthSchedule != other.monthSchedule) return false

            return true
        }

        @Generated
        override fun hashCode(): Int {
            var result = month
            result = 31 * result + monthSchedule.hashCode()
            return result
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

        @Generated
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Year

            if (year != other.year) return false
            if (yearSchedule != other.yearSchedule) return false

            return true
        }

        @Generated
        override fun hashCode(): Int {
            var result = year
            result = 31 * result + yearSchedule.hashCode()
            return result
        }


    }

    @Generated
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Schedule

        if (dates != other.dates) return false
        if (content != other.content) return false

        return true
    }

    @Generated
    override fun hashCode(): Int {
        var result = dates.hashCode()
        result = 31 * result + content.hashCode()
        return result
    }

}