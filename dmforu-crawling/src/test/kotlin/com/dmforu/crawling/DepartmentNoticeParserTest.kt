package com.dmforu.crawling

import com.dmforu.domain.notice.Major
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExtendWith(MockitoExtension::class)
class DepartmentNoticeParserTest {

    @Mock
    private lateinit var webPageLoader: WebPageLoader<Document>

    @InjectMocks
    private lateinit var parser: DepartmentNoticeParser

    @Test
    fun `should return list of Notice when HTML is valid`() {
        // Arrange

        parser.initialize(Major.COMPUTER_SOFTWARE_ENGINEERING)

        val mockDocument = mock(Document::class.java)
        val mockRow1 = mock(Element::class.java)
        val mockRow2 = mock(Element::class.java)

        // Mocking the rows of the table
        val rows = Elements(mockRow1, mockRow2)
        given(webPageLoader.getHTML(anyString())).willReturn(mockDocument)
        given(mockDocument.select(".board-table tbody tr")).willReturn(rows)

        // Setting up the first row mock
        val mockNumElement1 = mock(Element::class.java)
        given(mockNumElement1.text()).willReturn("1")

        val mockTitleElement1 = mock(Element::class.java)
        given(mockTitleElement1.text()).willReturn("Notice Title 1")

        val mockTitleElement10 = mock(Element::class.java)
        given(mockTitleElement10.attr("href")).willReturn("('dmu_23222','14','320','129499')")


        val mockAuthorElement1 = mock(Element::class.java)
        val testAuthorElements1 = Elements(mockAuthorElement1)
        given(mockRow1.select(".td-write")).willReturn(testAuthorElements1)
        given(mockAuthorElement1.text()).willReturn("Author A")

        val mockDateElement1 = mock(Element::class.java)
        given(mockDateElement1.text()).willReturn("2024.10.23")

        // Mocking select results for first row
        val test12 = mock(Elements(mockTitleElement10))
        given(mockRow1.select(".td-num")).willReturn(Elements(mockNumElement1))
        given(mockRow1.select(".td-subject a"))
            .willReturn(Elements(mockTitleElement1))
            .willReturn(test12)
        given(mockRow1.select(".td-date")).willReturn(Elements(mockDateElement1))

        // Setting up the second row mock
        val mockNumElement2 = mock(Element::class.java)
        given(mockNumElement2.text()).willReturn("2")

        val mockTitleElement2 = mock(Element::class.java)
        given(mockTitleElement2.text()).willReturn("Notice Title 2")
        given(mockTitleElement2.attr("href")).willReturn("('dmu_23222','14','320','129499')")

        val mockAuthorElement2 = mock(Element::class.java)
        val testAuthorElements2 = Elements(mockAuthorElement2)
        given(mockRow2.select(".td-write")).willReturn(testAuthorElements2)
        given(mockAuthorElement2.text()).willReturn("Author B")

        val mockDateElement2 = mock(Element::class.java)
        given(mockDateElement2.text()).willReturn("2024.10.24")

        // Mocking select results for second row
        given(mockRow2.select(".td-num")).willReturn(Elements(mockNumElement2))
        given(mockRow2.select(".td-subject a")).willReturn(Elements(mockTitleElement2))
        given(mockRow2.select(".td-date")).willReturn(Elements(mockDateElement2))

        // Act
        val notices = parser.parse()

        // Assert
        assertEquals(2, notices.size)
        assertTrue(notices.any { it.title == "Notice Title 1" && it.author == "Author A" && it.date == LocalDate.parse("2024.10.23", DateTimeFormatter.ofPattern("yyyy.MM.dd")) })
        assertTrue(notices.any { it.title == "Notice Title 2" && it.author == "Author B" && it.date == LocalDate.parse("2024.10.24", DateTimeFormatter.ofPattern("yyyy.MM.dd")) })
    }
}