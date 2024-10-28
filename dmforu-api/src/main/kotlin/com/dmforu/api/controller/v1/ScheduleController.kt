package com.dmforu.api.controller.v1

import com.dmforu.api.support.response.ApiResponse
import com.dmforu.domain.schedule.Schedule
import com.dmforu.domain.schedule.ScheduleReader
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "학사일정")
@RestController
class ScheduleController(
    private val scheduleReader: ScheduleReader,
) {
    @GetMapping("/api/v1/schedule")
    @Operation(
        summary = "학사일정 API",
        description = "현재년도를 기준으로 작년부터 내년 2월까지의 학사일정을 출력한다."
    )
    fun raedSchedule(): ApiResponse<List<Schedule.Year>> {
        return ApiResponse.success(scheduleReader.read())
    }
}