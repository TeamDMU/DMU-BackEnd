package com.dmforu.domain.subscribe

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
            subscribeToKeywords(token)
            return
        }

        unsubscribeToKeywords(token)
    }

    fun updateDepartment(token: String, department: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.changeDepartment(department)

        subscribeWriter.write(subscribe)
    }

    fun updateDepartmentSubscribeStatus(token: String, departmentSubscribeStatus: Boolean) {
        if (departmentSubscribeStatus) {
            subscribeToDepartment(token)
            return
        }

        unsubscribeToDepartment(token)
    }

    private fun subscribeToKeywords(token: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.subscribeKeyword()

        subscribeWriter.write(subscribe)
    }

    private fun unsubscribeToKeywords(token: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.unsubscribeKeyword()

        subscribeWriter.write(subscribe)
    }

    private fun subscribeToDepartment(token: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.subscribeDepartment()

        subscribeWriter.write(subscribe)
    }

    private fun unsubscribeToDepartment(token: String) {
        val subscribe = subscribeReader.findById(token)

        subscribe.unsubscribeDepartment()

        subscribeWriter.write(subscribe)
    }
}
