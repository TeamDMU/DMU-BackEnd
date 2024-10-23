package com.dmforu.domain.diet

class DietWriter(
    private val dietRepository: DietRepository
) {
    fun overwrite(diets: List<Diet>) {
        dietRepository.write(diets)
    }
}