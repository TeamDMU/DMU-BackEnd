package com.dmforu.crawling.parser

import com.dmforu.crawling.loader.HtmlLoader
import org.jsoup.nodes.Document
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class ScheduleParserTest {

    @Mock
    private lateinit var htmlLoader: HtmlLoader<Document>

    @InjectMocks
    private lateinit var scheduleParser: ScheduleParser


//    @DisplayName("학사 일정을 크롤링 할 수 있다.")
//    @Test
//    fun parse() {
//        // given
//        // todo 외부로 옮겨야하나?
//        val currentYear = LocalDate.now(ZoneId.of("Asia/Seoul")).year
//
//        val mockDocument1 = mock(Document::class.java)
//        val mockDocument2 = mock(Document::class.java)
//        val mockDocument3 = mock(Document::class.java)
//
//        // Mocking year and month tables
//        val yearTable1 = mock(Element::class.java)
//        val monthTable1 = mock(Element::class.java)
//
//        val yearTable2 = mock(Element::class.java)
//        val monthTable2 = mock(Element::class.java)
//
//        val yearTable3 = mock(Element::class.java)
//        val monthTable3 = mock(Element::class.java)
//
//        // Create elements for the first mock document
//        val yearElements1 = Elements(yearTable1)
//        val monthElements1 = Elements(monthTable1)
//        val yearElements2 = Elements(yearTable2)
//        val monthElements2 = Elements(monthTable2)
//        val yearElements3 = Elements(yearTable3)
//        val monthElements3 = Elements(monthTable3)
//
//        // Setting up different return values for different calls
//        given(webPageLoader.getHTML(anyString()))
//            .willReturn(mockDocument1) // 첫 번째 호출
//            .willReturn(mockDocument2) // 두 번째 호출
//            .willReturn(mockDocument3) // 세 번째 호출
//
//        // Setting up the behavior for the first document
//        given(mockDocument1.select(anyString())).willReturn(yearElements1)
//        given(yearTable1.select(anyString())).willReturn(monthElements1)
//        val test = Elements(mock(Element::class.java))
//        given(monthTable1.select(anyString())).willReturn(test)
//        given(test.first().text()).willReturn("2023.01")
//
//        // Setting up the behavior for the second document
//        given(mockDocument2.select(anyString())).willReturn(yearElements2)
//        given(yearTable2.select(anyString())).willReturn(monthElements2)
//        given(monthTable2.select(anyString())).willReturn(test)
//        given(test.first().text()).willReturn("2023.02")
//
//        // Setting up the behavior for the third document
//        given(mockDocument3.select(anyString())).willReturn(yearElements3)
//        given(yearTable3.select(anyString())).willReturn(monthElements3)
//        given(monthTable3.select(anyString())).willReturn(test)
//        given(test.first().text()).willReturn("2023.03")
//
//        // Execute
//        val result = scheduleParser.parse()
//
//        // Verify the result
//        assertEquals(3, result.size)
//    }


    //
    //        val test1 = Elements(mock(Element::class.java))
    //
    //        // Set up month text for the first month table
    //        given(monthTable1.select("p")).willReturn(test1)
    //
    //        given(test1.text()).willReturn("2024.1")
    //
    //        // Create elements for the second mock document

    //
    //        val test2 = Elements(mock(Element::class.java))
    //        // Set up month text for the second month table
    //        given(monthTable2.select("p")).willReturn(test2)
    //
    //        given(test2.text()).willReturn("2024.2")
    //
    //        // Create elements for the third mock document

    //
    //        val test3 = Elements(mock(Element::class.java))
    //        // Set up month text for the third month table
    //        given(monthTable3.select("p")).willReturn(test3)
    //
    //        given(test3.text()).willReturn("2024.3") // 여기도 마찬가지.
}