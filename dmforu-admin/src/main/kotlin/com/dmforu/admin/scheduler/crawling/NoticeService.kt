package com.dmforu.admin.scheduler.crawling

import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeWriter
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class NoticeService(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val noticeWriter: NoticeWriter,
) {

    fun saveNewNotices(notices: List<Notice>, currentMaxNumber: Int): Boolean {
        for (notice in notices) {
            if (notice.isNumberLessThanOrEqualTo(currentMaxNumber)) {
                return false
            }

            noticeWriter.write(notice)
            applicationEventPublisher.publishEvent(notice)

            if (notice.isLastInType()) {
                return false
            }
        }

        return true
    }
}