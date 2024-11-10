package com.dmforu.domain.subscribe

class SubscribeReader (
    private val subscribeRepository: SubscribeRepository,
){
    fun findById(token: String): Subscribe {
        return subscribeRepository.findByToken(token) ?: throw IllegalArgumentException("존재하지 않는 토큰입니다.")
    }

    fun getTokensBySubscribedToDepartment(department: String): List<String> {
        return subscribeRepository.findTokensByDepartment(department)
    }

    fun getTokensBySubscribedToKeyword(keyword: String): List<String> {
        return subscribeRepository.findTokensContainingKeyword(keyword)
    }
}