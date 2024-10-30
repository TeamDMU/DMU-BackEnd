package com.dmforu.admin.message

import com.dmforu.domain.notice.Notice
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationEventPublisher

@SpringBootTest(classes = [NoticeListener::class])
class NoticeListenerTest {

    @Autowired
    private lateinit var eventPublisher: ApplicationEventPublisher

    @MockBean
    private lateinit var messageService: MessageService

    @DisplayName("notice 이벤트가 발생하면, 푸시 알림을 전송한다.")
    @Test
    fun onNoticeMessageSendEventHandler() {
        // given
        val notice = mock(Notice::class.java)

        // when
        eventPublisher.publishEvent(notice)

        // then
        verify(messageService).sendNoticeMessage(notice)
    }
}