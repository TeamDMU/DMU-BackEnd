package com.dmforu.domain.subscribe

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class SubscribeReaderTest {

    @Mock
    lateinit var subscribeRepository: SubscribeRepository

    @InjectMocks
    lateinit var subscribeReader: SubscribeReader

    @DisplayName("토큰을 사용해서 구독 정보를 불러올 수 있다.")
    @Test
    fun findById() {
        // given
        val token = "validToken"
        val expectedSubscribe = Subscribe.of(
            token = token,
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("학사", "시험"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        `when`(subscribeRepository.findByToken(token)).thenReturn(expectedSubscribe)

        // when
        val actualSubscribe = subscribeReader.findById(token)

        // then
        assertEquals(expectedSubscribe, actualSubscribe)
        verify(subscribeRepository).findByToken(token)
    }

    @DisplayName("잘못된 토큰을 사용해서 구독 정보를 불러오는 경우 예외가 발생한다.")
    @Test
    fun findByIdThrowException() {
        // given
        val token = "invalidToken"
        `when`(subscribeRepository.findByToken(token)).thenReturn(null)

        // when // then
        assertThatThrownBy { subscribeReader.findById(token) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("존재하지 않는 토큰입니다.")
    }

    @DisplayName("학과명에 해당하는 모든 토큰을 불러올 수 있다.")
    @Test
    fun getTokensBySubscribedToDepartment() {
        // given
        val department = "컴퓨터소프트웨어공학과"
        val tokens = listOf("0001", "0002")
        `when`(subscribeRepository.findTokensByDepartment(department)).thenReturn(tokens)

        // when
        val result = subscribeReader.getTokensBySubscribedToDepartment(department)

        // then
        assertEquals(tokens, result)
        verify(subscribeRepository).findTokensByDepartment(department)
    }

    @DisplayName("해당하는 키워드를 가지고 있는 모든 토큰을 불러올 수 있다.")
    @Test
    fun getTokensBySubscribedToKeyword() {
        // given
        val keyword = "학사"
        val tokens = listOf("0001", "0002")
        `when`(subscribeRepository.findTokensContainingKeyword(keyword)).thenReturn(tokens)

        // when
        val result = subscribeReader.getTokensBySubscribedToKeyword(keyword)

        // then
        assertEquals(tokens, result)
        verify(subscribeRepository).findTokensContainingKeyword(keyword)
    }

}