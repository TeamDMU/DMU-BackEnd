package com.dmforu.api.controller.old

import com.dmforu.domain.diet.Diet
import com.dmforu.domain.diet.DietReader
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[구버전] 식단")
@RestController
class OldDietController(
    private val dietReader: DietReader,
) {
    @Operation(
        summary = "[구버전] 식단표 API",
        description = "금주의 식단을 출력한다.<br>식단표는 매주 일요일에 갱신된다.<br>만약, 공휴일인 경우는 빈 리스트를 출력한다."
    )
    @GetMapping("/api/v1/dmu/cafeteria")
    fun readDietOld(): ResponseEntity<List<Diet>> {
        return ResponseEntity.ok().body(dietReader.read())
    }
}