package com.dmforu.domain.message

import com.dmforu.domain.notice.Notice
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class NoticeMessageTest {

    @DisplayName("대학 공지 메세지를 생성할 때, 제목에 키워드가 들어간다.")
    @Test
    fun createUniversityNoticeMessage() {
        // given
        val notice = Notice.of(
            number = 10,
            type = "대학",
            date = LocalDate.now(),
            title = "시험 공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        val keyword = "시험"

        // when
        val message = NoticeMessage.createUniversityNoticeMessage(notice = notice, keyword = keyword)

        // then
        assertThat(message.title).isEqualTo("[ " + keyword + " ]  키워드 알림 도착")

    }

    @DisplayName("학과 공지 메세지를 생성할 때, 제목에 학과명이 들어간다.")
    @Test
    fun createDepartmentNoticeMessage() {
        // given
        val notice = Notice.of(
            number = 10,
            type = "컴퓨터소프트웨어공학과",
            date = LocalDate.now(),
            title = "시험 공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        // when
        val message = NoticeMessage.createDepartmentNoticeMessage(notice)

        // then
        assertThat(message.title).isEqualTo("[ " + notice.type + " ]  키워드 알림 도착")

    }
}