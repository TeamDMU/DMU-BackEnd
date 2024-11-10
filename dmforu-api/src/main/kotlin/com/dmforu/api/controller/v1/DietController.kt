package com.dmforu.api.controller.v1

import com.dmforu.api.support.response.ApiResponse
import com.dmforu.api.support.response.SuccessResponse
import com.dmforu.domain.diet.Diet
import com.dmforu.domain.diet.DietReader
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "식단")
@RestController
class DietController(
    private val dietReader: DietReader,
) {
    @Operation(
        summary = "식단표 API",
        description = "금주의 식단을 출력한다.<br>식단표는 매주 일요일에 갱신된다.<br>만약, 공휴일인 경우는 빈 리스트를 출력한다."
    )
    @GetMapping("/api/v1/cafeteria")
    fun readDiet(): ApiResponse<List<Diet>> {
        return SuccessResponse.success(dietReader.read())
    }
}