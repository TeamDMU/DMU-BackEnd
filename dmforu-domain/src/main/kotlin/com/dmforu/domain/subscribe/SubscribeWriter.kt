package com.dmforu.domain.subscribe

import org.springframework.stereotype.Service

@Service
class SubscribeWriter(
    private val subscribeRepository: SubscribeRepository,
) {
    fun write(subscribe: Subscribe) {
        subscribeRepository.save(subscribe)
    }
}