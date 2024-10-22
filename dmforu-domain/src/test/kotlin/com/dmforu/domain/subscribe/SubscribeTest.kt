package com.dmforu.domain.subscribe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SubscribeTest {

    @DisplayName("구독 정보에서 학과를 변경할 수 있다.")
    @Test
    fun changeDepartment() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "기계공학과",
            keywords = listOf("학사", "봉사"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

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
        val subscribe = Subscribe.of(
            token = "0001",
            department = "기계공학과",
            keywords = listOf("학사", "봉사"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        // when
        subscribe.subscribeDepartment()

        // then
        assertTrue(subscribe.isDepartmentSubscribed)
    }

    @DisplayName("구독 정보에서 학과 알림을 구독 해제할 수 있다.")
    @Test
    fun unsubscribeDepartment() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "기계공학과",
            keywords = listOf("학사", "봉사"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        // when
        subscribe.unsubscribeDepartment()

        // then
        assertFalse(subscribe.isDepartmentSubscribed)
    }

    @DisplayName("구독 정보에서 학과를 변경할 수 있다.")
    @Test
    fun changeKeywords() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "기계공학과",
            keywords = listOf("학사", "봉사"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

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
        val subscribe = Subscribe.of(
            token = "0001",
            department = "기계공학과",
            keywords = listOf("학사", "봉사"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        // when
        subscribe.subscribeKeyword()

        // then
        assertTrue(subscribe.isKeywordSubscribed)
    }

    @DisplayName("구독 정보에서 학과 알림을 구독 해제할 수 있다.")
    @Test
    fun unsubscribeKeyword() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "기계공학과",
            keywords = listOf("학사", "봉사"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        // when
        subscribe.unsubscribeKeyword()

        // then
        assertFalse(subscribe.isKeywordSubscribed)
    }

}