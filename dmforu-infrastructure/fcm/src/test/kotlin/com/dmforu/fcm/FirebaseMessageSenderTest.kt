package com.dmforu.fcm

import com.dmforu.domain.message.NoticeMessage
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.eq

@ExtendWith(MockitoExtension::class)
class FirebaseMessageSenderTest {

    @Mock
    private lateinit var firebaseMessageConverter: FirebaseMessageConverter

    @Mock
    private lateinit var firebaseMessaging: FirebaseMessaging

    @InjectMocks
    private lateinit var messageSender: FirebaseMessageSender

    @DisplayName("공지를 푸시 알림으로 전송한다.")
    @Test
    fun sendNoticeMessage() {
        // given
        val message = mock(NoticeMessage::class.java)
        val multicastMessage = mock(MulticastMessage::class.java)
        val tokens = listOf("토큰1", "토큰2")

        given(firebaseMessageConverter.buildMessageToNotice(eq(message), eq(tokens))).willReturn(multicastMessage)

        // when
        messageSender.sendNoticeMessage(message, tokens)

        // then
        verify(firebaseMessageConverter).buildMessageToNotice(message, tokens)
        verify(firebaseMessaging).sendEachForMulticast(multicastMessage)
    }
}