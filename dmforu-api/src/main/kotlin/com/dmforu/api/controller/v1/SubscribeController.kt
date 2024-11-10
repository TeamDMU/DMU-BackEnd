package com.dmforu.api.controller.v1

import com.dmforu.api.controller.v1.request.*
import com.dmforu.api.support.response.ApiResponse
import com.dmforu.api.support.response.SuccessResponse
import com.dmforu.domain.subscribe.SubscribeUpdater
import com.dmforu.domain.subscribe.SubscribeWriter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "알림 설정")
@RestController
class SubscribeController(
    private val subscribeWriter: SubscribeWriter,
    private val subscribeUpdater: SubscribeUpdater,
) {
    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/api/v1/subscribe/registration")
    fun initSubscribe(@Valid @RequestBody request: RegisterSubscribeRequest): ResponseEntity<ApiResponse<Any>> {
        subscribeWriter.write(request.toSubscribe())
        return ResponseEntity(SuccessResponse.create(), HttpStatus.CREATED)
    }

    @Operation(summary = "키워드 수정 API", description = "알림 키워드를 수정한다.")
    @PatchMapping("/api/v1/subscribe/keywords")
    fun updateSubscribeKeywords(@Valid @RequestBody request: UpdateSubscribeKeywordsRequest): ResponseEntity<ApiResponse<Any>> {
        subscribeUpdater.updateKeywords(token = request.token!!, keywords = request.keywords)
        return ResponseEntity(SuccessResponse.success(), HttpStatus.NO_CONTENT)
    }

    @Operation(summary = "키워드 알림 상태 변경 API", description = "키워드 알림 상태를 변경한다.")
    @PatchMapping("/api/v1/subscribe/keyword/status")
    fun updateSubscribeKeywordStatus(@Valid @RequestBody request: UpdateSubscribeStatusRequest): ResponseEntity<ApiResponse<Any>> {
        subscribeUpdater.updateKeywordSubscribeStatus(
            token = request.token!!,
            keywordSubscribeStatus = request.subscribeStatus!!
        )
        return ResponseEntity(SuccessResponse.success(), HttpStatus.NO_CONTENT)
    }

    @Operation(summary = "학과 수정 API", description = "학과 정보를 수정한다.")
    @PatchMapping("/api/v1/subscribe/department")
    fun updateSubscribeDepartment(@Valid @RequestBody request: UpdateSubscribeDepartmentRequest): ResponseEntity<ApiResponse<Any>> {
        subscribeUpdater.updateDepartment(token = request.token, department = request.department)
        return ResponseEntity(SuccessResponse.success(), HttpStatus.NO_CONTENT)
    }

    @Operation(summary = "학과 알림 상태 변경 API", description = "학과 알림 상태를 변경한다.")
    @PatchMapping("/api/v1/subscribe/department/status")
    fun updateSubscribeDepartmentStatus(@Valid @RequestBody request: UpdateSubscribeStatusRequest): ResponseEntity<ApiResponse<Any>> {
        subscribeUpdater.updateDepartmentSubscribeStatus(
            token = request.token!!,
            departmentSubscribeStatus = request.subscribeStatus!!
        )
        return ResponseEntity(SuccessResponse.success(), HttpStatus.NO_CONTENT)
    }
}

