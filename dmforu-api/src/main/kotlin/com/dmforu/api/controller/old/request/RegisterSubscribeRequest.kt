package com.dmforu.api.controller.old.request

import com.dmforu.domain.subscribe.Subscribe

data class RegisterSubscribeRequest (
    val token: String,
    val department: String,
    val keywords: List<String>,
    val isDepartmentSubscribed: Boolean,
    val areKeywordSubscribed: Boolean,
) {
    fun toSubscribe(): Subscribe {
        return Subscribe(
            token = token,
            department = department,
            keywords = keywords,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = areKeywordSubscribed
        )
    }
}
