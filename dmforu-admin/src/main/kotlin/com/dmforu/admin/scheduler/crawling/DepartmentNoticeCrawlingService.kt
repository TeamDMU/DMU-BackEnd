package com.dmforu.admin.scheduler.crawling

import com.dmforu.crawling.parser.DepartmentCrawlingPath
import com.dmforu.crawling.parser.DepartmentNoticeParser
import com.dmforu.domain.notice.*
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class DepartmentNoticeCrawlingService(
    private val prototypeBeanProvider: ObjectProvider<DepartmentNoticeParser>,
    private val noticeReader: NoticeReader,
    private val noticeService: NoticeService
) {

    fun addRecentDepartmentNotice() {
        for (major in DepartmentCrawlingPath.entries) {
            crawlMajorDepartment(major)
        }
    }

    private fun crawlMajorDepartment(major: DepartmentCrawlingPath) {
        val parser = prototypeBeanProvider.getObject()

        val maxNumber = noticeReader.findMaxNumberByType(major.type)
        val currentMaxNumber = maxNumber ?: 0

        while (true) {
            val notices: List<Notice> = parser.parse(major)
            val isNewNoticeFound = noticeService.saveNewNotices(notices, currentMaxNumber)

            if (!isNewNoticeFound) {
                return
            }
        }
    }

}
