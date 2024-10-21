package com.dmforu.domain.subscribe

interface SubscribeRepository {
    fun save(subscribe: Subscribe)
    fun findByToken(token: String): Subscribe?

    fun findTokensByDepartment(department: String): List<String>
    fun findTokensContainingKeyword(keyword: String): List<String>
}