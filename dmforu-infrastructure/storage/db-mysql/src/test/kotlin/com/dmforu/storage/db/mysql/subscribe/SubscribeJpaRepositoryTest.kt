package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.storage.db.mysql.config.MysqlJpaConfig
import com.dmforu.storage.db.mysql.subscribe.SubscribeEntity
import com.dmforu.storage.db.mysql.subscribe.SubscribeJpaRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(MysqlJpaConfig::class)
class SubscribeJpaRepositoryTest {

    @Autowired
    private lateinit var subscribeRepository: SubscribeJpaRepository

    @DisplayName("해당 학과 알림 구독이 되어있는 토큰을 불러온다.")
    @Test
    fun findTokensByDepartment() {
        // given
        val department = "컴퓨터소프트웨어공학과"

        val subscribeEntity1 = SubscribeEntity.from(
            Subscribe.of("0001", department, listOf("봉사"), true, true)
        )

        val subscribeEntity2 = SubscribeEntity.from(
            Subscribe.of("0002", department, listOf("봉사"), true, true)
        )

        val subscribeEntity3 = SubscribeEntity.from(
            Subscribe.of("0003", department, listOf("봉사"), false, true)
        )

        val subscribeEntity4 = SubscribeEntity.from(
            Subscribe.of("0004", "기계공학과", listOf("봉사"), true, true)
        )

        subscribeRepository.saveAll(
            listOf(
                subscribeEntity1,
                subscribeEntity2,
                subscribeEntity3,
                subscribeEntity4
            )
        )

        // when
        val tokens = subscribeRepository.findTokensByDepartment(department)

        // then
        assertThat(tokens).hasSize(2)
            .containsExactlyInAnyOrder("0001", "0002")
    }

    @DisplayName("해당 학과 알림 구독이 되어있는 토큰이 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findTokensByDepartmentWhenEmpty() {
        // given
        val department = "컴퓨터소프트웨어공학과"

        val subscribeEntity1 = SubscribeEntity.from(
            Subscribe.of("0001", department, listOf("봉사"), false, true)
        )

        val subscribeEntity2 = SubscribeEntity.from(
            Subscribe.of("0002", "기계공학과", listOf("봉사"), true, true)
        )

        subscribeRepository.saveAll(
            listOf(
                subscribeEntity1,
                subscribeEntity2,
            )
        )

        // when
        val tokens = subscribeRepository.findTokensByDepartment(department)

        // then
        assertThat(tokens).isEmpty()
    }

    @DisplayName("해당 키워드 알림 구독이 되어있는 토큰을 불러온다.")
    @Test
    fun findTokensContainingKeyword() {
        // given
        val keyword = "시험"

        val subscribeEntity1 = SubscribeEntity.from(
            Subscribe.of("0001", "컴퓨터소프트웨어공학과", listOf(keyword), true, true)
        )

        val subscribeEntity2 = SubscribeEntity.from(
            Subscribe.of("0002", "기계공학과", listOf("학사",keyword), true, true)
        )

        val subscribeEntity3 = SubscribeEntity.from(
            Subscribe.of("0003", "정보통신공학과", listOf(keyword), true, false)
        )

        val subscribeEntity4 = SubscribeEntity.from(
            Subscribe.of("0004", "정보통신공학과", listOf("학사"), true, true)
        )

        subscribeRepository.saveAll(
            listOf(
                subscribeEntity1,
                subscribeEntity2,
                subscribeEntity3,
                subscribeEntity4
            )
        )

        // when
        val tokens = subscribeRepository.findTokensContainingKeyword(keyword)

        // then
        assertThat(tokens).hasSize(2)
            .containsExactlyInAnyOrder("0001", "0002")
    }

    @DisplayName("해당 키워드 알림 구독이 되어있는 토큰이 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findTokensContainingKeywordWhenEmpty() {
        // given
        val keyword = "시험"

        val subscribeEntity1 = SubscribeEntity.from(
            Subscribe.of("0001", "컴퓨터소프트웨어공학과", listOf(keyword), false, false)
        )

        val subscribeEntity2 = SubscribeEntity.from(
            Subscribe.of("0002", "기계공학과", listOf("봉사"), true, true)
        )

        subscribeRepository.saveAll(
            listOf(
                subscribeEntity1,
                subscribeEntity2,
            )
        )

        // when
        val tokens = subscribeRepository.findTokensContainingKeyword(keyword)

        // then
        assertThat(tokens).isEmpty()
    }

}