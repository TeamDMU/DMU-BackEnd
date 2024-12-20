package com.dmforu.api.controller.v1.request

import jakarta.validation.constraints.NotBlank

data class UpdateSubscribeDepartmentRequest(
    @field:NotBlank(message = "토큰은 필수입니다.")
    val token: String,

    @field:NotBlank(message = "학과는 필수입니다.")
    val department: String
)