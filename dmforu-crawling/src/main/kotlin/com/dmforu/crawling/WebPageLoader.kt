package com.dmforu.crawling

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

object WebPageLoader {
    /**
     * Jsoup을 사용하여 URL에 해당하는 웹 페이지의 HTML을 가져온다.
     *
     * @param url HTML을 가져오고자 하는 웹 페이지의 URL
     * @return URL에 접속하면 볼 수 있는 HTML을 반환
     */
    @JvmStatic
    fun getHTML(url: String?): Document {
        if (url.isNullOrBlank()) {
            throw IllegalArgumentException("URL이 비어있거나 잘못되었습니다.")
        }

        return try {
            Jsoup.connect(url).get()
        } catch (e: IOException) {
            // TODO: 페이지 로딩 실패나 네트워크 오류인 경우 핸들링을 해야함
            throw IllegalArgumentException()
        }
    }
}