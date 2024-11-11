package com.dmforu.api.controller.old

import com.dmforu.api.controller.old.request.OldDepartmentRequest
import com.dmforu.api.controller.old.request.OldKeywordsSubscribeRequest
import com.dmforu.api.controller.old.request.OldRegisterSubscribeRequest
import com.dmforu.api.controller.old.request.OldTokenRequest
import com.dmforu.domain.subscribe.OldSubscribeUpdater
import com.dmforu.domain.subscribe.SubscribeWriter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[구버전] 알림 설정")
@RestController
class OldSubscribeController(
    private val subscribeWriter: SubscribeWriter,
    private val oldSubscribeUpdater: OldSubscribeUpdater,
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
        oldSubscribeUpdater.subscribeDepartment(token = departmentRequest.token, department = departmentRequest.department)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 학사 알림 구독해제", description = "학사 알림을 받지 않도록 설정한다.")
    @PostMapping("/department/v1/dmu/deleteDepartment")
    fun oldDepartmentUnsubscribe(@RequestBody tokenRequest: OldTokenRequest): ResponseEntity<Void> {
        oldSubscribeUpdater.unsubscribeDepartment(token = tokenRequest.token)
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 키워드 알림 구독", description = "키워드 알림을 받도록 설정한다.")
    @PostMapping("/token/v1/dmu/updateTopic")
    fun oldKeywordsSubscribe(@RequestBody keywordsRequest: OldKeywordsSubscribeRequest): ResponseEntity<Void> {
        oldSubscribeUpdater.subscribeKeywords(token = keywordsRequest.token, keywords = keywordsRequest.topics.map { it.korean })
        return ResponseEntity.ok().build()
    }

    @Operation(summary = "[구버전] 키워드 알림 구독해제", description = "키워드 알림을 받지 않도록 설정한다.")
    @PostMapping("/token/v1/dmu/deleteTopic")
    fun oldKeywordsUnsubscribe(@RequestBody tokenRequest: OldTokenRequest): ResponseEntity<Void> {
        oldSubscribeUpdater.unsubscribeKeywords(token = tokenRequest.token)
        return ResponseEntity.ok().build()
    }
}