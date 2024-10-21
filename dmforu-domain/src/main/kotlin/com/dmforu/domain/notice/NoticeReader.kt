package com.dmforu.domain.notice

import org.springframework.stereotype.Component

@Component
class NoticeReader(
    private val noticeRepository: NoticeRepository
) {
    fun searchNotice(searchWord:String, department:String, page:Int, size:Int): List<Notice> {
        return noticeRepository.findNoticesBySearchWord(searchWord, department, page, size)
    }

    fun readDepartmentNotice(department: String, page: Int, size: Int): List<Notice> {
        return noticeRepository.findDepartmentNotices(department, page, size)
    }

    fun readUniversityNotice(page: Int, size: Int): List<Notice> {
        return noticeRepository.findUniversityNotices(page, size)
    }

    fun findMaxNumberByType(type: String): Int? {
        return noticeRepository.findMaxNumberByType(type)
    }
}