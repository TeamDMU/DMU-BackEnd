package com.dmforu.storage.db.redis.schedule

import com.dmforu.domain.schedule.Schedule
import com.dmforu.domain.schedule.ScheduleRepository
import com.dmforu.domain.schedule.legacy.YearSchedule
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
internal class ScheduleEntityRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) : ScheduleRepository {

    private companion object {
        const val SCHEDULE_KEY = "schedule"
        const val OLD_SCHEDULE_KEY = "oldSchedule"
    }

    override fun writeOld(schedules: List<YearSchedule>) {
        writeEntity(OLD_SCHEDULE_KEY, OldScheduleEntity(schedules = schedules))
    }

    override fun write(schedules: List<Schedule.Year>) {
        writeEntity(SCHEDULE_KEY, ScheduleEntity(schedules = schedules))
    }

    override fun readOld(): List<YearSchedule>? {
        return readEntity<OldScheduleEntity>(OLD_SCHEDULE_KEY)?.schedules
    }

    override fun read(): List<Schedule.Year>? {
        return readEntity<ScheduleEntity>(SCHEDULE_KEY)?.schedules
    }

    /**
     * 공통된 엔티티 쓰기 메서드
     */
    private fun <T : Any> writeEntity(key: String, entity: T) {
        redisTemplate.opsForValue().set(key, entity)
    }

    /**
     * 공통된 엔티티 읽기 메서드
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T> readEntity(key: String): T? {
        return redisTemplate.opsForValue().get(key) as? T
    }
}