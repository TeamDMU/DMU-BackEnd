package com.dmforu.crawling.parser

import com.dmforu.crawling.exception.GenerateNoticeUrlException
import com.dmforu.crawling.loader.HtmlLoader
import org.assertj.core.api.Assertions.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.*
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class DepartmentNoticeParserTest {

    @Mock
    private lateinit var htmlLoader: HtmlLoader<Document>

    @InjectMocks
    private lateinit var parser: DepartmentNoticeParser

    @DisplayName("학과 공지사항 목록을 파싱할 수 있다.")
    @Test
    fun parse() {
        // given
        val html = """
            <table class="board-table">
                <tbody>
                    <tr>
                        <td class="td-num">2</td>
                        <td class="td-subject"><a href="javascript:fnView('2','url','title','2')">공지 제목 2</a></td>
                        <td class="td-write">작성자 2</td>
                        <td class="td-date">2024.10.02</td>
                    </tr>
                    <tr>
                        <td class="td-num">1</td>
                        <td class="td-subject"><a href="javascript:fnView('1','url','title','1')">공지 제목 1</a></td>
                        <td class="td-write">작성자 1</td>
                        <td class="td-date">2024.10.01</td>
                    </tr>
                </tbody>
            </table>
        """.trimIndent()
        val document: Document = Jsoup.parse(html)

        given(htmlLoader.get(anyString())).willReturn(document)

        // when
        val notices = parser.parse(DepartmentCrawlingPath.COMPUTER_SOFTWARE_ENGINEERING)

        // then
        assertThat(notices).hasSize(2)
            .extracting("number", "title", "author", "date", "url")
            .containsExactlyInAnyOrder(
                tuple(
                    2,
                    "공지 제목 2",
                    "작성자 2",
                    LocalDate.of(2024, 10, 2),
                    "https://www.dongyang.ac.kr/combBbs/2/url/2/view.do?layout=unknown"
                ),
                tuple(
                    1,
                    "공지 제목 1",
                    "작성자 1",
                    LocalDate.of(2024, 10, 1),
                    "https://www.dongyang.ac.kr/combBbs/1/url/1/view.do?layout=unknown"
                ),

                )

    }

    @DisplayName("학과 공지사항 목록을 파싱할 때, 공지사항 주소가 잘못되어 있는 경우 예외를 발생시킨다.")
    @Test
    fun parseWhenUrlInvalid() {
        // given
        val html = """
            <table class="board-table">
                <tbody>
                    <tr>
                        <td class="td-num">2</td>
                        <td class="td-subject"><a href="invalid">공지 제목 2</a></td>
                        <td class="td-write">작성자 2</td>
                        <td class="td-date">2024.10.02</td>
                    </tr>
                    <tr>
                        <td class="td-num">1</td>
                        <td class="td-subject"><a href="invalid">공지 제목 1</a></td>
                        <td class="td-write">작성자 1</td>
                        <td class="td-date">2024.10.01</td>
                    </tr>
                </tbody>
            </table>
        """.trimIndent()
        val document: Document = Jsoup.parse(html)

        given(htmlLoader.get(anyString())).willReturn(document)

        // when // then
        assertThatThrownBy { parser.parse(DepartmentCrawlingPath.COMPUTER_SOFTWARE_ENGINEERING) }
            .isInstanceOf(GenerateNoticeUrlException::class.java)
            .hasMessage("공지 URL 생성에 실패했습니다.")
    }
}