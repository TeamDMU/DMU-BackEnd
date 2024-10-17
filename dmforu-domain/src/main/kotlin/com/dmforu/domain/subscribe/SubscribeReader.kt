package com.dmforu.domain.subscribe

import org.springframework.stereotype.Service

@Service
class SubscribeReader (
    private val subscribeRepository: SubscribeRepository,
){
    fun getTokensBySubscribedToDepartment(department: String): List<String> {
        return subscribeRepository.findTokensByDepartment(department)
    }

    fun getTokensBySubscribedToKeyword(keyword: String): List<String> {
        return subscribeRepository.findTokensContainingKeyword(keyword)
    }
}