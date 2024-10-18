package com.dmforu.domain.message

interface MessageSender {
    fun sendNoticeMessage(message: NoticeMessage, tokens: List<String>)
}