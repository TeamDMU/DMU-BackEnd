package com.dmforu.storage.db.mysql.notice

import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository

@Repository
internal class NoticeEntityRepository(
    private val noticeJpaRepository: NoticeJpaRepository,
) : NoticeRepository {

    @Transactional
    override fun write(notice: Notice) {
        noticeJpaRepository.save(NoticeEntity.from(notice))
    }

    override fun findNoticesBySearchWord(
        searchWord: String,
        department: String,
        page: Int,
        size: Int,
    ): List<Notice> {
        val pageable = pageRequest(page, size)
        val noticePage = noticeJpaRepository.findBySearchWordAndDepartment(searchWord, department, pageable)
        return noticePage.map { it.toNotice() }.toList()
    }

    override fun findDepartmentNotices(
        department: String,
        page: Int,
        size: Int,
    ): List<Notice> {
        val pageable = pageRequest(page, size)
        val departmentNoticePage = noticeJpaRepository.findByType(department, pageable)
        return departmentNoticePage.map { it.toNotice() }.toList()
    }

    override fun findUniversityNotices(page: Int, size: Int): List<Notice> {
        val pageable = pageRequest(page, size)
        val universityNoticePage = noticeJpaRepository.findByType("대학", pageable)
        return universityNoticePage.map { it.toNotice() }.toList()
    }

    override fun findMaxNumberByType(type: String): Int? {
        return noticeJpaRepository.findMaxNumberByType(type)
    }

    private fun pageRequest(page: Int, size: Int): PageRequest {
        return PageRequest.of(
            page - 1, size, Sort.by(
                Sort.Order.desc("date"),
                Sort.Order.desc("id")
            )
        )
    }
}