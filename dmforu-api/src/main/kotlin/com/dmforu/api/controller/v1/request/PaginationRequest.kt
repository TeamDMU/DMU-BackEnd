package com.dmforu.api.controller.v1.request

import jakarta.validation.constraints.Min

data class PaginationRequest(
    @field:Min(value = 1, message = "페이지는 1이상이어야 합니다.")
    val page: Int = 1,

    @field:Min(value = 1, message = "사이즈는 1이상이어야 합니다.")
    val size: Int = 20
)