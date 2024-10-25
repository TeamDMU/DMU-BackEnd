package com.dmforu.domain.notice

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class NoticeReaderTest {

    @Mock
    private lateinit var noticeRepository: NoticeRepository

    @InjectMocks
    private lateinit var noticeReader: NoticeReader

    @DisplayName("학과와 검색어를 사용하여, 대학 공지와 해당하는 학과 공지에서 검색어가 포함된 공지를 불러올 수 있다. ")
    @Test
    fun searchNotice() {
        // given
        val searchWord = "시험"
        val department = Major.COMPUTER_SOFTWARE_ENGINEERING.type
        val page = 1
        val size = 20

        val universityNotice = createNotice(
            number = 10,
            type = "대학",
            title = "시험 안내"
        )

        val departmentNotice = createNotice(
            number = 20,
            type = department,
            title = "시험 시간표 공지"
        )

        `when`(noticeRepository.findNoticesBySearchWord(searchWord, department, page, size))
            .thenReturn(
                listOf(universityNotice, departmentNotice)
            )

        // when
        val foundNotices = noticeReader.searchNotice(searchWord, department, page, size)

        // then
        assertThat(foundNotices).hasSize(2)
            .extracting("type")
            .containsExactlyInAnyOrder(
                "대학", department
            )

        verify(noticeRepository).findNoticesBySearchWord(searchWord, department, page, size)
    }

    @DisplayName("해당하는 학과의 공지를 불러올 수 있다.")
    @Test
    fun readDepartmentNotice() {
        // given
        val department = Major.COMPUTER_SOFTWARE_ENGINEERING.type
        val page = 1
        val size = 20

        val departmentNotice1 = createNotice(
            number = 1,
            type = department,
            title = "등록금 납부 기간 안내"
        )

        val departmentNotice2 = createNotice(
            number = 2,
            type = department,
            title = "시험 시간표 공지"
        )

        `when`(noticeRepository.findDepartmentNotices(department, page, size))
            .thenReturn(
                listOf(departmentNotice1, departmentNotice2)
            )

        // when
        val foundNotices = noticeReader.readDepartmentNotice(department, page, size)

        // then
        assertThat(foundNotices).hasSize(2)
            .extracting("type")
            .containsExactlyInAnyOrder(
                department, department
            )

        verify(noticeRepository).findDepartmentNotices(department, page, size)
    }

    @DisplayName("대학 공지를 불러올 수 있다.")
    @Test
    fun readUniversityNotice() {
        // given
        val type = "대학"
        val page = 1
        val size = 20

        val universityNotice1 = createNotice(
            number = 1,
            type = type,
            title = "등록금 납부 기간 안내"
        )

        val universityNotice2 = createNotice(
            number = 2,
            type = type,
            title = "시험 시간표 공지"
        )

        `when`(noticeRepository.findUniversityNotices(page, size))
            .thenReturn(
                listOf(universityNotice1, universityNotice2)
            )

        // when
        val foundNotices = noticeReader.readUniversityNotice(page, size)

        // then
        assertThat(foundNotices).hasSize(2)
            .extracting("type")
            .containsExactlyInAnyOrder(
                type, type
            )

        verify(noticeRepository).findUniversityNotices(page, size)
    }

    @DisplayName("타입에 해당하는 공지의 가장 최신 번호를 불러온다.")
    @Test
    fun findMaxNumberByType() {
        // given
        val department = Major.COMPUTER_SOFTWARE_ENGINEERING.type
        val maxNumber = 10

        `when`(noticeRepository.findMaxNumberByType(department))
            .thenReturn(maxNumber)

        // then
        val foundMaxNumber = noticeReader.findMaxNumberByType(department)

        // when
        assertThat(foundMaxNumber).isEqualTo(maxNumber)

        verify(noticeRepository).findMaxNumberByType(department)
    }

    @DisplayName("타입에 해당하는 공지가 없는 경우 null 을 반환한다.")
    @Test
    fun findMaxNumberByTypeWhenEmpty() {
        // given
        val department = Major.COMPUTER_SOFTWARE_ENGINEERING.type

        `when`(noticeRepository.findMaxNumberByType(department))
            .thenReturn(null)

        // when
        val foundMaxNumber = noticeReader.findMaxNumberByType(department)

        // then
        assertThat(foundMaxNumber).isNull()

        verify(noticeRepository).findMaxNumberByType(department)
    }

    private fun createNotice(
        number: Int,
        type: String,
        title: String,
        date: LocalDate = LocalDate.now(),
        author: String = "작성자",
        url: String = "https://www.test.com",
    ): Notice {
        return Notice.of(
            number = number,
            type = type,
            date = date,
            title = title,
            author = author,
            url = url
        )
    }
}