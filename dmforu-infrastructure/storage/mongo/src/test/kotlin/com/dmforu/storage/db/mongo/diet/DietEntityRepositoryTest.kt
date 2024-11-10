package com.dmforu.storage.db.mongo.diet

import com.dmforu.domain.diet.Diet
import com.dmforu.storage.db.mongo.MongoTestSupport
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate


class DietEntityRepositoryTest: MongoTestSupport(){

    @Autowired
    private lateinit var dietMongoRepository: DietMongoRepository

    @Autowired
    private lateinit var dietEntityRepository: DietEntityRepository

    @AfterEach
    fun tearDown() {
        dietMongoRepository.deleteAll()
    }

    @DisplayName("식단을 저장한다.")
    @Test
    fun write() {
        // given
        val diet1 = Diet.of(date = LocalDate.of(2024, 10, 1), menus = listOf("메뉴1, 메뉴2"))
        val diet2 = Diet.of(date = LocalDate.of(2024, 10, 2), menus = listOf("메뉴3, 메뉴4"))

        // when
        dietEntityRepository.write(listOf(diet1, diet2))

        // then
        val result = dietMongoRepository.findAll()[0]

        assertThat(result).isNotNull
        assertThat(DietMapper.entityToDiet(result)).isEqualTo(listOf(diet1, diet2))
    }

    @DisplayName("식단을 불러온다.")
    @Test
    fun read() {
        // given
        val diet1 = Diet.of(date = LocalDate.of(2024, 10, 1), menus = listOf("메뉴1, 메뉴2"))
        val diet2 = Diet.of(date = LocalDate.of(2024, 10, 2), menus = listOf("메뉴3, 메뉴4"))
        val diets = listOf(diet1, diet2)

        dietMongoRepository.save(DietMapper.mapToEntity(diets))

        // when
        val savedEntity = dietEntityRepository.read()

        assertThat(savedEntity).isNotNull
        assertThat(savedEntity).isEqualTo(diets)
    }

    @DisplayName("식단을 불러올 때, 식단이 없다면 null을 반환한다.")
    @Test
    fun readWhenEmpty() {
        // given

        // when
        val savedEntity = dietEntityRepository.read()

        // then
        assertThat(savedEntity).isNull()
    }

}
