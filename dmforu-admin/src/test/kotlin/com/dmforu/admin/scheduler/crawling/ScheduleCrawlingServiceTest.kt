package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.ScheduleParser
import com.dmforu.domain.schedule.Schedule
import com.dmforu.domain.schedule.ScheduleWriter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any

@ExtendWith(MockitoExtension::class)
class ScheduleCrawlingServiceTest {

    @Mock
    private lateinit var scheduleParser: ScheduleParser

    @Mock
    private lateinit var scheduleWriter: ScheduleWriter

    @InjectMocks
    private lateinit var scheduleCrawlingService: ScheduleCrawlingService

    @DisplayName("새로운 학사 일정을 덮어쓴다.")
    @Test
    fun updateToRecentSchedule() {
        // given
        val parseResult: List<Schedule.Year> = mock()
        given(scheduleParser.parse(any())).willReturn(parseResult)

        // when
        scheduleCrawlingService.updateToRecentSchedule()

        // then
        verify(scheduleParser).parse(any())
        verify(scheduleWriter).overwrite(any())

    }
}