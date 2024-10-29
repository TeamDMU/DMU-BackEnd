package com.dmforu.storage.db.mongo.schedule.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "months")
internal data class MonthEntity(
    val month: Int,
    val monthSchedule: List<ScheduleEntity>,
)
