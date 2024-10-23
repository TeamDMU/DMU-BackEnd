package com.dmforu.domain.diet

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DietTest {

    @DisplayName("식단을 생성할 수 있다.")
    @Test
    fun of() {
        // given
        val date = LocalDate.of(2024, 10, 23)
        val menus = listOf("메뉴1", "메뉴2", "메뉴3")

        // when
        val diet = Diet.of(date = date, menus = menus)

        // then
        assertThat(diet.date).isEqualTo(date)
        assertThat(diet.menus).isEqualTo(menus)
    }

}