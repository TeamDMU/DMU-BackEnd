package com.dmforu.admin.presentation

import com.dmforu.admin.scheduler.CrawlingScheduler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    private val crawlingScheduler: CrawlingScheduler
) {
    @GetMapping("/test")
    fun parse() {
        crawlingScheduler.noticeCrawling()
    }
}