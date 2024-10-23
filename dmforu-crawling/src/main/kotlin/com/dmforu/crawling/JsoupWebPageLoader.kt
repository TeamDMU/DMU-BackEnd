package com.dmforu.crawling

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class JsoupWebPageLoader : WebPageLoader<Document> {
    override fun getHTML(url: String): Document {
        return try {
            Jsoup.connect(url).get()
        } catch (e: IOException) {
            // TODO: 페이지 로딩 실패나 네트워크 오류인 경우 핸들링을 해야함
            throw IllegalArgumentException("해당하는 URL 의 HTML을 불러올 수 없습니다.")
        }
    }
}