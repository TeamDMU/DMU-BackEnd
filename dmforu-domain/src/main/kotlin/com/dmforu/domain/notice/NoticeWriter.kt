package com.dmforu.domain.notice

import org.springframework.stereotype.Component

@Component
class NoticeWriter (
    private val noticeRepository: NoticeRepository
){
    fun write(notice: Notice) {
        noticeRepository.write(notice)
    }
}