package com.dmforu.domain.diet

import com.dmforu.domain.Generated
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

    @Generated
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Diet

        if (date != other.date) return false
        if (menus != other.menus) return false

        return true
    }

    @Generated
    override fun hashCode(): Int {
        var result = date.hashCode()
        result = 31 * result + menus.hashCode()
        return result
    }

    @Generated
    override fun toString(): String {
        return "Diet(date=$date, menus=$menus)"
    }

}
