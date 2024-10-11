package com.dmforu.domain.subscribe

interface SubscribeRepository {
    fun save(subscribe: Subscribe)

    fun findByIdAndUpdateKeywords(token: String, keywords: List<String>)
    fun findByIdAndUpdateKeywordSubscribe(token: String)
    fun findByIdAndUpdateKeywordUnsubscribe(token: String)

    fun findByIdAndUpdateDepartment(token: String, department: String)
    fun findByIdAndUpdateDepartmentSubscribe(token: String)
    fun findByIdAndUpdateDepartmentUnsubscribe(token: String)
}