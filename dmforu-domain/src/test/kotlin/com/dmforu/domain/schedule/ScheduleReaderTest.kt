package com.dmforu.domain.schedule


import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScheduleReaderTest {

    @Mock
    private lateinit var scheduleRepository: ScheduleRepository

    @InjectMocks
    private lateinit var scheduleReader: ScheduleReader

    @DisplayName("학사 일정을 불러온다.")
    @Test
    fun read() {
        // given
        val date = listOf("01.03 (수)", "01.05 (목)")
        val expectedDate = "[01.03 (수), 01.05 (목)]"
        val content = "정시모집 원서 접수"
        val month = 1
        val year = 2024

        val schedule = Schedule.of(date, content)
        val scheduleMonth = Schedule.Month.of(month, listOf(schedule))
        val scheduleYear = Schedule.Year.of(year, listOf(scheduleMonth))
        val expectedSchedules = listOf(scheduleYear)
        given(scheduleReader.read()).willReturn(expectedSchedules)

        // when
        scheduleReader.read()

        // then
        verify(scheduleRepository).read()

    }

    @DisplayName("학사 일정이 존재하지 않는 경우 예외가 발생한다.")
    @Test
    fun readWhenReturnNull() {
        // given
        given(scheduleReader.read()).willReturn(null)

        // when // then
        assertThatThrownBy { scheduleReader.read() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("No schedule exists 관리자 에러")

        verify(scheduleRepository).read()
    }
}