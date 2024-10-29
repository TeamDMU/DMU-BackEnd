package com.dmforu.storage.db.mongo.schedule

import com.dmforu.domain.schedule.Schedule
import com.dmforu.domain.schedule.ScheduleRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class ScheduleEntityRepository(
    private val scheduleMongoRepository: ScheduleMongoRepository
) : ScheduleRepository {

    @Transactional
    override fun write(schedules: List<Schedule.Year>) {
        scheduleMongoRepository.deleteAll()
        scheduleMongoRepository.save(ScheduleMapper.mapToEntity(schedules))
    }

    override fun read(): List<Schedule.Year>? {
        return ScheduleMapper.entityToSchedules(scheduleMongoRepository.findAll().firstOrNull())
    }
}