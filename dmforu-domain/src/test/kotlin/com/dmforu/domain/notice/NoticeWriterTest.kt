package com.dmforu.domain.notice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class NoticeWriterTest {

    @Mock
    private lateinit var noticeRepository: NoticeRepository

    @InjectMocks
    private lateinit var noticeWriter: NoticeWriter

    @DisplayName("공지를 저장할 수 있다.")
    @Test
    fun write() {
        // given
        val notice = Notice.of(
            number = 10,
            type = "대학",
            date = LocalDate.now(),
            title = "공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )

        // when
        noticeWriter.write(notice)

        // then
        verify(noticeRepository).save(notice)

    }
}