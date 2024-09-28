package com.dmforu.storage.db.redis.schedule

import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("legacySchedule")
internal class OldScheduleEntity(
    @Id val id: String = "legacySchedule",
    val schedules: List<YearSchedule>
) {
    private constructor() : this("", emptyList())
}