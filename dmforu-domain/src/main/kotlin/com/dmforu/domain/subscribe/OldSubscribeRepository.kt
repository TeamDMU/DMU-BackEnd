package com.dmforu.domain.subscribe

interface OldSubscribeRepository {
    fun findByIdAndSubscribeDepartment(token: String, department: String)
    fun findByIdAndUpdateDepartmentUnsubscribe(token: String)

    fun findByIdAndSubscribeKeywords(token: String, keywords: List<String>)
    fun findByIdAndUpdateKeywordUnsubscribe(token: String)
}