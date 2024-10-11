package com.dmforu.domain.subscribe

import org.springframework.stereotype.Service

@Service
class SubscribeUpdater(
    private val subscribeRepository: SubscribeRepository,
) {
    fun updateKeywords(token: String, keywords: List<String>) {
        subscribeRepository.findByIdAndUpdateKeywords(token, keywords)
    }

    fun updateKeywordSubscribeStatus(token: String, keywordSubscribeStatus: Boolean) {
        if (keywordSubscribeStatus) {
            subscribeRepository.findByIdAndUpdateKeywordSubscribe(token)
            return
        }

        subscribeRepository.findByIdAndUpdateKeywordUnsubscribe(token)
    }

    fun updateDepartment(token: String, department: String) {
        subscribeRepository.findByIdAndUpdateDepartment(token, department)
    }

    fun updateDepartmentSubscribeStatus(token: String, departmentSubscribeStatus: Boolean) {
        if (departmentSubscribeStatus) {
            subscribeRepository.findByIdAndUpdateDepartmentSubscribe(token)
            return
        }

        subscribeRepository.findByIdAndUpdateDepartmentUnsubscribe(token)
    }
}