package com.dmforu.crawling

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

object WebPageLoader {

    @JvmStatic
    fun getHTML(url: String?): Document {
        if (url.isNullOrBlank()) {
            throw IllegalArgumentException("URL이 비어있거나 잘못되었습니다.")
        }

        return try {
            Jsoup.connect(url).get()
        } catch (e: IOException) {
            // TODO: 페이지 로딩 실패나 네트워크 오류인 경우 핸들링을 해야함
            throw IllegalArgumentException("해당하는 URL 의 HTML을 불러올 수 없습니다.")
        }
    }
}