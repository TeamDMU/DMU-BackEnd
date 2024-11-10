package com.dmforu.admin.scheduler

import com.dmforu.admin.scheduler.crawling.DepartmentNoticeCrawlingService
import com.dmforu.admin.scheduler.crawling.DietCrawlingService
import com.dmforu.admin.scheduler.crawling.ScheduleCrawlingService
import com.dmforu.admin.scheduler.crawling.UniversityNoticeCrawlingService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class CrawlingScheduler (
    private val departmentNoticeCrawlingService: DepartmentNoticeCrawlingService,
    private val universityNoticeCrawlingService: UniversityNoticeCrawlingService,
    private val dietCrawlingService: DietCrawlingService,
    private val scheduleCrawlingService: ScheduleCrawlingService,
) {
    @Scheduled(cron = "0 */10 9-19 * * MON-FRI")
    fun noticeCrawling() {
        departmentNoticeCrawlingService.addRecentDepartmentNotice()
        universityNoticeCrawlingService.addRecentUniversityNotice()
    }

    @Scheduled(cron = "0 0 8,20 * * *")
    fun dietAndScheduleCrawling() {
        dietCrawlingService.updateToRecentDiet()
        scheduleCrawlingService.updateToRecentSchedule()
    }
}