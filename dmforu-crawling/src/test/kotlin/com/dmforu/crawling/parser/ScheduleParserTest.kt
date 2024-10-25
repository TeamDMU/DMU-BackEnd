package com.dmforu.crawling.parser

import com.dmforu.crawling.exception.ScheduleParseException
import com.dmforu.crawling.loader.HtmlLoader
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScheduleParserTest {

    @Mock
    private lateinit var htmlLoader: HtmlLoader<Document>

    @InjectMocks
    private lateinit var scheduleParser: ScheduleParser


    @DisplayName("학사 일정을 크롤링 할 수 있다.")
    @Test
    fun parse() {
        // given
        val year = 2024

        val html1 = createHtml(year - 1)
        val html2 = createHtml(year)
        val html3 = createHtml(year + 1)

        val document1 = Jsoup.parse(html1)
        val document2 = Jsoup.parse(html2)
        val document3 = Jsoup.parse(html3)

        given(htmlLoader.get(anyString()))
            .willReturn(document1)
            .willReturn(document2)
            .willReturn(document3)

        // when
        val result = scheduleParser.parse(year)

        // then
        assertThat(result).hasSize(3)

        val parsedYear = result[1]
        assertThat(parsedYear.year).isEqualTo(year)
        assertThat(parsedYear.yearSchedule).hasSize(1)

        val month = parsedYear.yearSchedule[0]
        assertThat(month.month).isEqualTo(1)
        assertThat(month.monthSchedule).hasSize(2)

        val firstSchedule = month.monthSchedule[0]
        assertThat(firstSchedule.dates).isEqualTo(listOf("01.01(월)", "01.01(월)"))
        assertThat(firstSchedule.content).isEqualTo("신정")

        val secondSchedule = month.monthSchedule[1]
        assertThat(secondSchedule.dates).isEqualTo(listOf("01.03(수)", "01.15(월)"))
        assertThat(secondSchedule.content).isEqualTo("정시모집 원서 접수")
    }

    @DisplayName("월 정보가 누락된 경우, ScheduleParserException 예외가 발생한다.")
    @Test
    fun parseWhenMonthEmpty() {
        // given
        val year = 2024

        val html1 = createInvalidHtmlWhenEmptyMonth(year - 1)
        val html2 = createInvalidHtmlWhenEmptyMonth(year)
        val html3 = createInvalidHtmlWhenEmptyMonth(year + 1)

        val document1 = Jsoup.parse(html1)
        val document2 = Jsoup.parse(html2)
        val document3 = Jsoup.parse(html3)

        given(htmlLoader.get(anyString()))
            .willReturn(document1)
            .willReturn(document2)
            .willReturn(document3)

        // when // then
        assertThatThrownBy { scheduleParser.parse(year) }
            .isInstanceOf(ScheduleParseException::class.java)
            .hasMessage("학사 일정 월 정보를 찾을 수 없습니다.")

    }

    @DisplayName("월 정보가 숫자가 아닌 경우, ScheduleParseException 예외가 발생한다.")
    @Test
    fun parseWhenMonthIsNotInt() {
        // given
        val year = 2024

        val html1 = createInvalidHtmlWhenMonthIsNotInt(year - 1)
        val html2 = createInvalidHtmlWhenMonthIsNotInt(year)
        val html3 = createInvalidHtmlWhenMonthIsNotInt(year + 1)

        val document1 = Jsoup.parse(html1)
        val document2 = Jsoup.parse(html2)
        val document3 = Jsoup.parse(html3)

        given(htmlLoader.get(anyString()))
            .willReturn(document1)
            .willReturn(document2)
            .willReturn(document3)

        // when // then
        assertThatThrownBy { scheduleParser.parse(year) }
            .isInstanceOf(ScheduleParseException::class.java)
            .hasMessage("학사 일정 월 정보를 숫자로 변경할 수 없습니다.")
    }

    private fun createHtml(year: Int) = """
                <div class="yearSchdulWrap">
                    <div class="monthTable">
                        <input type="hidden" id="monthTableYear_1_1" value="2024">
                        <p id="yearmonth${year}1">2024.01</p>
                    </div>
                    <div class="scheList">
                        <ul>
                            <li>
                                <dl>
                                    <dt>
                                        <span class="">" 01.01 (월) "</span>
                                    </dt>
                                    <dd>
                                        <span>신정</span>
                                    </dd>
                                 </dl>
                            </li>
                            <li>
                                <dl>
                                    <dt>
                                        <span class="">" 01.03 (수) ~ 01.15 (월) "</span>
                                    </dt>
                                    <dd>
                                        <span>정시모집 원서 접수</span>
                                    </dd>
                                 </dl>
                            </li>
                        </ul>
                    </div>
                </div>
            """.trimIndent()

    private fun createInvalidHtmlWhenEmptyMonth(year: Int) = """
                <div class="yearSchdulWrap">
                    <div class="scheList">
                        <ul>
                            <li>
                                <dl>
                                    <dt>
                                        <span class="">" 01.01 (월) "</span>
                                    </dt>
                                    <dd>
                                        <span>신정</span>
                                    </dd>
                                 </dl>
                            </li>
                            <li>
                                <dl>
                                    <dt>
                                        <span class="">" 01.03 (수) ~ 01.15 (월) "</span>
                                    </dt>
                                    <dd>
                                        <span>정시모집 원서 접수</span>
                                    </dd>
                                 </dl>
                            </li>
                        </ul>
                    </div>
                </div>
            """.trimIndent()

    private fun createInvalidHtmlWhenMonthIsNotInt(year: Int) = """
                <div class="yearSchdulWrap">
                    <div class="monthTable">
                        <input type="hidden" id="monthTableYear_1_1" value="2024">
                            <p id="yearmonth${year}1">2024.as</p>
            
                    </div>
                    <div class="scheList">
                        <ul>
                            <li>
                                <dl>
                                    <dt>
                                        <span class="">" 01.01 (월) "</span>
                                    </dt>
                                    <dd>
                                        <span>신정</span>
                                    </dd>
                                 </dl>
                            </li>
                            <li>
                                <dl>
                                    <dt>
                                        <span class="">" 01.03 (수) ~ 01.15 (월) "</span>
                                    </dt>
                                    <dd>
                                        <span>정시모집 원서 접수</span>
                                    </dd>
                                 </dl>
                            </li>
                        </ul>
                    </div>
                </div>
            """.trimIndent()
}