package com.dmforu.crawling.loader

import com.dmforu.crawling.exception.HtmlLoadException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.jsoup.nodes.Document
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import java.io.IOException


class JsoupHtmlLoaderTest {

    private lateinit var connectionProvider: (String) -> Document
    private lateinit var htmlLoader: JsoupHtmlLoader

    @BeforeEach
    fun setUp() {
        connectionProvider = mock()
        htmlLoader = JsoupHtmlLoader(connectionProvider)
    }

    @DisplayName("URL의 HTML을 불러올 수 있다.")
    @Test
    fun get() {
        // given
        val url = "https://www.example.com"
        val expectedDocument: Document = mock()

        given(connectionProvider.invoke(url)).willReturn(expectedDocument)

        // when
        val result = htmlLoader.get(url)

        // then
        assertThat(result).isSameAs(expectedDocument)
    }

    @DisplayName("URL의 HTML을 불러올 수 없을 경우, HtmlLoadException 예외가 발생한다.")
    @Test
    fun getWhenConnectionFails() {
        // given
        val url = "https://www.example.com"

        given(connectionProvider.invoke(url)).willAnswer { throw IOException() }

        // when // then
        assertThatThrownBy { htmlLoader.get(url) }
            .isInstanceOf(HtmlLoadException::class.java)
            .hasMessage("해당하는 URL의 HTML을 불러올 수 없습니다.")
    }
}