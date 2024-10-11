package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.OldSubscribeRepository
import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.domain.subscribe.SubscribeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository

@Repository
internal class SubscribeEntityRepository(
    private val subscribeJpaRepository: SubscribeJpaRepository,
) : SubscribeRepository, OldSubscribeRepository {
    override fun save(subscribe: Subscribe) {
        subscribeJpaRepository.save(SubscribeEntity.from(subscribe))
    }

    @Transactional
    override fun findByIdAndSubscribeDepartment(token: String, department: String) {
        val subscribe = findNotNullById(token)

        subscribe.changeDepartment(department)

        subscribe.subscribeDepartment()
    }

    @Transactional
    override fun findByIdAndUnsubscribeDepartment(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.unsubscribeDepartment()
    }

    @Transactional
    override fun findByIdAndSubscribeKeywords(token: String, keywords: List<String>) {
        val subscribe = findNotNullById(token)

        subscribe.changeKeywords(keywords)

        subscribe.subscribeKeywords()
    }

    @Transactional
    override fun findByIdAndUnsubscribeKeywords(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.unsubscribeKeywords()
    }

    private fun findNotNullById(token: String): SubscribeEntity {
        return subscribeJpaRepository.findById(token).orElseThrow { throw IllegalArgumentException("존재하지 않은 토큰입니다.") }
    }

}