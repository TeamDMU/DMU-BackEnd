package com.dmforu.storage.db.redis.schedule

import com.dmforu.domain.schedule.Schedule
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("schedule")
internal class ScheduleEntity(
    @Id val id: String = "schedule",
    val schedules: List<Schedule.Year>
)