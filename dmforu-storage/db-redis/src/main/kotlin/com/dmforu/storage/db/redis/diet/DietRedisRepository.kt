package com.dmforu.storage.db.redis.diet

import org.springframework.data.repository.CrudRepository

internal interface DietRedisRepository : CrudRepository<DietEntity, String> {}