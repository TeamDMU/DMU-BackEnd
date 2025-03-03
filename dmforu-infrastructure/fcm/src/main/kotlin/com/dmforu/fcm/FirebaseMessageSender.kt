package com.dmforu.fcm

import com.dmforu.domain.message.MessageSender
import com.dmforu.domain.message.NoticeMessage
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.stereotype.Component

@Component
internal class FirebaseMessageSender(
    private val firebaseMessaging: FirebaseMessaging,
) : MessageSender {

    override fun sendNoticeMessage(message: NoticeMessage, tokens: List<String>) {
        tokens.chunked(500).forEach { chunkedTokens ->
            val firebaseMessage = MulticastMessageMapper.mapToMulticastMessage(message, chunkedTokens)
            firebaseMessaging.sendEachForMulticast(firebaseMessage)
        }
    }

}