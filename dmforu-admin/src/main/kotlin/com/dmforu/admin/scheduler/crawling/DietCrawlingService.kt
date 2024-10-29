package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DietParser
import com.dmforu.domain.diet.DietWriter
import org.springframework.stereotype.Service

@Service
class DietCrawlingService(
    private val dietParser: DietParser,
    private val dietWriter: DietWriter,
) {
    fun updateToRecentDiet() {
        dietWriter.overwrite(dietParser.parse())
    }
}