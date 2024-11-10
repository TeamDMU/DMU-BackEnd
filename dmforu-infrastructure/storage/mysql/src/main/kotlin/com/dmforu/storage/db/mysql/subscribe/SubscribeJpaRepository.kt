package com.dmforu.storage.db.mysql.subscribe

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

internal interface SubscribeJpaRepository : JpaRepository<SubscribeEntity, String> {

    @Query("SELECT t.token FROM SubscribeEntity t WHERE t.isDepartmentSubscribed = true AND t.department = :department")
    fun findTokensByDepartment(@Param("department") department: String): List<String>

    @Query(
        value = "SELECT token.token FROM token WHERE keyword_onoff = true AND keywords_list LIKE %:keyword%",
        nativeQuery = true
    )
    fun findTokensContainingKeyword(@Param("keyword") keyword: String): List<String>
}