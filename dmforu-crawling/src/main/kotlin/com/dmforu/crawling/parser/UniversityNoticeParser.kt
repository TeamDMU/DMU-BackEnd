package com.dmforu.crawling.parser

import com.dmforu.crawling.loader.HtmlLoader
import com.dmforu.domain.notice.Notice
import org.jsoup.nodes.Document
import java.lang.NumberFormatException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UniversityNoticeParser(
    private val htmlLoader: HtmlLoader<Document>,
) : Parser<Notice> {
    private var pageNumber = 1

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
        String
        return universityNotices
    }

    private fun generateSearchUrl(): String {
        return StringBuilder()
            .append("https://www.dongyang.ac.kr/dmu/4904/subview.do?page=").append(pageNumber++)
            .toString()
    }

    private fun generateUrlFromSearch(url: String): String {
        return StringBuilder()
            .append("https://www.dongyang.ac.kr").append(url).append("?layout=unknown")
            .toString()
    }

    private fun removeSuffix(title: String, suffix: String): String {
        if (title.endsWith(suffix)) {
            return title.substring(0, title.length - suffix.length).trim { it <= ' ' }
        }

        return title
    }

    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val SUFFIX_NEW_POST = "새글"
        private const val NOTICE_TYPE = "대학"
    }
}