package com.dmforu.storage.db.redis.diet

import com.dmforu.domain.diet.Diet
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash("diet")
internal class DietEntity(
    @Id val id: String = "diet",
    val diets: List<Diet>
)

