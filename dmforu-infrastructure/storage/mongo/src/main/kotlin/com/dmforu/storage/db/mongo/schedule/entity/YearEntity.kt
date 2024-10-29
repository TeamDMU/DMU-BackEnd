package com.dmforu.storage.db.mongo.schedule.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "years")
internal data class YearEntity(
    val year: Int,
    val yearSchedule: List<MonthEntity>
)
