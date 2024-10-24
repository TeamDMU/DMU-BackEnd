package com.dmfou.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.storage.db.mysql.subscribe.SubscribeEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SubscribeEntityTest {

    @DisplayName("Subscribe로 SubscribeEntity를 생성할 수 있다.")
    @Test
    fun from() {
        // given
        val token = "0001"
        val department = "컴퓨터소프트웨어공학과"
        val keywords = listOf("학사", "봉사")
        val isDepartmentSubscribed = true
        val isKeywordSubscribed = true

        val subscribe = Subscribe.of(
            token = token,
            department = department,
            keywords = keywords,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = isKeywordSubscribed
        )

        // when
        val subscribeEntity = SubscribeEntity.from(subscribe)

        // then
        assertThat(subscribeEntity.token).isEqualTo(token)
        assertThat(subscribeEntity.department).isEqualTo(department)
        assertThat(subscribeEntity.keywords).isEqualTo(keywords)
        assertThat(subscribeEntity.isDepartmentSubscribed).isEqualTo(isDepartmentSubscribed)
        assertThat(subscribeEntity.isKeywordSubscribed).isEqualTo(isKeywordSubscribed)
    }

    @DisplayName("SubscribeEntity로 Subscribe를 생성할 수 있다.")
    @Test
    fun toSubscribe() {
        // given
        val token = "0001"
        val department = "컴퓨터소프트웨어공학과"
        val keywords = listOf("학사", "봉사")
        val isDepartmentSubscribed = true
        val isKeywordSubscribed = true

        val subscribe = Subscribe.of(
            token = token,
            department = department,
            keywords = keywords,
            isDepartmentSubscribed = isDepartmentSubscribed,
            isKeywordSubscribed = isKeywordSubscribed
        )

        val subscribeEntity = SubscribeEntity.from(subscribe)

        // when
        val savedSubscribe = subscribeEntity.toSubscribe()

        // then
        assertThat(savedSubscribe).isEqualTo(subscribe)
    }

}