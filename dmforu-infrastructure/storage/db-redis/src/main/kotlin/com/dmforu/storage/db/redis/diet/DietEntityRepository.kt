package com.dmforu.storage.db.redis.diet

import com.dmforu.domain.diet.Diet
import com.dmforu.domain.diet.DietRepository
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
internal class DietEntityRepository(
    private val redisTemplate: RedisTemplate<String, Any>
) : DietRepository {
    private companion object {
        const val DIET_KEY = "diet"
    }

    override fun write(diets: List<Diet>) {
        writeEntity(DIET_KEY, DietEntity(diets = diets))
    }

    override fun read(): List<Diet>? {
        return readEntity<DietEntity>(DIET_KEY)?.diets
    }

    /**
     * 공통된 엔티티 쓰기 메서드
     */
    private fun <T : Any> writeEntity(key: String, entity: T) {
        redisTemplate.opsForValue().set(key, entity)
    }

    /**
     * 공통된 엔티티 읽기 메서드
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T> readEntity(key: String): T? {
        return redisTemplate.opsForValue().get(key) as? T
    }
}