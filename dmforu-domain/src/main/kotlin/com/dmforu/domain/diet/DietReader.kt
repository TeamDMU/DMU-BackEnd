package com.dmforu.domain.diet

class DietReader(
    private val dietRepository: DietRepository,
) {
    // TODO
    fun read(): List<Diet> {
        return dietRepository.read() ?: throw IllegalArgumentException("식단 정보가 존재하지 않습니다. 관리자 에러")
    }
}