package com.dmforu.crawling.diet

import com.dmforu.domain.diet.DietService

class DietCrawlingService(
    private val dietParser: DietParser,
    private val dietService: DietService
) {
    fun rewriteToRedis() {
        dietService.write(dietParser.parse())
    }
}