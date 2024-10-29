package com.dmforu.storage.db.mongo.diet

import com.dmforu.domain.diet.Diet
import com.dmforu.domain.diet.DietRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
internal class DietEntityRepository(
    private val dietRepository: DietMongoRepository,
) : DietRepository {

    @Transactional
    override fun write(diets: List<Diet>) {
        dietRepository.deleteAll()
        dietRepository.save(DietMapper.mapToEntity(diets))
    }

    override fun read(): List<Diet>? {
        return DietMapper.entityToDiet(dietRepository.findAll().firstOrNull())
    }
}