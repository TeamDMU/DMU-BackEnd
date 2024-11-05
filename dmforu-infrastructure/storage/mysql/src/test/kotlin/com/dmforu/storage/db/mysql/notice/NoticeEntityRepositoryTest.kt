package com.dmforu.storage.db.mysql.notice

import com.dmforu.domain.notice.Notice
import com.dmforu.storage.db.mysql.MysqlApplicationTest
import com.dmforu.storage.db.mysql.MysqlIntegrationTest
import com.dmforu.storage.db.mysql.config.MysqlJpaConfig
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate


class NoticeEntityRepositoryTest : MysqlIntegrationTest() {

    @Autowired
    private lateinit var noticeRepository: NoticeJpaRepository

    @Autowired
    private lateinit var noticeEntityRepository: NoticeEntityRepository

    @AfterEach
    fun tearDown() {
        noticeRepository.deleteAllInBatch()
    }

    @DisplayName("공지를 저장할 수 있다.")
    @Test
    fun save() {
        // given
        val date = LocalDate.of(2024, 10, 23)
        val notice = Notice.of(
            number = 1,
            type = "대학",
            date = date,
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        // when
        noticeEntityRepository.save(notice)

        // then
        val notices = noticeRepository.findAll()
        assertThat(notices).hasSize(1)
            .extracting("number", "type", "date", "title", "author", "url")
            .containsExactly(
                tuple(1, "대학", date, "공지사항1", "관리자", "https://www.test.com/1")
            )
    }

    @DisplayName("선택한 학과, 대학 공지에서 검색어가 포함된 공지를 최신순으로 불러온다.")
    @Test
    fun findNoticesBySearchWord() {
        // given
        val searchWord = "수강"
        val department = "컴퓨터소프트웨어공학과"
        val title1 = "수강신청 안내"
        val title2 = "학사안내"
        val title3 = "학과 수강신청 안내"

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
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 25),
            title = title3,
            author = "관리자",
            url = "https://www.test.com/3"
        )

        val notice4 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 26),
            title = "수강신청안내",
            author = "관리자",
            url = "https://www.test.com/4"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
                NoticeEntity.from(notice3),
                NoticeEntity.from(notice4),
            )
        )

        // when
        val result = noticeEntityRepository.findNoticesBySearchWord(
            searchWord = searchWord,
            department = department,
            page = 1,
            size = 20
        )

        // then
        assertThat(result).hasSize(2)
            .extracting("type", "title")
            .containsExactly(
                tuple(department, title3),
                tuple("대학", title1)
            )

        assertThat(title1).contains(searchWord)
        assertThat(title3).contains(searchWord)
        assertThat(title2).doesNotContain(searchWord)
    }

    @DisplayName("선택한 학과, 대학 공지에서 검색어가 포함된 공지가 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findNoticesBySearchWordWhenEmpty() {
        // given
        val searchWord = "수강"
        val department = "컴퓨터소프트웨어공학과"
        val title1 = "학사안내"
        val title2 = "학과 학사안내"
        val title3 = "수강신청안내"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = title1,
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 25),
            title = title2,
            author = "관리자",
            url = "https://www.test.com/3"
        )

        val notice3 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 26),
            title = title3,
            author = "관리자",
            url = "https://www.test.com/4"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
                NoticeEntity.from(notice3),
            )
        )

        // when
        val result = noticeEntityRepository.findNoticesBySearchWord(
            searchWord = searchWord,
            department = department,
            page = 1,
            size = 20
        )

        // then
        assertThat(result).isEmpty()

        assertThat(title1).doesNotContain(searchWord)
        assertThat(title2).doesNotContain(searchWord)
        assertThat(title3).contains(searchWord)
    }

    @DisplayName("학과에 해당하는 공지를 최신순으로 불러온다.")
    @Test
    fun findDepartmentNotices() {
        // given
        val department = "컴퓨터소프트웨어공학과"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = department,
            date = LocalDate.of(2024, 10, 25),
            title = "공지2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        val notice3 = Notice.of(
            number = 2,
            type = department,
            date = LocalDate.of(2024, 10, 25),
            title = "공지3",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        val notice4 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 26),
            title = "공지4",
            author = "관리자",
            url = "https://www.test.com/4"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
                NoticeEntity.from(notice3),
                NoticeEntity.from(notice4)
            )
        )

        // when
        val result = noticeEntityRepository.findDepartmentNotices(department, 1, 20)

        // then
        assertThat(result).hasSize(2)
            .extracting("number", "type", "date")
            .containsExactly(
                tuple(2, department, LocalDate.of(2024, 10, 25)),
                tuple(1, department, LocalDate.of(2024, 10, 25)),
            )
    }

    @DisplayName("학과에 해당하는 공지가 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findDepartmentNoticesWhenEmpty() {
        // given
        val department = "컴퓨터소프트웨어공학과"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 25),
            title = "공지2",
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
        val result = noticeEntityRepository.findDepartmentNotices(department, 1, 20)

        // then
        assertThat(result).isEmpty()
    }

    @DisplayName("대학 공지를 최신순으로 불러온다.")
    @Test
    fun findUniversityNotices() {
        // given
        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 2,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        val notice3 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 25),
            title = "공지3",
            author = "관리자",
            url = "https://www.test.com/3"
        )


        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
                NoticeEntity.from(notice3)
            )
        )

        // when
        val result = noticeEntityRepository.findUniversityNotices(1, 20)

        // then
        assertThat(result).hasSize(2)
            .extracting("number", "type", "date")
            .containsExactly(
                tuple(2, "대학", LocalDate.of(2024, 10, 23)),
                tuple(1, "대학", LocalDate.of(2024, 10, 23)),
            )
    }

    @DisplayName("대학 공지가 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findUniversityNoticesWhenEmpty() {
        // given
        val notice1 = Notice.of(
            number = 1,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.of(2024, 10, 23),
            title = "공지1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 25),
            title = "공지2",
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
        val result = noticeEntityRepository.findUniversityNotices(1, 20)

        // then
        assertThat(result).isEmpty()
    }

    @DisplayName("타입에 해당하는 공지중 가장 최신 번호를 가져온다.")
    @Test
    fun findMaxNumberByType() {
        // given
        val type = "컴퓨터소프트웨어공학과"
        val maxNumber = 2

        val notice1 = Notice.of(
            number = 1,
            type = type,
            date = LocalDate.of(2024, 10, 23),
            title = "공지1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = maxNumber,
            type = type,
            date = LocalDate.of(2024, 10, 25),
            title = "공지2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
            )
        )
        // when
        val result = noticeEntityRepository.findMaxNumberByType(type)

        // given
        assertThat(result).isEqualTo(maxNumber)
    }

    @DisplayName("타입에 해당하는 공지가 없는 경우 null을 반환한다.")
    @Test
    fun findMaxNumberByTypeWhenEmpty() {
        // given
        val type = "컴퓨터소프트웨어공학과"

        val notice1 = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val notice2 = Notice.of(
            number = 1,
            type = "기계공학과",
            date = LocalDate.of(2024, 10, 25),
            title = "공지2",
            author = "관리자",
            url = "https://www.test.com/2"
        )

        noticeRepository.saveAll(
            listOf(
                NoticeEntity.from(notice1),
                NoticeEntity.from(notice2),
            )
        )
        // when
        val result = noticeEntityRepository.findMaxNumberByType(type)

        // given
        assertThat(result).isNull()
    }
}