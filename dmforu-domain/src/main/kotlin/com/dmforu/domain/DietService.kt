package com.dmforu.domain

import org.springframework.stereotype.Service

@Service
class DietService(
    private val dietReader: DietReader
) {
    fun read(): List<Diet> {
        return dietReader.read()
    }
}
