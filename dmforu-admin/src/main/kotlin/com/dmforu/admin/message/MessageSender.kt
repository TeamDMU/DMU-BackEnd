package com.dmforu.admin.message

interface MessageSender {
    fun sendNoticeMessage(message: NoticeMessage, tokens: List<String>)
}