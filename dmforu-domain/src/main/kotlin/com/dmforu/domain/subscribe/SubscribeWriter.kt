package com.dmforu.domain.subscribe

class SubscribeWriter(
    private val subscribeRepository: SubscribeRepository,
) {
    fun write(subscribe: Subscribe) {
        subscribeRepository.save(subscribe)
    }
}