package com.dmforu.domain.subscribe

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SubscribeUpdaterTest {

    @Mock
    lateinit var subscribeReader: SubscribeReader

    @Mock
    lateinit var subscribeWriter: SubscribeWriter

    @InjectMocks
    lateinit var subscribeUpdater: SubscribeUpdater

    @DisplayName("토큰과 키워드로 구독 정보의 키워드를 업데이트할 수 있다.")
    @Test
    fun updateKeywords() {
        // given
        val token = "0001"
        val keywords = listOf("봉사", "장학")
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        subscribeUpdater.updateKeywords(token, keywords)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).changeKeywords(keywords)
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰 정보에 해당하는 구독 정보에서 키워드 구독 상태를 구독으로 변경할 수 있다.")
    @Test
    fun subscribeKeywords() {
        // given
        val token = "0001"
        val keywordSubscribeStatus = true
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        subscribeUpdater.updateKeywordSubscribeStatus(token, keywordSubscribeStatus)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).subscribeKeyword()
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰 정보에 해당하는 구독 정보에서 키워드 구독 상태를 구독 해제로 변경할 수 있다.")
    @Test
    fun unsubscribeKeywords() {
        // given
        val token = "0001"
        val keywordSubscribeStatus = false
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        subscribeUpdater.updateKeywordSubscribeStatus(token, keywordSubscribeStatus)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).unsubscribeKeyword()
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰과 학과명으로 구독 정보의 학과를 업데이트할 수 있다.")
    @Test
    fun updateDepartment() {
        // given
        val token = "0001"
        val department = "컴퓨터소프트웨어공학과"
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        subscribeUpdater.updateDepartment(token, department)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).changeDepartment(department)
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰 정보에 해당하는 구독 정보에서 학과 구독 상태를 구독으로 변경할 수 있다.")
    @Test
    fun subscribeDepartment() {
        // given
        val token = "0001"
        val departmentSubscribeStatus = true
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        subscribeUpdater.updateDepartmentSubscribeStatus(token, departmentSubscribeStatus)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).subscribeDepartment()
        verify(subscribeWriter).write(subscribe)
    }

    @DisplayName("토큰 정보에 해당하는 구독 정보에서 학과 구독 상태를 구독 해제로 변경할 수 있다.")
    @Test
    fun unsubscribeDepartment() {
        // given
        val token = "0001"
        val departmentSubscribeStatus = false
        val subscribe = mock(Subscribe::class.java)

        given(subscribeReader.findById(token)).willReturn(subscribe)

        // when
        subscribeUpdater.updateDepartmentSubscribeStatus(token, departmentSubscribeStatus)

        // then
        verify(subscribeReader).findById(token)
        verify(subscribe).unsubscribeDepartment()
        verify(subscribeWriter).write(subscribe)
    }
}