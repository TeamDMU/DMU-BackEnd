package com.dmforu.domain.diet

import java.time.LocalDate

class Diet private constructor(
    val date: LocalDate,
    val menus: List<String>,
) {
    companion object {
        fun of(date: LocalDate, menus: List<String>): Diet {
            return Diet(date, menus)
        }
    }
}
