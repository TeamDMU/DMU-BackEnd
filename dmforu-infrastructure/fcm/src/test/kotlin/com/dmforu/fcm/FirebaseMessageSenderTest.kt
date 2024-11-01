package com.dmforu.fcm

import com.dmforu.domain.message.NoticeMessage
import com.dmforu.domain.notice.Notice
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import java.time.LocalDate

class FirebaseMessageSenderTest {

    private lateinit var firebaseMessaging: FirebaseMessaging

    private lateinit var messageSender: FirebaseMessageSender

    @BeforeEach
    fun setUp() {
        firebaseMessaging = mock()
        messageSender = FirebaseMessageSender(firebaseMessaging)
    }

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