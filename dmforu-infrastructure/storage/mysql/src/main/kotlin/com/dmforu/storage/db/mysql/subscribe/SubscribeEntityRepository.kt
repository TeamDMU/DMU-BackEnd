package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.domain.subscribe.SubscribeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
internal class SubscribeEntityRepository(
    private val subscribeJpaRepository: SubscribeJpaRepository,
) : SubscribeRepository {

    @Transactional
    override fun save(subscribe: Subscribe) {
        subscribeJpaRepository.save(SubscribeEntity.from(subscribe))
    }

    override fun findByToken(token: String): Subscribe? {
        val foundSubscribe = subscribeJpaRepository.findById(token)
        return foundSubscribe.getOrNull()?.toSubscribe()
    }

    override fun findTokensByDepartment(department: String): List<String> {
        return subscribeJpaRepository.findTokensByDepartment(department)
    }

    override fun findTokensContainingKeyword(keyword: String): List<String> {
        return subscribeJpaRepository.findTokensContainingKeyword(keyword)
    }

}