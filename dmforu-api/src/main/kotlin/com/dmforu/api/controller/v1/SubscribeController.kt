package com.dmforu.api.controller.v1

import com.dmforu.api.controller.old.request.RegisterSubscribeRequest
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
) {
    @Operation(summary = "최초 Token 등록 API", description = "애플리케이션 최초 실행시 Token과 학과, 키워드를 등록한다.")
    @PostMapping("/api/v1/subscribe/registration")
    fun tokenSubscribe(@RequestBody registerSubscribeRequest: RegisterSubscribeRequest): ResponseEntity<Void> {
        subscribeWriter.write(registerSubscribeRequest.toSubscribe())
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}

