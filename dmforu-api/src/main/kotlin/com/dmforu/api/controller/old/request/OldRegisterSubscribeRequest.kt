package com.dmforu.api.controller.old.request

import com.dmforu.domain.subscribe.Subscribe

@Deprecated("구버전 사용자를 위해 남겨둔 DTO 입니다.")
data class OldRegisterSubscribeRequest(
    val token: String,
    val department: String,
    val topic: List<String>
) {
    fun toSubscribe(): Subscribe {
        val isDepartmentSubscribed = department.isNotBlank()
        val areKeywordsSubscribed = topic.isNotEmpty()

        return Subscribe.of(
            token = token,
            department = department,
            keywords = topic,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = areKeywordsSubscribed
        )
    }
}