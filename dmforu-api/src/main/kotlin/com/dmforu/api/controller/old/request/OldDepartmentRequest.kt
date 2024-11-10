package com.dmforu.api.controller.old.request

@Deprecated("구버전 사용자를 위해 남겨둔 DTO 입니다.")
data class OldDepartmentRequest(
    val token: String,
    val department: String,
)