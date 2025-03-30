package com.dmforu.crawling.parser


import com.dmforu.crawling.loader.HtmlLoader
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class DietParserTest {

    @Mock
    private lateinit var htmlLoader: HtmlLoader<Document>

    @InjectMocks
    private lateinit var dietParser: DietParser

    @DisplayName("식단표를 파싱할 수 있다")
    @Test
    fun parseNormalCase() {
        // given
        val mockDocument = mock(Document::class.java)

        val menuRows = Elements(mock(Element::class.java), mock(Element::class.java))
        val menuCells = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("[점심] 김치찌개, 된장국, 불고기") },
            mock(Element::class.java).apply { given(text()).willReturn("[점심] 라면, 김밥, 떡볶이") }
        )

        val dateElements = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("월요일(2024.03.25)") },
            mock(Element::class.java).apply { given(text()).willReturn("화요일(2024.03.26)") }
        )

        given(htmlLoader.get(anyString())).willReturn(mockDocument)
        given(mockDocument.select("div.table_1 table tbody tr")).willReturn(menuRows)
        given(mockDocument.select("div.table_1 thead tr th")).willReturn(dateElements)
        given(menuRows[1].select("td")).willReturn(menuCells)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).hasSize(2)
            .extracting("date", "menus")
            .containsExactly(
                tuple(LocalDate.of(2024, 3, 25), listOf("김치찌개", "된장국", "불고기")),
                tuple(LocalDate.of(2024, 3, 26), listOf("라면", "김밥", "떡볶이"))
            )
    }

    @DisplayName("메뉴가 비어있는 경우 빈 리스트로 처리한다")
    @Test
    fun parseEmptyMenu() {
        // given
        val mockDocument = mock(Document::class.java)

        val menuRows = Elements(mock(Element::class.java), mock(Element::class.java))
        val menuCells = Elements(
            mock(Element::class.java).apply {
                given(text()).willReturn("[점심] ")
            }
        )

        val dateElements = Elements(
            mock(Element::class.java).apply {
                given(text()).willReturn("수요일(2024.03.27)")
            }
        )

        given(htmlLoader.get(anyString())).willReturn(mockDocument)
        given(mockDocument.select("div.table_1 table tbody tr")).willReturn(menuRows)
        given(mockDocument.select("div.table_1 thead tr th")).willReturn(dateElements)
        given(menuRows[1].select("td")).willReturn(menuCells)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).hasSize(1)
        assertThat(result[0].date).isEqualTo(LocalDate.of(2024, 3, 27))
        assertThat(result[0].menus).isEmpty()
    }

    @DisplayName("날짜와 메뉴의 수가 다른 경우 매칭되는 것만 처리한다")
    @Test
    fun parseWithDifferentSizes() {
        // given
        val mockDocument = mock(Document::class.java)

        val menuRows = Elements(mock(Element::class.java), mock(Element::class.java))

        val menuCells = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("[점심] 김치찌개, 된장국") }
        )

        val dateElements = Elements(
            mock(Element::class.java).apply { given(text()).willReturn("금요일(2024.03.29)") },
            mock(Element::class.java).apply { given(text()).willReturn("토요일(2024.03.30)") }
        )

        // Mock 동작 설정
        given(htmlLoader.get(anyString())).willReturn(mockDocument)
        given(mockDocument.select("div.table_1 table tbody tr")).willReturn(menuRows)
        given(mockDocument.select("div.table_1 thead tr th")).willReturn(dateElements)
        given(menuRows[1].select("td")).willReturn(menuCells)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).hasSize(1)
        assertThat(result[0].date).isEqualTo(LocalDate.of(2024, 3, 29))
        assertThat(result[0].menus).containsExactly("김치찌개", "된장국")
    }

    @DisplayName("메뉴 테이블이 없는 경우 빈 리스트를 반환한다")
    @Test
    fun parseWithNoMenuTable() {
        // given
        val mockDocument = mock(Document::class.java)

        val emptyMenuRows = Elements()

        val dateElements = Elements(
            mock(Element::class.java).apply {
                given(text()).willReturn("일요일(2024.03.31)")
            }
        )

        given(htmlLoader.get(anyString())).willReturn(mockDocument)
        given(mockDocument.select("div.table_1 table tbody tr")).willReturn(emptyMenuRows)
        given(mockDocument.select("div.table_1 thead tr th")).willReturn(dateElements)

        // when
        val result = dietParser.parse()

        // then
        assertThat(result).isEmpty()
    }
}
