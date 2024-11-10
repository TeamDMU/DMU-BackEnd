package com.dmforu.api.controller.old

import com.dmforu.api.controller.old.response.LegacySchedule
import com.dmforu.domain.schedule.ScheduleReader
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "[구버전] 학사일정")
@RestController
class OldScheduleController(
    private val scheduleReader: ScheduleReader,
) {
    @GetMapping("/api/v1/dmu/schedule")
    @Operation(
        summary = "[구버전] 학사일정 API",
        description = "현재년도를 기준으로 작년부터 내년 2월까지의 학사일정을 출력한다."
    )
    fun readScheduleOld(): ResponseEntity<List<LegacySchedule.YearSchedule>> {
        val schedules = LegacySchedule.fromScheduleYears(scheduleReader.read())
        return ResponseEntity.ok().body(schedules)
    }
}