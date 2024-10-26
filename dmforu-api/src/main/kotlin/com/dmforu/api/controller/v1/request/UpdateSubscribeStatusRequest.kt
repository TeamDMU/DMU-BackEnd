package com.dmforu.api.controller.v1.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateSubscribeStatusRequest (
    @NotBlank(message = "토큰은 필수입니다.")
    val token: String,

    @NotNull(message = "구독 상태는 필수입니다.")
    val subscribeStatus: Boolean
)