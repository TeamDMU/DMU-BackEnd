package com.dmforu.api.controller.old.request

import com.dmforu.domain.subscribe.Subscribe

data class OldRegisterSubscribeRequest(
    val token: String,
    val department: String,
    val topic: List<String>
) {
    fun toSubscribe(): Subscribe {
        val isDepartmentSubscribed = department.isNotBlank()
        val areKeywordsSubscribed = topic.isNotEmpty()

        return Subscribe(
            token = token,
            department = department,
            keywords = topic,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = areKeywordsSubscribed
        )
    }
}