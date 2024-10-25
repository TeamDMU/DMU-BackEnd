package com.dmforu.crawling.parser

import com.dmforu.crawling.loader.HtmlLoader
import com.dmforu.domain.notice.Notice
import org.jsoup.nodes.Document
import java.lang.NumberFormatException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UniversityNoticeParser(
    private val htmlLoader: HtmlLoader<Document>
) : Parser<Notice> {
    private var pageNumber = 1

    /**
     * HTML을 파싱하여 대학 공지사항 목록을 반환한다.
     *
     * @return 대학 공지사항 목록
     */
    override fun parse(): List<Notice> {
        val universityNotices: MutableList<Notice> = java.util.ArrayList<Notice>()

        val document = htmlLoader.get(generateSearchUrl())

        val rows = document.select(".board-table tbody tr")

        for (row in rows) {
            var number: Int

            try {
                number = row.select(".td-num").text().toInt()
            } catch (e: NumberFormatException) {
                continue
            }

            val title = removeSuffix(row.select(".td-subject a").text(), SUFFIX_NEW_POST)
            val author = row.select(".td-write").text()
            val url = generateUrlFromSearch(row.select(".td-subject a").attr("href"))
            val date = LocalDate.parse(row.select(".td-date").text(), DATE_FORMATTER)

            val universityNotice = Notice.of(
                number = number,
                type = NOTICE_TYPE,
                date = date,
                title = title,
                author = author,
                url = url
            )

            universityNotices.add(universityNotice)
        }

        return universityNotices
    }

    /**
     * 파싱할 페이지의 URL을 생성한다.
     *
     * @return URL
     */
    private fun generateSearchUrl(): String {
        return String.format(SEARCH_URL, pageNumber++)
    }

    /**
     * 파싱 결과를 통해 공지사항의 URL를 생성한다.
     *
     * @param url URL 파싱결과
     * @return 공지사항 URL
     */
    private fun generateUrlFromSearch(url: String): String {
        return String.format(RESULT_URL, url)
    }

    /**
     * 접미사를 제거한다.
     *
     * @param title  원본 문자열
     * @param suffix 제거하고 싶은 접미사
     * @return 원본에서 접미사를 제거한 문자열
     */
    private fun removeSuffix(title: String, suffix: String): String {
        if (title.endsWith(suffix)) {
            return title.substring(0, title.length - suffix.length).trim { it <= ' ' }
        }

        return title
    }

    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val SEARCH_URL = "https://www.dongyang.ac.kr/dongyang/129/subview.do?page=%d"
        private const val RESULT_URL = "https://www.dongyang.ac.kr%s?layout=unknown"
        private const val SUFFIX_NEW_POST = "새글"
        private const val NOTICE_TYPE = "대학"
    }
}