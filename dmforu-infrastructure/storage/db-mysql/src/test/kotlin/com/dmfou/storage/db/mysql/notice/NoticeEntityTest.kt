package com.dmfou.storage.db.mysql.notice

import com.dmforu.domain.notice.Notice
import com.dmforu.storage.db.mysql.notice.NoticeEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class NoticeEntityTest {

    @DisplayName("Notice로 NoticeEntity를 생성할 수 있다.")
    @Test
    fun from() {
        // given
        val number = 1
        val type = "대학"
        val date = LocalDate.of(2024, 10, 23)
        val title = "공지사항1"
        val author = "관리자"
        val url = "https://www.test.com/1"

        val notice = Notice.of(
            number = number,
            type = type,
            date = date,
            title = title,
            author = author,
            url = url
        )

        // when
        val noticeEntity = NoticeEntity.from(notice)

        // then
        assertThat(noticeEntity).isInstanceOf(NoticeEntity::class.java)

        assertThat(noticeEntity.number).isEqualTo(number)
        assertThat(noticeEntity.type).isEqualTo(type)
        assertThat(noticeEntity.date).isEqualTo(date)
        assertThat(noticeEntity.title).isEqualTo(title)
        assertThat(noticeEntity.author).isEqualTo(author)
        assertThat(noticeEntity.url).isEqualTo(url)

    }

    @DisplayName("NoticeEntity로 Notice를 생성할 수 있다.")
    @Test
    fun toNotice() {
        // given
        val notice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 23),
            title = "공지사항1",
            author = "관리자",
            url = "https://www.test.com/1"
        )

        val noticeEntity = NoticeEntity.from(notice)

        // when
        val savedNotice = noticeEntity.toNotice()

        // then
        assertThat(savedNotice).isEqualTo(notice)

    }
}