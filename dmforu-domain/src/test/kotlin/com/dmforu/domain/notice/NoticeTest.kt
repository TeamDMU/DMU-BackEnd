package com.dmforu.domain.notice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class NoticeTest {

    @DisplayName("공지의 번호가 특정 숫자보다 작거나 같은지 확인할 수 있다.")
    @Test
    fun isNumberLessThanOrEqualTo() {
        // given
        val notice = Notice.of(
            number = 10,
            type = "대학",
            date = LocalDate.now(),
            title = "공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        // when // then
        assertTrue(notice.isNumberLessThanOrEqualTo(10))
        assertFalse(notice.isNumberLessThanOrEqualTo(9))

    }

    @DisplayName("특정 타입의 마지막 공지인지 확인할 수 있다.")
    @Test
    fun isLastInType() {
        // given
        val lastNotice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.now(),
            title = "공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        val notLastNotice = Notice.of(
            number = 2,
            type = "대학",
            date = LocalDate.now(),
            title = "공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        // when // then
        assertTrue(lastNotice.isLastInType())
        assertFalse(notLastNotice.isLastInType())

    }

    @DisplayName("대학 공지인지 확인할 수 있다.")
    @Test
    fun isUniversalNotice() {
        // given
        val notice = Notice.of(
            number = 10,
            type = "대학",
            date = LocalDate.now(),
            title = "공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        // when // then
        assertTrue(notice.isUniversityNotice())

    }

}