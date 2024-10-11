package com.dmforu.api.controller.v1.request

data class OldKeywordsSubscribeRequest (
    val token: String,
    val topics: List<String>
)