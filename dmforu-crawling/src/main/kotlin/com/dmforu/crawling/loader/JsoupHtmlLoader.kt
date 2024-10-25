package com.dmforu.crawling.loader

import com.dmforu.crawling.exception.HtmlLoadException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class JsoupHtmlLoader(
    private val connectionProvider : (String) -> Document = { Jsoup.connect(it).get() }
) : HtmlLoader<Document> {
    override fun get(url: String): Document {
        return try {
            connectionProvider(url)
        } catch (e: IOException) {
            throw HtmlLoadException("해당하는 URL의 HTML을 불러올 수 없습니다.", e)
        }
    }
}