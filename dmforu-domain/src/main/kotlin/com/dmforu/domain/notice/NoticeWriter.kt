package com.dmforu.domain.notice

class NoticeWriter(
    private val noticeRepository: NoticeRepository,
) {
    fun write(notice: Notice) {
        noticeRepository.write(notice)
    }
}