package com.dmforu.storage.db.mongo.diet

import com.dmforu.domain.diet.Diet
import com.dmforu.storage.db.mongo.diet.entity.DietEntity
import com.dmforu.storage.db.mongo.diet.entity.DietsEntity

internal object DietMapper {

    fun mapToEntity(diets: List<Diet>): DietsEntity {
        return DietsEntity(diets = diets.map { toDietEntity(it) })
    }

    fun entityToDiet(dietEntity: DietsEntity?): List<Diet>? {
        return dietEntity?.diets?.map { toDiet(it) }
    }

    private fun toDietEntity(diet: Diet): DietEntity {
        return DietEntity(date = diet.date, menus = diet.menus)
    }

    private fun toDiet(dietEntity: DietEntity): Diet {
        return Diet.of(date = dietEntity.date, menus = dietEntity.menus)
    }
}