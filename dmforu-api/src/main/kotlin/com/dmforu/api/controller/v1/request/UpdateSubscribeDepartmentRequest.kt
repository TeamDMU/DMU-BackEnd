package com.dmforu.api.controller.v1.request

data class UpdateSubscribeDepartmentRequest (
    val token: String,
    val department: String
)