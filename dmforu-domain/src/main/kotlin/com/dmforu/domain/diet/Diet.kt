package com.dmforu.domain.diet

import java.time.LocalDate

data class Diet(
    val date: LocalDate,
    val menus: List<String>
)
