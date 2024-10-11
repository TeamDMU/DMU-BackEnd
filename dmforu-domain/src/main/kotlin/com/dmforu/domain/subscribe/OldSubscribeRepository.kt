package com.dmforu.domain.subscribe

interface OldSubscribeRepository {
    fun findByIdAndSubscribeDepartment(token: String, department: String)
    fun findByIdAndSubscribeKeywords(token: String, keywords: List<String>)
    fun findByIdAndUnsubscribeDepartment(token: String)
    fun findByIdAndUpdateKeywordUnsubscribe(token: String)
}