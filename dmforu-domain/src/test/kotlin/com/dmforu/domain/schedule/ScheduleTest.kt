package com.dmforu.domain.schedule

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ScheduleTest {

    @DisplayName("학사 일정을 생성할 수 있다.")
    @Test
    fun of() {
        // given
        val date = arrayOf("01.03 (수)", "01.05 (목)")
        val expectedDate = "[01.03 (수), 01.05 (목)]"
        val content = "정시모집 원서 접수"
        val month = 1
        val year = 2024

        // when
        val schedule = Schedule.of(date, content)
        val scheduleMonth = Schedule.Month.of(month, listOf(schedule))
        val scheduleYear = Schedule.Year.of(year, listOf(scheduleMonth))
        val schedules = listOf(scheduleYear)

        // then
        assertThat(schedules[0].year).isEqualTo(year)
        assertThat(schedules[0].yearSchedule[0].month).isEqualTo(month)
        assertThat(schedules[0].yearSchedule[0].monthSchedule[0].date).isEqualTo(expectedDate)
        assertThat(schedules[0].yearSchedule[0].monthSchedule[0].content).isEqualTo(content)

    }

}