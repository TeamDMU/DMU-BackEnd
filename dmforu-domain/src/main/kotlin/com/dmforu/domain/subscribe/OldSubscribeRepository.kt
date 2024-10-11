package com.dmforu.domain.subscribe

interface OldSubscribeRepository {
    fun findByIdAndSubscribeDepartment(token: String, department: String)
    fun findByIdAndUnsubscribeDepartment(token: String)
    fun findByIdAndSubscribeKeywords(token: String, keywords: List<String>)
    fun findByIdAndUnsubscribeKeywords(token: String)
}