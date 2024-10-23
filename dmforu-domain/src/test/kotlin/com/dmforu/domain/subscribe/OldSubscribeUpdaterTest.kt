package com.dmforu.domain.subscribe

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class OldSubscribeUpdaterTest {

    @Mock
    private lateinit var subscribeReader: SubscribeReader

    @Mock
    private lateinit var subscribeWriter: SubscribeWriter

    @InjectMocks
    private lateinit var oldSubscribeUpdater: OldSubscribeUpdater

    @DisplayName("토큰과 학과를 가지고, 해당 학과로 알림 구독을 할 수 있다.")
    @Test
    fun subscribeDepartment() {
        // given
        val token = "0001"
        val department = "컴퓨터소프트웨어공학과"
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        oldSubscribeUpdater.subscribeDepartment(token, department)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).changeDepartment(department)
        verify(subscribe).subscribeDepartment()
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰에 해당하는 구독 정보에서, 학과 알림 구독을 해제할 수 있다.")
    @Test
    fun unsubscribeDepartment() {
        // given
        val token = "0001"
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        oldSubscribeUpdater.unsubscribeDepartment(token)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).unsubscribeDepartment()
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰과 키워드를 가지고, 해당 키워드 알림 구독을 할 수 있다.")
    @Test
    fun subscribeKeywords() {
        // given
        val token = "0001"
        val keywords = listOf("봉사", "장학")
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        oldSubscribeUpdater.subscribeKeywords(token = token, keywords = keywords)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).changeKeywords(keywords)
        verify(subscribe).subscribeKeyword()
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰에 해당하는 구독 정보에서, 학과 알림 구독을 해제할 수 있다.")
    @Test
    fun unsubscribeKeywords() {
        // given
        val token = "0001"
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        oldSubscribeUpdater.unsubscribeKeywords(token)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).unsubscribeKeyword()
        verify(subscribeWriter).write(subscribe)
    }

}