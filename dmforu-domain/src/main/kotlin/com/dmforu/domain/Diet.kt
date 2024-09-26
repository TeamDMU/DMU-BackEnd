package com.dmforu.domain

import java.time.LocalDate

data class Diet(
    val date: LocalDate,
    val menus: List<String>
)
