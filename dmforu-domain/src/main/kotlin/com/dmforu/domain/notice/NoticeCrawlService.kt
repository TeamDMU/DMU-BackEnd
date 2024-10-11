package com.dmforu.domain.notice

import org.springframework.stereotype.Component

@Component
class NoticeCrawlService(
    private val noticeRepository: NoticeRepository
) {
    fun findMaxNumberByType(type: String): Int? {
        return noticeRepository.findMaxNumberByType(type)
    }

    fun write(notice: Notice) {
        noticeRepository.write(notice)
    }
}