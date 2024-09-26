package com.dmforu.domain.diet

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DietReader {
    // TODO: Diet 크롤링 정보를 가져와야 함
    fun read(): List<Diet> {
        val list = mutableListOf<Diet>()

        list.add(Diet(LocalDate.now(), listOf("메뉴1", "메뉴2")))
        list.add(Diet(LocalDate.now(), listOf("메뉴1", "메뉴2")))
        list.add(Diet(LocalDate.now(), listOf("메뉴1", "메뉴2")))

        return list
    }
}