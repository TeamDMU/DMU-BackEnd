package com.dmforu.domain.diet

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class DietReaderTest {

    @Mock
    private lateinit var dietRepository: DietRepository

    @InjectMocks
    private lateinit var dietReader: DietReader

    @DisplayName("식단을 불러온다.")
    @Test
    fun read() {
        // given
        val date = LocalDate.of(2024, 10, 23)
        val menus = listOf("메뉴1", "메뉴2", "메뉴3")
        val diet = Diet.of(date = date, menus = menus)

        given(dietReader.read()).willReturn(listOf(diet))

        // when
        dietReader.read()

        // then
        verify(dietRepository).read()

    }

    @DisplayName("식단이 존재하지 않는 경우 예외가 발생한다.")
    @Test
    fun readWhenReturnNull() {
        // given
        given(dietReader.read()).willReturn(null)

        // when // then
        assertThatThrownBy { dietReader.read() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("식단 정보가 존재하지 않습니다. 관리자 에러")

        verify(dietRepository).read()
    }

}