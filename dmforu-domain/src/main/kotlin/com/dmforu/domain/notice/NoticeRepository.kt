package com.dmforu.domain.notice

interface NoticeRepository {
    fun save(notice: Notice)
    fun findNoticesBySearchWord(searchWord: String, department: String, page: Int, size: Int): List<Notice>
    fun findDepartmentNotices(department: String, page: Int, size: Int): List<Notice>
    fun findUniversityNotices(page: Int, size: Int): List<Notice>
    fun findMaxNumberByType(type: String): Int?
}