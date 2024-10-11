package com.dmforu.api.controller.v1

import com.dmforu.api.controller.v1.request.*
import com.dmforu.domain.subscribe.OldSubscribeUpdater
import com.dmforu.domain.subscribe.SubscribeWriter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "알림 설정")
@RestController
class SubscribeController(
    private val subscribeWriter: SubscribeWriter,
    private val subscribeUpdater: OldSubscribeUpdater,
) {
    @Operation(summary = "[구버전] 최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/token/v1/dmu/initToken")
    fun oldTokenSubscribe(@RequestBody oldRegisterSubscribeRequest: OldRegisterSubscribeRequest): ResponseEntity<Void> {
        subscribeWriter.write(oldRegisterSubscribeRequest.toSubscribe())
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 학사 알림 구독", description = "학사 알림을 받도록 설정한다.")
    @PostMapping("/department/v1/dmu/updateDepartment")
    fun oldDepartmentSubscribe(@RequestBody departmentRequest: OldDepartmentRequest): ResponseEntity<Void> {
        subscribeUpdater.subscribeDepartment(token = departmentRequest.token, department = departmentRequest.department)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 학사 알림 구독해제", description = "학사 알림을 받지 않도록 설정한다.")
    @PostMapping("/department/v1/dmu/deleteDepartment")
    fun oldDepartmentUnsubscribe(@RequestBody tokenRequest: OldTokenRequest): ResponseEntity<Void> {
        subscribeUpdater.unsubscribeDepartment(token = tokenRequest.token)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 키워드 알림 구독", description = "키워드 알림을 받도록 설정한다.")
    @PostMapping("/token/v1/dmu/updateTopic")
    fun oldKeywordsSubscribe(@RequestBody keywordsRequest: OldKeywordsSubscribeRequest): ResponseEntity<Void> {
        subscribeUpdater.subscribeKeywords(token = keywordsRequest.token, keywords = keywordsRequest.topics)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 키워드 알림 구독해제", description = "키워드 알림을 받지 않도록 설정한다.")
    @PostMapping("/token/v1/dmu/deleteTopic")
    fun oldKeywordsUnsubscribe(@RequestBody tokenRequest: OldTokenRequest): ResponseEntity<Void> {
        subscribeUpdater.unsubscribeKeywords(token = tokenRequest.token)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/api/v1/subscribe/registration")
    fun tokenSubscribe(@RequestBody registerSubscribeRequest: RegisterSubscribeRequest): ResponseEntity<Void> {
        subscribeWriter.write(registerSubscribeRequest.toSubscribe())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}
