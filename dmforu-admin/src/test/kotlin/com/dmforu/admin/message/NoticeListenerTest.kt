package com.dmforu.admin.message

import com.dmforu.domain.notice.Notice
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.ApplicationContext
import java.time.LocalDate

@SpringBootTest(classes = [NoticeListener::class])
class NoticeListenerTest {

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @MockBean
    private lateinit var noticeListener: NoticeListener

    @DisplayName("notice 이벤트가 발생하면, 푸시 알림을 전송한다.")
    @Test
    fun onNoticeMessageSendEventHandler() {
        // given
        val notice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.of(2024, 10, 1),
            title = "제목",
            author = "작성자",
            url = "http://test.com",
        )

        // when
        applicationContext.publishEvent(notice)

        // then
        verify(noticeListener).onNoticeMessageSendEventHandler(notice)
    }
}