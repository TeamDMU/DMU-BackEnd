package com.dmforu.crawling.parser

import com.dmforu.crawling.loader.HtmlLoader
import com.dmforu.domain.diet.Diet
import org.jsoup.nodes.Document
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DietParser(
    private val htmlLoader: HtmlLoader<Document>
) : Parser<Diet> {
    
    override fun parse(): List<Diet> {
        val document = htmlLoader.get(DMU_DIET_URL)
        return parseDietList(document)
    }
    
    private fun parseDietList(document: Document): List<Diet> {
        val menus = document.select(MENU_SELECTOR)
        val dates = document.select(DATE_SELECTOR)
        
        val menuList = parseMenuList(menus)
        val dateList = parseDateList(dates)
        
        // 날짜와 메뉴 데이터를 조합하여 Diet 객체 생성
        return dateList.zip(menuList)
            .map { (date, menu) -> Diet.of(date, menu) }
    }
    
    private fun parseMenuList(menus: org.jsoup.select.Elements): List<List<String>> {
        if (menus.size < 2) return emptyList()
        
        return menus[1].select("td").map { element ->
            element.text()
                .substringAfter("[점심] ", "")
                .takeIf { it.isNotBlank() }
                ?.split(MENU_SEPARATOR)
                ?.map { it.trim() }
                ?: emptyList()
        }
    }
    
    private fun parseDateList(dates: org.jsoup.select.Elements): List<LocalDate> {
        return dates.map { dateElement ->
            val dateText = dateElement.text()
                .substringAfter("(")
                .substringBefore(")")
            
            LocalDate.parse(dateText, DATE_FORMATTER)
        }
    }
    
    companion object {
        private val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val DMU_DIET_URL = "https://www.dongyang.ac.kr/dmu/4902/subview.do"
        private const val MENU_SELECTOR = "div.table_1 table tbody tr"
        private const val DATE_SELECTOR = "div.table_1 thead tr th"
        private const val MENU_SEPARATOR = ", "
    }
}
