package com.dmforu.crawling.schedule

import com.dmforu.crawling.DietParser
import com.dmforu.crawling.WebPageLoader
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class DietParserTest {

    @Mock
    private lateinit var webPageLoader: WebPageLoader<Document>

    @InjectMocks
    private lateinit var dietParser: DietParser

    @DisplayName("식단표를 크롤링 할 수 있다.")
    @Test
    fun parse() {
        // given
        val mockDocument = mock(Document::class.java)
        val mockRows = Elements()
        val row1 = mock(Element::class.java)
        val row2 = mock(Element::class.java)
        val row3 = mock(Element::class.java)
        mockRows.add(row1)
        mockRows.add(row2)
        mockRows.add(row3)
        val columns1 = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("2024.10.23") },
            mock(Element::class.java),
            mock(Element::class.java),
            mock(Element::class.java).apply { given(text()).willReturn("Menu 1, Menu 2") }
        )
        val columns2 = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("교직원식당") }
        )
        val columns3 = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("2024.10.24") },
            mock(Element::class.java),
            mock(Element::class.java),
            mock(Element::class.java).apply { given(text()).willReturn("Menu 3, Menu 4") }
        )

        given(webPageLoader.getHTML(anyString())).willReturn(mockDocument)
        given(mockDocument.select(anyString())).willReturn(mockRows)
        given(row1.select(anyString())).willReturn(columns1)
        given(row2.select(anyString())).willReturn(columns2)
        given(row3.select(anyString())).willReturn(columns3)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).hasSize(2)
            .extracting("date", "menus")
            .containsExactly(
                tuple(LocalDate.of(2024, 10, 23), listOf("Menu 1", "Menu 2")),
                tuple(LocalDate.of(2024, 10, 24), listOf("Menu 3", "Menu 4")),
            )
    }

    @DisplayName("메뉴가 비어있다면 메뉴를 빈 리스트로 반환한다.")
    @Test
    fun parseWhenEmptyMenus() {
        // given
        val mockDocument = mock(Document::class.java)
        val mockRows = Elements()
        val row = mock(Element::class.java)
        mockRows.add(row)
        val columns = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("2024.10.23") },
            mock(Element::class.java),
            mock(Element::class.java),
            mock(Element::class.java).apply { given(text()).willReturn(" ") }
        )

        given(webPageLoader.getHTML(anyString())).willReturn(mockDocument)
        given(mockDocument.select(anyString())).willReturn(mockRows)
        given(row.select(anyString())).willReturn(columns)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).hasSize(1)
        val diet = result[0]
        assertThat(diet.date).isEqualTo(LocalDate.of(2024, 10, 23))
        assertThat(diet.menus).isEmpty()
    }

    @DisplayName("공휴일인 경우 메뉴를 빈 리스트로 반환한다.")
    @Test
    fun parseWhenHolidays() {
        // given
        val mockDocument = mock(Document::class.java)
        val mockRows = Elements()
        val row = mock(Element::class.java)
        mockRows.add(row)
        val columns = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("2024.10.20") },
            mock(Element::class.java)
        )

        given(webPageLoader.getHTML(anyString())).willReturn(mockDocument)
        given(mockDocument.select(anyString())).willReturn(mockRows)
        given(row.select(anyString())).willReturn(columns)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).hasSize(1)
        val diet = result[0]
        assertThat(diet.date).isEqualTo(LocalDate.of(2024, 10, 20))
        assertThat(diet.menus).isEmpty()
    }
}

