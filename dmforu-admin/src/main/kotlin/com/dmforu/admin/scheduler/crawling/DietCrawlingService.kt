package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.DietParser
import com.dmforu.domain.diet.DietService

class DietCrawlingService(
    private val dietParser: DietParser,
    private val dietService: DietService
) {
    fun rewriteToRedis() {
        dietService.write(dietParser.parse())
    }
}