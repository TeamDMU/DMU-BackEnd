package com.dmforu.admin.scheduler.crawling

import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.NoticeWriter
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.springframework.context.ApplicationEventPublisher

@ExtendWith(MockitoExtension::class)
class NoticeServiceTest {

    @Mock
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @Mock
    private lateinit var noticeWriter: NoticeWriter

    @InjectMocks
    private lateinit var noticeService: NoticeService

    @DisplayName("신규 공지를 저장하며, 이벤트를 발행한다. 정상적으로 처리가 되었다면 True를 반환한다.")
    @Test
    fun saveNewNotices() {
        // given
        val notice = mock(Notice::class.java)
        val notices = listOf(notice, notice)

        given(notice.isNumberLessThanOrEqualTo(any())).willReturn(false)
        given(notice.isLastInType()).willReturn(false)

        // when
        val result = noticeService.saveNewNotices(notices, 0)

        // then
        assertThat(result).isTrue()
        verify(noticeWriter, times(notices.size)).write(notice)
        verify(applicationEventPublisher, times(notices.size)).publishEvent(notice)
    }

    @DisplayName("신규 공지가 아닌 경우, False을 반환한다.")
    @Test
    fun saveNewNoticesWhenLegacyNotice() {
        // given
        val notice = mock(Notice::class.java)
        val notices = listOf(notice, notice)

        given(notice.isNumberLessThanOrEqualTo(any())).willReturn(true)

        // when
        val result = noticeService.saveNewNotices(notices, 0)

        // then
        assertThat(result).isFalse()
    }

    @DisplayName("가장 최초의 공지인 경우, 공지를 저장하며, 이벤트를 발행한다. 결과는 False를 반환한다.")
    @Test
    fun saveNewNoticesWhenLastNotice() {
        // given
        val notice = mock(Notice::class.java)
        val notices = listOf(notice, notice)

        given(notice.isNumberLessThanOrEqualTo(any())).willReturn(false)
        given(notice.isLastInType()).willReturn(true)

        // when
        val result = noticeService.saveNewNotices(notices, 0)

        // then
        assertThat(result).isFalse()
        verify(noticeWriter).write(notice)
        verify(applicationEventPublisher).publishEvent(notice)
    }


}