package com.dmforu.api.controller.v1.request

import com.dmforu.domain.subscribe.Subscribe
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class RegisterSubscribeRequest(
    @NotBlank(message = "토큰은 필수입니다.")
    val token: String,

    @NotBlank(message = "학과는 필수입니다.")
    val department: String,

    val keywords: List<String>,

    @NotNull(message = "학과 구독 상태는 필수입니다.")
    val departmentSubscribeStatus: Boolean,

    @NotNull(message = "키워드 구독 상태는 필수입니다.")
    val keywordSubscribeStatus: Boolean,
) {
    fun toSubscribe(): Subscribe {
        return Subscribe.of(
            token = token,
            department = department,
            keywords = keywords,
            isDepartmentSubscribed = departmentSubscribeStatus,
            isKeywordSubscribed = keywordSubscribeStatus
        )
    }
}
