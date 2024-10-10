package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.domain.subscribe.SubscribeRepository
import org.springframework.stereotype.Repository

@Repository
internal class SubscribeEntityRepository(
    private val subscribeJpaRepository: SubscribeJpaRepository,
) : SubscribeRepository {
    override fun save(subscribe: Subscribe) {
        subscribeJpaRepository.save(SubscribeEntity.from(subscribe))
    }

}