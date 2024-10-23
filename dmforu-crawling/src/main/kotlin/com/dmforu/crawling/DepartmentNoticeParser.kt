package com.dmforu.crawling

import com.dmforu.domain.notice.Notice
import com.dmforu.domain.notice.Major
import java.lang.NumberFormatException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

class DepartmentNoticeParser : UrlGenerator(), Parser<Notice> {

    private lateinit var major: Major
    private var pageNumber: Int = 1

    fun initialize(major: Major) {
        this.major = major
    }

    /**
     * HTML을 파싱하여 학과 공지사항 목록을 반환한다.
     *
     * @return 학과 공지사항 목록
     */
    override fun parse(): List<Notice> {

        val departmentNotices: MutableList<Notice> = java.util.ArrayList<Notice>()

        val document = WebPageLoader.getHTML(generateSearchUrl())

        val rows = document.select(".board-table tbody tr")

        for (row in rows) {
            var number: Int

            try {
                number = row.select(".td-num").text().toInt()
            } catch (e: NumberFormatException) {
                continue
            }

            val title = row.select(".td-subject a").text()
            val author = row.select(".td-write").text()
            val url = generateUrlFromSearch(row.select(".td-subject a").attr("href"))
            val date = LocalDate.parse(row.select(".td-date").text(), formatter)

            val departmentNotice = Notice.of(
                number = number,
                type = major.type,
                date = date,
                title = title,
                author = author,
                url = url,
            )

            departmentNotices.add(departmentNotice)
        }

        return departmentNotices
    }

    /**
     * 파싱할 페이지의 URL을 생성한다.
     *
     * @return URL
     */
    protected override fun generateSearchUrl(): String {
        return java.lang.String.format(
            "https://www.dongyang.ac.kr/%s/%s/subview.do?page=%d",
            major.majorPath, major.noticePath, pageNumber++
        )
    }

    /**
     * 파싱 결과를 통해 공지사항의 URL을 생성한다.
     *
     * @param url 파싱 결과
     * @return URL
     * @throws IllegalArgumentException Matcher를 통해 URL을 생성할 수 없는 경우 예외 발생
     */
    protected override fun generateUrlFromSearch(url: String): String {
        // URL에서 정보를 추출하기 위한 Matcher 생성

        val matcher = pattern.matcher(url)

        require(matcher.find()) { "Matcher did not find any match" }

        return String.format(
            "https://www.dongyang.ac.kr/combBbs/%s/%s/%s/view.do?layout=unknown",
            matcher.group(1), matcher.group(2), matcher.group(4)
        )
    }

    companion object {
        private val pattern: Pattern = Pattern.compile("\\('([^']+)'\\,'([^']+)'\\,'([^']+)'\\,'([^']+)'")
        private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}