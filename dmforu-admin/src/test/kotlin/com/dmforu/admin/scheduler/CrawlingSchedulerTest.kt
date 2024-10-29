package com.dmforu.admin.scheduler

import com.dmforu.admin.scheduler.crawling.DepartmentNoticeCrawlingService
import com.dmforu.admin.scheduler.crawling.DietCrawlingService
import com.dmforu.admin.scheduler.crawling.ScheduleCrawlingService
import com.dmforu.admin.scheduler.crawling.UniversityNoticeCrawlingService
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class CrawlingSchedulerTest {

    @Autowired
    private lateinit var crawlingScheduler: CrawlingScheduler

    @MockBean
    private lateinit var departmentNoticeCrawlingService: DepartmentNoticeCrawlingService

    @MockBean
    private lateinit var universityNoticeCrawlingService: UniversityNoticeCrawlingService

    @MockBean
    private lateinit var dietCrawlingService: DietCrawlingService

    @MockBean
    private lateinit var scheduleCrawlingService: ScheduleCrawlingService

    @DisplayName("대학 공지와 학과 공지를 크롤링하고 최신 정보를 추가한다.")
    @Test
    fun noticeCrawling() {
        // when
        crawlingScheduler.noticeCrawling()

        // then
        verify(departmentNoticeCrawlingService).addRecentDepartmentNotice()
        verify(universityNoticeCrawlingService).addRecnetUniversityNotice()
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