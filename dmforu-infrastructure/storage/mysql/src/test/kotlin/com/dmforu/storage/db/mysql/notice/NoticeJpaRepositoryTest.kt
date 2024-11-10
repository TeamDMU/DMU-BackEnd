package com.dmforu.storage.db.mysql.notice

import com.dmforu.domain.notice.Notice
import com.dmforu.storage.db.mysql.MysqlIntegrationTest
import com.dmforu.storage.db.mysql.config.MysqlJpaConfig
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate

class NoticeJpaRepositoryTest : MysqlIntegrationTest() {

    @AfterEach
    fun tearDown() {
        noticeRepository.deleteAllInBatch()
    }

    @Autowired
    private lateinit var noticeRepository: NoticeJpaRepository

    @DisplayName("타입에 해당하는 공지에서 가장 최신 번호를 가져온다.")
    @Test
    fun findMaxNumberByType() {
        // given
        val maxNumber = 2
        val type = "대학"

        val notice1 = Notice.of(
            number = 1,
            type = type,
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = maxNumber,
            type = type,
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2)
            )
        )

        // when
        val result = noticeRepository.findMaxNumberByType(type)

        // then
        assertThat(result).isEqualTo(maxNumber)
    }

    @DisplayName("타입에 해당하는 공지가 존재하지 않는 경우, null을 반환한다.")
    @Test
    fun findMaxNumberByTypeWhenEmpty() {
        // given
        val type = "기계공학과"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항3",
            author = "관리자",
            url = "https://www.test.com/3"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2)
            )
        )

        // when
        val result = noticeRepository.findMaxNumberByType(type)

        // then
        assertThat(result).isNull()
    }

    @DisplayName("원하는 타입의 공지를 최신순으로 불러온다.")
    @Test
    fun findByType() {
        // given
        val type = "대학"

        val notice1 = Notice.of(
            number = 1,
            type = type,
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 2,
            type = type,
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        val notice3 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항3",
            author = "관리자",
            url = "https://www.test.com/3"
        )

        val pageable = pageRequest()

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
                NoticeEntity.from(notice3),
            )
        )

        // when
        val result = noticeRepository.findByType(type, pageable)

        // then
        assertThat(result).hasSize(2)
            .extracting("number", "type")
            .containsExactly(
                tuple(2, type),
                tuple(1, type)
            )
    }

    @DisplayName("원하는 타입의 공지가 존재하지 않는 경우, 빈 Page를 반환한다.")
    @Test
    fun findByTypeWhenEmpty() {
        // given
        val type = "기계공학과"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항3",
            author = "관리자",
            url = "https://www.test.com/3"
        )

        val pageable = pageRequest()

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
            )
        )

        // when
        val result = noticeRepository.findByType(type, pageable)

        // then
        assertThat(result).isEmpty()
    }

    @DisplayName("선택한 학과, 대학 공지에서 검색어가 포함된 공지를 최신순으로 불러온다.")
    @Test
    fun findBySearchWordAndDepartment() {
        // given
        val type = "컴퓨터소프트웨어공학과"
        val searchWord = "공지"
        val title1 = "공지사항1"
        val title2 = "공지사항2"
        val title3 = "공지사항3"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = title1,
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 2,
            type = "대학",
            date = LocalDate.of(2024, 10, 24),
            title = title2,
            author = "관리자",
            url = "https://www.test.com/2"
        )

        val notice3 = Notice.of(
            number = 1,
            type = type,
            date = LocalDate.of(2024, 10, 25),
            title = title3,
            author = "관리자",
            url = "https://www.test.com/3"
        )

        val notice4 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 26),
            title = "공지사항4",
            author = "관리자",
            url = "https://www.test.com/4"
        )

        val pageable = pageRequest()

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
                NoticeEntity.from(notice3),
                NoticeEntity.from(notice4),
            )
        )

        // when
        val result = noticeRepository.findBySearchWordAndDepartment(searchWord, type, pageable)

        // then
        assertThat(result).hasSize(3)
            .extracting("number", "date", "type")
            .containsExactly(
                tuple(1, LocalDate.of(2024, 10, 25), type),
                tuple(2, LocalDate.of(2024, 10, 24), "대학"),
                tuple(1, LocalDate.of(2024, 10, 23), "대학"),
            )

        val pages = result.get().toList()
        assertThat(pages[0].title).contains(searchWord)
        assertThat(pages[1].title).contains(searchWord)
        assertThat(pages[2].title).contains(searchWord)
    }

    @DisplayName("선택한 학과, 대학 공지에서 검색어가 포함된 공지가 없는 경우, 빈 Page를 반환한다.")
    @Test
    fun findBySearchWordAndDepartmentWhenEmpty() {
        // given
        val type = "기계공학과"
        val searchWord = "학사일정"
        val title1 = "공지사항1"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        val pageable = pageRequest()

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
            )
        )

        // when
        val result = noticeRepository.findByType(type, pageable)

        // then
        assertThat(result).isEmpty()
        assertThat(title1).doesNotContain(searchWord)
    }


    private fun pageRequest(): PageRequest {
        return PageRequest.of(
            0, 20, Sort.by(
                Sort.Order.desc("date"),
                Sort.Order.desc("id")
            )
        )
    }
}