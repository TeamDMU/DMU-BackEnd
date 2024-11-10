package com.dmforu.domain.schedule

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScheduleWriterTest {

    @Mock
    private lateinit var scheduleRepository: ScheduleRepository

    @InjectMocks
    private lateinit var scheduleWriter: ScheduleWriter

    @DisplayName("학사 일정 정보를 저장할 수 있다.")
    @Test
    fun write() {
        // given
        val date = listOf("01.03 (수)", "01.05 (목)")
        val content = "정시모집 원서 접수"
        val schedule = Schedule.of(date, content)
        val scheduleMonth = Schedule.Month.of(1, listOf(schedule))
        val scheduleYear = Schedule.Year.of(2024, listOf(scheduleMonth))
        val schedules = listOf(scheduleYear)

        // when
        scheduleWriter.overwrite(schedules)

        // given
        verify(scheduleRepository).write(schedules)

    }

}