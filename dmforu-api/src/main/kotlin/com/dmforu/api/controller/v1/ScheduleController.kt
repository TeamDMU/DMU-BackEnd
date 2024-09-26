package com.dmforu.api.controller.v1

import com.dmforu.domain.schedule.Schedule
import com.dmforu.domain.schedule.ScheduleService
import com.dmforu.domain.schedule.legacy.YearSchedule
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "학사일정")
@RestController
class ScheduleController(
    private val scheduleService: ScheduleService
) {
    @GetMapping("/api/v1/dmu/schedule")
    @Operation(
        summary = "[구버전] 학사일정 API",
        description = "현재년도를 기준으로 작년부터 내년 2월까지의 학사일정을 출력한다."
    )
    fun readScheduleOld(): ResponseEntity<List<YearSchedule>> {
        return ResponseEntity.ok().body(scheduleService.readOld())
    }

    @GetMapping("/api/v1/schedule")
    @Operation(
        summary = "학사일정 API",
        description = "현재년도를 기준으로 작년부터 내년 2월까지의 학사일정을 출력한다."
    )
    fun raedSchedule(): ResponseEntity<List<Schedule.Year>> {
        return ResponseEntity.ok().body(scheduleService.read())
    }
}