package com.dmforu.fcm

import com.dmforu.admin.message.MessageSender
import com.dmforu.admin.message.NoticeMessage
import com.google.firebase.messaging.FirebaseMessaging

class FirebaseMessageSender(
    private val firebaseMessageConverter: FirebaseMessageConverter,
) : MessageSender {

    override fun sendNoticeMessage(message: NoticeMessage, tokens: List<String>) {

        val firebaseMessage = firebaseMessageConverter.buildMessageToNotice(message, tokens)

        FirebaseMessaging.getInstance().sendEachForMulticast(firebaseMessage)
    }


}