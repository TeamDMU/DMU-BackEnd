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
    override fun findByIdAndUpdateDepartment(token: String, department: String) {
        val subscribe = findNotNullById(token)

        subscribe.changeDepartment(department)
    }

    @Transactional
    override fun findByIdAndUpdateDepartmentSubscribe(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.subscribeDepartment()
    }

    @Transactional
    @Deprecated("구버전 호환성을 위해 남겨둔 메서드입니다.")
    override fun findByIdAndSubscribeKeywords(token: String, keywords: List<String>) {
        val subscribe = findNotNullById(token)

        subscribe.changeKeywords(keywords)

        subscribe.subscribeKeyword()
    }

    @Transactional
    override fun findByIdAndUpdateKeywords(token: String, keywords: List<String>) {
        val subscribe = findNotNullById(token)

        subscribe.changeKeywords(keywords)
    }

    @Transactional
    override fun findByIdAndUpdateKeywordSubscribe(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.subscribeKeyword()
    }

    @Transactional
    override fun findByIdAndUpdateKeywordUnsubscribe(token: String) {
        val subscribe = findNotNullById(token)

        subscribe.unsubscribeKeyword()
    }

    private fun findNotNullById(token: String): SubscribeEntity {
        return subscribeJpaRepository.findById(token).orElseThrow { throw IllegalArgumentException("존재하지 않은 토큰입니다.") }
    }

}