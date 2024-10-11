package com.dmforu.domain.subscribe

import org.springframework.stereotype.Service

@Service
class OldSubscribeUpdater(
    private val subscribeRepository: OldSubscribeRepository,
) {
    fun subscribeDepartment(token: String, department: String) {
        subscribeRepository.findByIdAndSubscribeDepartment(
            token = token,
            department = department
        )
    }

    fun unsubscribeDepartment(token: String) {
        subscribeRepository.findByIdAndUnsubscribeDepartment(token = token)
    }

    fun subscribeKeywords(token: String, keywords: List<String>) {
        subscribeRepository.findByIdAndSubscribeKeywords(
            token = token,
            keywords = keywords
        )
    }

    fun unsubscribeKeywords(token: String) {
        subscribeRepository.findByIdAndUpdateKeywordUnsubscribe(token = token)
    }
}