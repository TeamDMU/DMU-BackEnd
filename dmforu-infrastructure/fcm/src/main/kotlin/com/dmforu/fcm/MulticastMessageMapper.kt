package com.dmforu.fcm

import com.dmforu.domain.message.NoticeMessage
import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification

internal object MulticastMessageMapper {

    fun mapToMulticastMessage(message: NoticeMessage, tokens: List<String>): MulticastMessage {
        val notification = Notification.builder()
            .setTitle(message.title)
            .setBody(message.body)
            .build()

        return MulticastMessage.builder()
            .setNotification(notification)
            .addAllTokens(tokens)
            .putData("title", message.title)
            .putData("body", message.body)
            .putData("url", message.url)
            .putData("type", message.type)
            .build()
    }

}