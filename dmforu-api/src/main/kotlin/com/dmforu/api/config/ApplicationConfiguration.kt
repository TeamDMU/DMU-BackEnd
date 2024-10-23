package com.dmforu.api.config

import com.dmforu.domain.subscribe.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration {

    @Bean
    fun subscribeReader(subscribeRepository: SubscribeRepository): SubscribeReader {
        return SubscribeReader(subscribeRepository = subscribeRepository)
    }

    @Bean
    fun subscribeWriter(subscribeRepository: SubscribeRepository): SubscribeWriter {
        return SubscribeWriter(subscribeRepository = subscribeRepository)
    }

    @Bean
    fun subscribeUpdater(subscribeReader: SubscribeReader, subscribeWriter: SubscribeWriter): SubscribeUpdater {
        return SubscribeUpdater(subscribeReader = subscribeReader, subscribeWriter = subscribeWriter)
    }

    @Bean
    fun oldSubscribeUpdater(subscribeReader: SubscribeReader, subscribeWriter: SubscribeWriter): OldSubscribeUpdater {
        return OldSubscribeUpdater(subscribeReader = subscribeReader, subscribeWriter = subscribeWriter)
    }
}