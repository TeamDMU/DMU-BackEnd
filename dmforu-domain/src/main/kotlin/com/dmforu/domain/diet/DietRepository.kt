package com.dmforu.domain.diet

interface DietRepository {
    fun write(diets: List<Diet>)
    fun read(): List<Diet>?
}