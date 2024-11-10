package com.dmforu.storage.db.mongo.schedule.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "schedules")
internal data class ScheduleEntity(
    val dates: List<String>,
    val content: String
)