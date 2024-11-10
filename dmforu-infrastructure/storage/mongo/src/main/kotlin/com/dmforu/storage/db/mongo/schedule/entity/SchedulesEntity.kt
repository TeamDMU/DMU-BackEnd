package com.dmforu.storage.db.mongo.schedule.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "schedules_entity")
internal data class SchedulesEntity(
    @Id
    val id: String? = null,
    val years: List<YearEntity>,
)
