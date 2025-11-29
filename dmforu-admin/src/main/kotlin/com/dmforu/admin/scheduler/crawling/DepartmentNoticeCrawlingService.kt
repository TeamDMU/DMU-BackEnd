package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.loader.JsoupHtmlLoader
import com.dmforu.crawling.parser.DepartmentCrawlingPath
import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.domain.notice.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class DepartmentNoticeCrawlingService(
//    private val htmlLoader: JsoupHtmlLoader,
    private val objectProvider: ObjectProvider<DepartmentNoticeParser>,
    private val noticeReader: NoticeReader,
    private val noticeService: NoticeService
) {

    fun addRecentDepartmentNotice() {
        for (major in DepartmentCrawlingPath.entries) {
            crawlMajorDepartment(major)
        }
    }

    private fun crawlMajorDepartment(major: DepartmentCrawlingPath) {
        val parser = objectProvider.getObject()
        val log: Logger = LoggerFactory.getLogger(DepartmentNoticeParser::class.java)
        log.info("${major} 스크래핑 시작")

        val maxNumber = noticeReader.findMaxNumberByType(major.type)
        val currentMaxNumber = maxNumber ?: 0

        while (true) {
            val notices: List<Notice> = parser.parse(major)
            val isNewNoticeFound = noticeService.saveNewNotices(notices, currentMaxNumber)

            if (!isNewNoticeFound) {
                log.info("${major} 스크래핑 종료")
                return
            }
        }
    }

}
