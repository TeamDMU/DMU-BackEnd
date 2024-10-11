package com.dmforu.api.controller.v1.request

data class UpdateSubscribeKeywordsRequest(
    val token: String,
    val keywords: List<String>
)
