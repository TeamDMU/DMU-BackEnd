package com.dmforu.crawling.parser

import com.dmforu.crawling.exception.GenerateNoticeUrlException
import com.dmforu.crawling.loader.HtmlLoader
import com.dmforu.domain.notice.Notice
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Matcher
import java.util.regex.Pattern

class DepartmentNoticeParser(
    private val htmlLoader: HtmlLoader<Document>,
) {

    private lateinit var major: DepartmentCrawlingPath
    private var pageNumber: Int = 1

    fun parse(major: DepartmentCrawlingPath): List<Notice> {
        initialize(major)

        val document = htmlLoader.get(generateSearchUrl())

        return document.select(".board-table tbody tr")
            .mapNotNull { row -> parseRow(row) }
            .toList()

    }

    private fun initialize(major: DepartmentCrawlingPath) {
        this.major = major
    }

    private fun parseRow(row: Element): Notice? {
        val number = row.select(".td-num").text().toIntOrNull() ?: return null
        val title = row.select(".td-subject a").text()
        val author = row.select(".td-write").text()
        val url = generateUrlFromSearch(row.select(".td-subject a").attr("href"))
        val date = LocalDate.parse(row.select(".td-date").text(), formatter)

        return Notice.of(
            number = number,
            type = major.type,
            date = date,
            title = title,
            author = author,
            url = url,
        )
    }

    private fun generateSearchUrl(): String {
        return StringBuilder()
            .append("https://www.dongyang.ac.kr/dmu/").append(major.path)
            .append("/subview.do?page=").append(pageNumber++).toString()

    }

    private fun generateUrlFromSearch(url: String): String {
        val matcher: Matcher = pattern.matcher(url)

        verifyValidMatcher(matcher)

        return StringBuilder()
            .append("https://www.dongyang.ac.kr/combBbs/").append(matcher.group(1))
            .append("/").append(matcher.group(2)).append("/").append(matcher.group(4))
            .append("/view.do?layout=unknown").toString()
    }

    private fun verifyValidMatcher(matcher: Matcher) {
        if (!matcher.find()) {
            throw GenerateNoticeUrlException()
        }
    }


    companion object {
        private val pattern: Pattern = Pattern.compile("\\('([^']+)'\\,'([^']+)'\\,'([^']+)'\\,'([^']+)'")
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}