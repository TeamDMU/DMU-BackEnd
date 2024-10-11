package com.dmforu.api.controller.v1.request

data class UpdateSubscribeStatusRequest (
    val token: String,
    val isSubscribed: Boolean
)