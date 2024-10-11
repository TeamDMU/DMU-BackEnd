package com.dmforu.api.controller.v1

import com.dmforu.api.controller.old.request.RegisterSubscribeRequest
import com.dmforu.api.controller.v1.request.UpdateKeywordSubscribeStatusRequest
import com.dmforu.api.controller.v1.request.UpdateSubscribeKeywordsRequest
import com.dmforu.domain.subscribe.SubscribeUpdater
import com.dmforu.domain.subscribe.SubscribeWriter
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
    private val subscribeUpdater: SubscribeUpdater
) {
    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/api/v1/subscribe/registration")
    fun tokenSubscribe(@RequestBody registerSubscribeRequest: RegisterSubscribeRequest): ResponseEntity<Void> {
        subscribeWriter.write(registerSubscribeRequest.toSubscribe())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @Operation(summary = "키워드 수정 API", description = "알림 키워드를 수정한다.")
    @PatchMapping("/api/v1/subscribe/keywords")
    fun updateSubscribeKeywords(@RequestBody request: UpdateSubscribeKeywordsRequest): ResponseEntity<Void> {
        subscribeUpdater.updateKeywords(request.token, request.keywords)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @Operation(summary = "키워드 알림 상태 API", description = "키워드 알림 상태를 수정한다.")
    @PatchMapping("/api/v1/subscribe/keyword/status")
    fun updateSubscribeKeywordStatus(@RequestBody request: UpdateKeywordSubscribeStatusRequest): ResponseEntity<Void> {
        subscribeUpdater.updateKeywordSubscribeStatus(request.token, request.isSubscribed)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}

