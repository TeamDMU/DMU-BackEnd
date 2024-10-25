package com.dmforu.crawling.parser

import com.dmforu.crawling.loader.HtmlLoader
import com.dmforu.domain.diet.Diet
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DietParser (
    private val htmlLoader: HtmlLoader<Document>
) : Parser<Diet> {
    override fun parse(): List<Diet> {
        val document = htmlLoader.get(DMU_DIET_URL)
        val rows = document.select(TABLE_SELECTOR)

        return rows.mapNotNull { row -> parseDiet(row) }
    }

    private fun parseDiet(row: Element): Diet? {
        val columns = row.select(DATA_SELECTOR)

        // 요일 출력
        val day = columns[0].text()

        // 짝수 컬럼에는 day 정보가 있는 위치에 "교직원식당"의 정보가 기입됨으로 넘겨야 함
        if (PASS_COLUMN == day) {
            return null
        }

        val parsedDate = LocalDate.parse(day.substring(0, 10), DATE_FORMATTER)

        // 코리안 푸드 메뉴가 4번째 컬럼에 작성된다.
        // 만일 식단의 작성 방법이 변경된다면 해당 로직 또한 변경의 필요성이 존재한다.
        val menuColumn = columns.getOrNull(3)
        val menuElement = menuColumn?.text()

        // 메뉴가 공백이 아니면 메뉴를 분리하여 리스트로 변환
        val menus = menuElement?.takeIf { it.isNotBlank() }
            ?.split(MENU_SEPARATOR)
            ?.map { it.trim() }
            ?: emptyList()

        return Diet.of(parsedDate, menus)
    }

    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val DMU_DIET_URL = "https://www.dongyang.ac.kr/diet/dongyang/1/view.do"
        private const val TABLE_SELECTOR = "div.table_1 table tbody tr"
        private const val DATA_SELECTOR = "th, td"
        private const val MENU_SEPARATOR = ", "
        private const val PASS_COLUMN = "교직원식당"
    }
}
