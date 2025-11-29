package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.loader.JsoupHtmlLoader
import com.dmforu.crawling.parser.UniversityNoticeParser
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeReader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service


@Service
class UniversityNoticeCrawlingService(
//    private val htmlLoader: JsoupHtmlLoader,
    private val objectProvider: ObjectProvider<UniversityNoticeParser>,
    private val noticeService: NoticeService,
    private val noticeReader: NoticeReader,
) {

    fun addRecentUniversityNotice() {
        val log: Logger = LoggerFactory.getLogger(UniversityNoticeCrawlingService::class.java)
        log.info("대학 스크래핑 시작")
        val parser = objectProvider.getObject()
        val maxNumber: Int? = noticeReader.findMaxNumberByType("대학")
        val currentMaxNumber = maxNumber ?: 0

        while (true) {
            val departmentNotices: List<Notice> = parser.parse()
            val isNewNoticeFound = noticeService.saveNewNotices(departmentNotices, currentMaxNumber)
            if (!isNewNoticeFound) {
                log.info("대학 스크래핑 종료")
                return
            }
        }
    }
}