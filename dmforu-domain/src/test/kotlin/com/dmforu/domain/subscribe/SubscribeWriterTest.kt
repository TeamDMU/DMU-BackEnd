package com.dmforu.domain.subscribe

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SubscribeWriterTest {

    @Mock
    lateinit var subscribeRepository: SubscribeRepository

    @InjectMocks
    lateinit var subscribeWriter: SubscribeWriter

    @DisplayName("구독 정보를 저장할 수 있다.")
    @Test
    fun write() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("학사", "시험"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        // when
        subscribeWriter.write(subscribe)

        // then
        verify(subscribeRepository).save(subscribe)
    }

}