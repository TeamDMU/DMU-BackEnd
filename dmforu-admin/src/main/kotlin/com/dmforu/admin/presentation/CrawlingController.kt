package com.dmforu.admin.presentation

import com.dmforu.admin.scheduler.CrawlingScheduler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CrawlingController(
    private val crawlingScheduler: CrawlingScheduler
) {

    @GetMapping("/crawling")
    fun crawlingNotice() {
        crawlingScheduler.noticeCrawling()
    }

    @GetMapping("/crawling2")
    fun crawlingScheduleAndMenu() {
        crawlingScheduler.dietAndScheduleCrawling()
    }
}