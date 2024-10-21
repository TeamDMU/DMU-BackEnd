package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.OldSubscribeRepository
import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.domain.subscribe.SubscribeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Repository
internal class SubscribeEntityRepository(
    private val subscribeJpaRepository: SubscribeJpaRepository,
) : SubscribeRepository, OldSubscribeRepository {

    @Transactional
    override fun save(subscribe: Subscribe) {
        subscribeJpaRepository.save(SubscribeEntity.from(subscribe))
    }

    override fun findByToken(token: String): Subscribe? {
        val foundSubscribe = subscribeJpaRepository.findById(token)
        return foundSubscribe.getOrNull()?.toSubscribe()
    }

    @Transactional
    @Deprecated("구버전 호환성을 위해 남겨둔 메서드입니다.")
    override fun findByIdAndSubscribeDepartment(token: String, department: String) {
        val subscribe = findNotNullById(token)

        subscribe.changeDepartment(department)

        subscribe.subscribeDepartment()
    }

    @Transactional
    override fun findByIdAndUpdateDepartmentUnsubscribe(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.unsubscribeDepartment()
    }

    @Transactional
    @Deprecated("구버전 호환성을 위해 남겨둔 메서드입니다.")
    override fun findByIdAndSubscribeKeywords(token: String, keywords: List<String>) {
        val subscribe = findNotNullById(token)

        subscribe.changeKeywords(keywords)

        subscribe.subscribeKeyword()
    }

    @Transactional
    override fun findByIdAndUpdateKeywordUnsubscribe(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.unsubscribeKeyword()
    }

    override fun findTokensByDepartment(department: String): List<String> {
        return subscribeJpaRepository.findTokensByDepartment(department)
    }

    override fun findTokensContainingKeyword(keyword: String): List<String> {
        return subscribeJpaRepository.findTokensContainingKeyword(keyword)
    }

    private fun findNotNullById(token: String): SubscribeEntity {
        return subscribeJpaRepository.findById(token).orElseThrow { throw IllegalArgumentException("존재하지 않은 토큰입니다.") }
    }

}