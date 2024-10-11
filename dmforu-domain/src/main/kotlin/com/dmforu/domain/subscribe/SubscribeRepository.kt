package com.dmforu.domain.subscribe

interface SubscribeRepository {
    fun save(subscribe: Subscribe)
    fun findByIdAndUpdateKeywords(token: String, keywords: List<String>)
    fun findByIdAndUpdateKeywordSubscribe(token: String)
    fun findByIdAndUpdateKeywordUnsubscribe(token: String)
}