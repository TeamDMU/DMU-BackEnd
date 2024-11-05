package com.dmforu.fcm

import com.dmforu.domain.message.NoticeMessage
import com.dmforu.domain.notice.Notice
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class FirebaseMessageSenderTest {

    @Mock
    private lateinit var firebaseMessaging: FirebaseMessaging

    @InjectMocks
    private lateinit var messageSender: FirebaseMessageSender

    @DisplayName("공지를 푸시 알림으로 전송한다.")
    @Test
    fun sendNoticeMessage() {
        // given
        val notice = Notice.of(
            number = 1,
            type = "대학",
            date = LocalDate.now(),
            title = "공지사항입니다.",
            author = "관리자",
            url = "https://www.test.com"
        )
        val message = NoticeMessage.createDepartmentNoticeMessage(notice)
        val tokens = listOf("토큰1", "토큰2")

        // when
        messageSender.sendNoticeMessage(message, tokens)

        // then
        verify(firebaseMessaging).sendEachForMulticast(any())
    }
}