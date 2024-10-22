package com.dmforu.domain.subscribe

import org.springframework.stereotype.Service

@Service
class SubscribeUpdater(
    private val subscribeReader: SubscribeReader,
    private val subscribeWriter: SubscribeWriter,
) {
    fun updateKeywords(token: String, keywords: List<String>) {
        val subscribe = subscribeReader.findById(token)

        subscribe.changeKeywords(keywords)

        subscribeWriter.write(subscribe)
    }

    fun updateKeywordSubscribeStatus(token: String, keywordSubscribeStatus: Boolean) {
        if (keywordSubscribeStatus) {
            val subscribe = subscribeReader.findById(token)

            subscribe.subscribeKeyword()

            subscribeWriter.write(subscribe)

            return
        }

        val subscribe = subscribeReader.findById(token)

        subscribe.unsubscribeKeyword()

        subscribeWriter.write(subscribe)
    }

    fun updateDepartment(token: String, department: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.changeDepartment(department)

        subscribeWriter.write(subscribe)
    }

    fun updateDepartmentSubscribeStatus(token: String, departmentSubscribeStatus: Boolean) {
        if (departmentSubscribeStatus) {
            val subscribe = subscribeReader.findById(token)

            subscribe.subscribeDepartment()

            subscribeWriter.write(subscribe)

            return
        }

        val subscribe = subscribeReader.findById(token)

        subscribe.unsubscribeDepartment()

        subscribeWriter.write(subscribe)
    }
}