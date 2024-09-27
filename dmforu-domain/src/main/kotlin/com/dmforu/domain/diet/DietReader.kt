package com.dmforu.domain.diet

import org.springframework.stereotype.Component

@Component
class DietReader(
    private val dietRepository: DietRepository,
) {
    fun read(): List<Diet> {
        return dietRepository.read().orEmpty()
    }
}