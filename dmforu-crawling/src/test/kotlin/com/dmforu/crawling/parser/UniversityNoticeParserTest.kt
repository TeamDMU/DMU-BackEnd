package com.dmforu.crawling.parser

import com.dmforu.crawling.loader.HtmlLoader
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class UniversityNoticeParserTest {

    @Mock
    private lateinit var htmlLoader: HtmlLoader<Document>

    @InjectMocks
    private lateinit var universityNoticeParser: UniversityNoticeParser

    @DisplayName("대학 공지사항 목록을 파싱할 수 있다.")
    @Test
    fun parse() {
        // given
        val html = """
            <table class="board-table">
                <tbody>
                    <tr>
                        <td class="td-num">2</td>
                        <td class="td-subject">
                            <a href="/url">
                                <strong>공지 제목 2</strong>
                            </a>
                        </td>
                        <td class="td-write">작성자 2</td>
                        <td class="td-date">2024.10.02</td>
                    </tr>
                    <tr>
                        <td class="td-num">1</td>
                        <td class="td-subject">
                            <a href="/url">
                                <strong>공지 제목 1</strong>
                            </a>
                        </td>
                        <td class="td-write">작성자 1</td>
                        <td class="td-date">2024.10.01</td>
                    </tr>
                </tbody>
            </table>
        """.trimIndent()

        val mockDocument = Jsoup.parse(html)
        given(htmlLoader.get(anyString())).willReturn(mockDocument)

        // when
        val notices = universityNoticeParser.parse()

        // then
        assertThat(notices).hasSize(2)
            .extracting("number", "title", "author", "date", "url")
            .containsExactlyInAnyOrder(
                tuple(
                    2,
                    "공지 제목 2",
                    "작성자 2",
                    LocalDate.of(2024, 10, 2),
                    "https://www.dongyang.ac.kr/url?layout=unknown"
                ),
                tuple(
                    1,
                    "공지 제목 1",
                    "작성자 1",
                    LocalDate.of(2024, 10, 1),
                    "https://www.dongyang.ac.kr/url?layout=unknown"
                )
            )
    }

    @DisplayName("공지사항 번호가 유효하지 않은 경우 해당 행을 건너뛴다.")
    @Test
    fun parseWhenNumberIsInvalid() {
        // given
        val html = """
            <table class="board-table">
                <tbody>
                    <tr>
                        <td class="td-num">일반공지</td>
                        <td class="td-subject">
                            <a href="/url">
                                <strong>공지 제목</strong>
                            </a>
                        </td>
                        <td class="td-write">작성자</td>
                        <td class="td-date">2024.10.01</td>
                    </tr>
                </tbody>
            </table>
        """.trimIndent()

        val mockDocument = Jsoup.parse(html)
        given(htmlLoader.get(anyString())).willReturn(mockDocument)

        // when
        val notices = universityNoticeParser.parse()

        // then
        assertThat(notices).isEmpty()
    }

    @DisplayName("제목에 접미사가 포함된 경우, 접미사를 제거한다.")
    @Test
    fun removeSuffix() {
        // given
        val html = """
            <table class="board-table">
                <tbody>
                    <tr>
                        <td class="td-num">1</td>
                        <td class="td-subject">
                            <a href="/url">
                                <strong>공지 제목</strong>
                                <span class="new">새글</span>
                            </a>
                        </td>
                        <td class="td-write">작성자 1</td>
                        <td class="td-date">2024.10.02</td>
                    </tr>
                </tbody>
            </table>
        """.trimIndent()

        val mockDocument = Jsoup.parse(html)
        given(htmlLoader.get(anyString())).willReturn(mockDocument)

        // when
        val notices = universityNoticeParser.parse()

        // then
        assertThat(notices).hasSize(1)
            .extracting("number", "title", "author", "date", "url")
            .containsExactlyInAnyOrder(
                tuple(
                    1,
                    "공지 제목",
                    "작성자 1",
                    LocalDate.of(2024, 10, 2),
                    "https://www.dongyang.ac.kr/url?layout=unknown"
                ),
            )
    }
}