package com.dmforu.domain.diet

import org.springframework.stereotype.Component

@Component
class DietWriter(
    private val dietRepository: DietRepository
) {
    fun write(diets: List<Diet>) {
        dietRepository.write(diets)
    }
}