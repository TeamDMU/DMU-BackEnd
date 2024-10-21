package com.dmforu.fcm

import com.dmforu.domain.message.MessageSender
import com.dmforu.domain.message.NoticeMessage
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.stereotype.Component

@Component
internal class FirebaseMessageSender(
    private val firebaseMessageConverter: FirebaseMessageConverter,
) : MessageSender {

    override fun sendNoticeMessage(message: NoticeMessage, tokens: List<String>) {

        val firebaseMessage = firebaseMessageConverter.buildMessageToNotice(message, tokens)
        val firebaseApp = FirebaseApp.getInstance("DMFORU_APP_PROD")

        FirebaseMessaging.getInstance(firebaseApp).sendEachForMulticast(firebaseMessage)
    }


}