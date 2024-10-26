package com.dmforu.api.controller.v1.request

import jakarta.validation.constraints.NotBlank

data class UpdateSubscribeKeywordsRequest(
    @NotBlank(message = "토큰은 필수입니다.")
    val token: String,

    val keywords: List<String>
)
