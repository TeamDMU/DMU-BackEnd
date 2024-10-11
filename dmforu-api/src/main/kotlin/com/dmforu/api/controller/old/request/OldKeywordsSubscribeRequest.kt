package com.dmforu.api.controller.old.request

data class OldKeywordsSubscribeRequest (
    val token: String,
    val topics: List<String>
)