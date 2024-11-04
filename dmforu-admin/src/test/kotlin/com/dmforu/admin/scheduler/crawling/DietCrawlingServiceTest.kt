package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DietParser
import com.dmforu.domain.diet.Diet
import com.dmforu.domain.diet.DietWriter
import com.dmforu.domain.schedule.Schedule
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
class DietCrawlingServiceTest {

    @Mock
    private lateinit var dietParser: DietParser

    @Mock
    private lateinit var dietWriter: DietWriter

    @InjectMocks
    private lateinit var dietCrawlingService: DietCrawlingService

    @DisplayName("새로운 식단표를 덮어쓴다.")
    @Test
    fun updateToRecentSchedule() {
        // given
        val parseResult: List<Diet> = mock()
        given(dietParser.parse()).willReturn(parseResult)

        // when
        dietCrawlingService.updateToRecentDiet()

        // then
        verify(dietParser).parse()
        verify(dietWriter).overwrite(any())

    }
}