package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.DietParser
import com.dmforu.domain.diet.DietWriter
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class DietCrawlingService(
    private val dietParser: DietParser,
    private val dietWriter: DietWriter,
) {
    @Scheduled(cron = "0 0 8,20 * * *")
    fun overwriteToRedis() {
        dietWriter.overwrite(dietParser.parse())
    }
}