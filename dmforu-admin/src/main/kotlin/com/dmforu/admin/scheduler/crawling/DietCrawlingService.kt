package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.DietParser
import com.dmforu.domain.diet.DietService
import com.dmforu.domain.diet.DietWriter

class DietCrawlingService(
    private val dietParser: DietParser,
    private val dietWriter: DietWriter,
) {
    fun overwriteToRedis() {
        dietWriter.overwrite(dietParser.parse())
    }
}