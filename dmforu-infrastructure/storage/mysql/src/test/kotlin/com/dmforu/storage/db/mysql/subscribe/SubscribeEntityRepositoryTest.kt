package com.dmforu.storage.db.mysql.subscribe

import com.dmforu.domain.subscribe.Subscribe
import com.dmforu.storage.db.mysql.config.MysqlJpaConfig
import com.dmforu.storage.db.mysql.MysqlApplicationTest
import com.dmforu.storage.db.mysql.MysqlIntegrationTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

class SubscribeEntityRepositoryTest : MysqlIntegrationTest(){

    @Autowired
    private lateinit var subscribeRepository: SubscribeJpaRepository

    @Autowired
    private lateinit var subscribeEntityRepository: SubscribeEntityRepository

    @AfterEach
    fun tearDown() {
        subscribeRepository.deleteAllInBatch()
    }

    @DisplayName("구독 정보를 저장할 수 있다.")
    @Test
    fun write() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("시험"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        // when
        subscribeEntityRepository.save(subscribe)

        // then
        val result = subscribeRepository.findAll()
        assertThat(result).hasSize(1)
            .extracting("token", "department", "keywords", "isDepartmentSubscribed", "isKeywordSubscribed")
            .containsExactly(
                tuple("0001", "컴퓨터소프트웨어공학과", listOf("시험"), true, true)
            )
    }

    @DisplayName("토큰으로 구독정보를 불러올 수 있다.")
    @Test
    fun findByToken() {
        // given
        val subscribe = Subscribe.of(
            token = "0001",
            department = "컴퓨터소프트웨어공학과",
            keywords = listOf("시험"),
            isDepartmentSubscribed = true,
            isKeywordSubscribed = true
        )

        subscribeRepository.save(SubscribeEntity.from(subscribe))

        // when
        val savedSubscribe = subscribeEntityRepository.findByToken("0001")

        // then
        assertThat(savedSubscribe).isEqualTo(subscribe)
        assertThat(savedSubscribe)
            .extracting("token", "department", "keywords", "isDepartmentSubscribed", "isKeywordSubscribed")
            .containsExactly("0001", "컴퓨터소프트웨어공학과", listOf("시험"), true, true)
    }

    @DisplayName("토큰에 해당하는 구독 정보가 없는 경우, Null을 반환한다.")
    @Test
    fun findByTokenWhenEmpty() {
        // given

        // when
        val savedSubscribe = subscribeEntityRepository.findByToken("0001")

        // then
        assertThat(savedSubscribe).isNull()
    }

    @DisplayName("해당 학과 알림 구독이 되어있는 토큰을 불러온다.")
    @Test
    fun findTokensByDepartment() {
        // given
        val department = "컴퓨터소프트웨어공학과"

        val subscribe1 = Subscribe.of(
            "0001", department, listOf("시험"), true, true
        )

        val subscribe2 = Subscribe.of(
            "0002", department, listOf("시험"), true, true
        )

        val subscribe3 = Subscribe.of(
            "0003", department, listOf("시험"), false, true
        )

        val subscribe4 = Subscribe.of(
            "0004", "기계공학과", listOf("시험"), true, true
        )

        subscribeRepository.saveAll(
            listOf(
                SubscribeEntity.from(subscribe1),
                SubscribeEntity.from(subscribe2),
                SubscribeEntity.from(subscribe3),
                SubscribeEntity.from(subscribe4)
            )
        )

        // when
        val subscribes = subscribeEntityRepository.findTokensByDepartment(department)

        // then
        assertThat(subscribes).hasSize(2)
            .containsExactlyInAnyOrder("0001", "0002")
    }

    @DisplayName("해당 학과 알림 구독이 되어있는 토큰이 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findTokensByDepartmentWhenEmpty() {
        // given
        val department = "컴퓨터소프트웨어공학과"

        val subscribe1 = Subscribe.of(
            "0001", department, listOf("시험"), false, true
        )

        val subscribe2 = Subscribe.of(
            "0002", "기계공학과", listOf("시험"), true, true
        )

        subscribeRepository.saveAll(
            listOf(
                SubscribeEntity.from(subscribe1),
                SubscribeEntity.from(subscribe2)
            )
        )

        // when
        val subscribes = subscribeEntityRepository.findTokensByDepartment(department)

        // then
        assertThat(subscribes).isEmpty()
    }

    @DisplayName("해당 키워드 알림 구독이 되어있는 토큰을 불러온다.")
    @Test
    fun findTokensContainingKeyword() {
        // given
        val keyword = "시험"

        val subscribe1 = Subscribe.of(
            "0001", "컴퓨터소프트웨어공학과", listOf("시험", "학사"), true, true
        )

        val subscribe2 = Subscribe.of(
            "0002", "기계공학과", listOf("시험"), true, false
        )

        val subscribe3 = Subscribe.of(
            "0003", "컴퓨터소프트웨어공학과", listOf("봉사"), true, true
        )

        val subscribe4 = Subscribe.of(
            "0004", "기계공학과", listOf("시험"), true, true
        )

        subscribeRepository.saveAll(
            listOf(
                SubscribeEntity.from(subscribe1),
                SubscribeEntity.from(subscribe2),
                SubscribeEntity.from(subscribe3),
                SubscribeEntity.from(subscribe4)
            )
        )

        // when
        val subscribes = subscribeEntityRepository.findTokensContainingKeyword(keyword)

        // then
        assertThat(subscribes).hasSize(2)
            .containsExactlyInAnyOrder("0001", "0004")
    }

    @DisplayName("해당 키워드 알림 구독이 되어있는 토큰이 없는 경우, 빈 리스트를 반환한다.")
    @Test
    fun findTokensContainingKeywordWhenEmpty() {
        // given
        val keyword = "학사"

        val subscribe1 = Subscribe.of(
            "0001", "컴퓨터소프트웨어공학과", listOf("학사", "봉사"), true, false
        )

        val subscribe2 = Subscribe.of(
            "0002", "기계공학과", listOf("시험", "장학"), true, true
        )

        subscribeRepository.saveAll(
            listOf(
                SubscribeEntity.from(subscribe1),
                SubscribeEntity.from(subscribe2)
            )
        )

        // when
        val subscribes = subscribeEntityRepository.findTokensContainingKeyword(keyword)

        // then
        assertThat(subscribes).isEmpty()
    }
}