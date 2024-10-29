package com.dmforu.storage.db.mongo.schedule

import com.dmforu.storage.db.mongo.schedule.entity.SchedulesEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
internal interface ScheduleMongoRepository : MongoRepository<SchedulesEntity, String> {}