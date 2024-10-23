package com.dmforu.domain.diet

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class DietWriterTest {

    @Mock
    private lateinit var dietRepository: DietRepository

    @InjectMocks
    private lateinit var dietWriter: DietWriter

    @DisplayName("식단 정보를 저장할 수 있다.")
    @Test
    fun write() {
        // given
        val date = LocalDate.of(2024, 10, 23)
        val menus = listOf("메뉴1", "메뉴2", "메뉴3")
        val diet = Diet.of(date = date, menus = menus)
        val diets = listOf(diet)

        // when
        dietWriter.overwrite(diets)

        // given
        verify(dietRepository).write(diets)

    }
}