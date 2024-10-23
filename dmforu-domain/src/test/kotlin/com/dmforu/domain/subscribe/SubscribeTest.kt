package com.dmforu.domain.subscribe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SubscribeTest {

    @DisplayName("구독 정보를 생성할 수 있다.")
    @Test
    fun of() {
        //given
        val token = "0001"
        val department = "컴퓨터소프트웨어공학과"
        val keywords = listOf("학사", "봉사")
        val isDepartmentSubscribed = true
        val isKeywordSubscribed = true

        // when
        val subscribe = createSubscribe(token, department, keywords, isDepartmentSubscribed, isKeywordSubscribed)

        // then
        assertThat(subscribe.token).isEqualTo(token)
        assertThat(subscribe.department).isEqualTo(department)
        assertThat(subscribe.keywords).isEqualTo(keywords)
        assertThat(subscribe.isDepartmentSubscribed).isEqualTo(isDepartmentSubscribed)
        assertThat(subscribe.isKeywordSubscribed).isEqualTo(isKeywordSubscribed)
    }

    @DisplayName("구독 정보에서 학과를 변경할 수 있다.")
    @Test
    fun changeDepartment() {
        // given
        val subscribe = createSubscribe()

        val changedDepartment = "컴퓨터소프트웨어공학과"

        // when
        subscribe.changeDepartment(changedDepartment)

        // then
        assertThat(subscribe.department).isEqualTo(changedDepartment)
    }

    @DisplayName("구독 정보에서 학과 알림을 구독할 수 있다.")
    @Test
    fun subscribeDepartment() {
        // given
        val subscribe = createSubscribe()

        // when
        subscribe.subscribeDepartment()

        // then
        assertTrue(subscribe.isDepartmentSubscribed)
    }

    @DisplayName("구독 정보에서 학과 알림을 구독 해제할 수 있다.")
    @Test
    fun unsubscribeDepartment() {
        // given
        val subscribe = createSubscribe()

        // when
        subscribe.unsubscribeDepartment()

        // then
        assertFalse(subscribe.isDepartmentSubscribed)
    }

    @DisplayName("구독 정보에서 학과를 변경할 수 있다.")
    @Test
    fun changeKeywords() {
        // given
        val subscribe = createSubscribe()

        val changedKeywords = listOf("장학", "등록")

        // when
        subscribe.changeKeywords(changedKeywords)

        // then
        assertThat(subscribe.keywords).isEqualTo(changedKeywords)
    }

    @DisplayName("구독 정보에서 학과 알림을 구독할 수 있다.")
    @Test
    fun subscribeKeyword() {
        // given
        val subscribe = createSubscribe()

        // when
        subscribe.subscribeKeyword()

        // then
        assertTrue(subscribe.isKeywordSubscribed)
    }

    @DisplayName("구독 정보에서 학과 알림을 구독 해제할 수 있다.")
    @Test
    fun unsubscribeKeyword() {
        // given
        val subscribe = createSubscribe()

        // when
        subscribe.unsubscribeKeyword()

        // then
        assertFalse(subscribe.isKeywordSubscribed)
    }

    private fun createSubscribe(
        token: String = "0001",
        department: String = "컴퓨터소프트웨어공학과",
        keywords: List<String> = listOf("학사", "봉사"),
        isDepartmentSubscribed: Boolean = true,
        isKeywordSubscribed: Boolean = true,
    ) = Subscribe.of(
        token = token,
        department = department,
        keywords = keywords,
        isDepartmentSubscribed = isDepartmentSubscribed,
        isKeywordSubscribed = isKeywordSubscribed,
    )
}