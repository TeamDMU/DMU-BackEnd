package com.dmforu.api.controller.v1.request

data class UpdateKeywordSubscribeStatusRequest (
    val token: String,
    val isSubscribed: Boolean
)