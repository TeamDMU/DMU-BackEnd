package com.dmforu.storage.db.mongo.diet.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "diet")
internal class DietEntity(
    val date: LocalDate,
    val menus: List<String>,
)