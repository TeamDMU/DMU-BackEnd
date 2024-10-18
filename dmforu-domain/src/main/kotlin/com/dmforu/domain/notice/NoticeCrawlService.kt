package com.dmforu.domain.notice

import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class NoticeCrawlService(
    private val noticeRepository: NoticeRepository,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    fun findMaxNumberByType(type: String): Int? {
        return noticeRepository.findMaxNumberByType(type)
    }

    fun write(notice: Notice) {
        noticeRepository.write(notice)
        applicationEventPublisher.publishEvent(notice)
    }
}