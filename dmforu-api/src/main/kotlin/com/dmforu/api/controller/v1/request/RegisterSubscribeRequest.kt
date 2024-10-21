package com.dmforu.api.controller.v1.request

import com.dmforu.domain.subscribe.Subscribe

data class RegisterSubscribeRequest (
    val token: String,
    val department: String,
    val keywords: List<String>,
    val isDepartmentSubscribed: Boolean,
    val areKeywordSubscribed: Boolean,
) {
    fun toSubscribe(): Subscribe {
        return Subscribe.of(
            token = token,
            department = department,
            keywords = keywords,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = areKeywordSubscribed
        )
    }
}
