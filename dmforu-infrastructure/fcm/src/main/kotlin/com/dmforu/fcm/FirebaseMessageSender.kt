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

        val firebaseMessage = MulticastMessageMapper.mapToMulticastMessage(message, tokens)

        firebaseMessaging.sendEachForMulticast(firebaseMessage)
    }

}