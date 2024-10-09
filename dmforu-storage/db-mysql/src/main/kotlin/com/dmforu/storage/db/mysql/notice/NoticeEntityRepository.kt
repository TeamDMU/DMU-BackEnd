package com.dmforu.storage.db.mysql.notice

import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
internal class NoticeEntityRepository(
    private val noticeJpaRepository: NoticeJpaRepository
) : NoticeRepository {
    override fun write(notice: Notice) {
        noticeJpaRepository.save(NoticeEntity.from(notice))
    }

    override fun findNoticesBySearchWord(
        searchWord: String,
        department: String,
        page: Int,
        size: Int
    ): List<Notice> {
        val pageable = pageRequest(page, size)
        val noticePage = noticeJpaRepository.findBySearchWordAndDepartment(searchWord, department, pageable)
        return noticePage?.mapNotNull { it?.toNotice() }?.toList().orEmpty()
    }

    override fun findDepartmentNotices(
        department: String,
        page: Int,
        size: Int
    ): List<Notice> {
        val pageable = pageRequest(page, size)
        val departmentNoticePage = noticeJpaRepository.findByType(department, pageable)
        return departmentNoticePage?.mapNotNull { it?.toNotice() }?.toList().orEmpty()
    }

    override fun findUniversityNotices(page: Int, size: Int): List<Notice> {
        val pageable = pageRequest(page, size)
        val universityNoticePage = noticeJpaRepository.findByType("대학", pageable)
        return universityNoticePage?.mapNotNull { it?.toNotice() }?.toList().orEmpty()
    }

    private fun pageRequest(page: Int, size: Int): PageRequest {
        val pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "number"))
        return pageable
    }
}