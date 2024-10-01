package com.dmforu.domain.diet

import org.springframework.stereotype.Service

@Service
class DietService(
    private val dietReader: DietReader,
    private val dietWriter: DietWriter
) {
    fun read(): List<Diet> {
        return dietReader.read()
    }

    fun write(diets: List<Diet>) {
        return dietWriter.write(diets)
    }
}
