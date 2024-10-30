package com.dmforu.admin.scheduler

import com.dmforu.admin.scheduler.crawling.DepartmentNoticeCrawlingService
import com.dmforu.admin.scheduler.crawling.DietCrawlingService
import com.dmforu.admin.scheduler.crawling.ScheduleCrawlingService
import com.dmforu.admin.scheduler.crawling.UniversityNoticeCrawlingService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CrawlingSchedulerTest {

    @Mock
    private lateinit var departmentNoticeCrawlingService: DepartmentNoticeCrawlingService

    @Mock
    private lateinit var universityNoticeCrawlingService: UniversityNoticeCrawlingService

    @Mock
    private lateinit var dietCrawlingService: DietCrawlingService

    @Mock
    private lateinit var scheduleCrawlingService: ScheduleCrawlingService

    @InjectMocks
    private lateinit var crawlingScheduler: CrawlingScheduler

    @DisplayName("대학 공지와 학과 공지를 크롤링하고 최신 정보를 추가한다.")
    @Test
    fun noticeCrawling() {
        // when
        crawlingScheduler.noticeCrawling()

        // then
        verify(departmentNoticeCrawlingService).addRecentDepartmentNotice()
        verify(universityNoticeCrawlingService).addRecentUniversityNotice()
    }

    @DisplayName("식단과 학사 일정을 크롤링하고 최신 정보로 업데이트한다.")
    @Test
    fun dietAndScheduleCrawling() {
        // when
        crawlingScheduler.dietAndScheduleCrawling()

        // then
        verify(dietCrawlingService).updateToRecentDiet()
        verify(scheduleCrawlingService).updateToRecentSchedule()
    }

}