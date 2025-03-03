package com.dmforu.admin.presentation.request

data class SendNoticeMessageRequest(
    val token: String,
    val title: String,
    val type: String,
    val body: String,
    val url: String
)
