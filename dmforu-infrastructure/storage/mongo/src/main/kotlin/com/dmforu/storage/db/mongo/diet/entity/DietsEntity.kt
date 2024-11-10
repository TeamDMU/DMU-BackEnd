package com.dmforu.storage.db.mongo.diet.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "diet_entity")
internal class DietsEntity(
    @Id
    val id: String? = null,
    val diets: List<DietEntity>,
)