package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DietParser
import com.dmforu.domain.diet.DietWriter
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class DietCrawlingService(
    private val dietParser: DietParser,
    private val dietWriter: DietWriter,
) {
    fun updateToRecentDiet() {
        val log: Logger = LoggerFactory.getLogger(DietCrawlingService::class.java)
        log.info("식단 스크래핑 시작")
        dietWriter.overwrite(dietParser.parse())
        log.info("식단 스크래핑 종료")
    }
}