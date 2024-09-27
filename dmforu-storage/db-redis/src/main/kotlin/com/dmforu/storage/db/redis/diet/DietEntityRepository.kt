package com.dmforu.storage.db.redis.diet

import com.dmforu.domain.diet.Diet
import com.dmforu.domain.diet.DietRepository
import org.springframework.stereotype.Repository

@Repository
internal class DietEntityRepository(
    private val dietRedisRepository: DietRedisRepository
) : DietRepository {
    override fun write(diets: List<Diet>) {
        // 기존 데이터를 모두 삭제
        dietRedisRepository.deleteAll()
        // 새로운 데이터를 저장
        dietRedisRepository.save(DietEntity(diets = diets))
    }

    override fun read(): List<Diet>? {
        return dietRedisRepository.findAll().firstOrNull()?.diets
    }
}