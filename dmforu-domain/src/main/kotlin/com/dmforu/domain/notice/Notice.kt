package com.dmforu.domain.notice

import java.time.LocalDate

class Notice private constructor(
    val number: Int,
    val type: String,
    val date: LocalDate,
    val title: String,
    val author: String,
    val url: String,
) {
    companion object {
        fun of(number: Int, type: String, date: LocalDate, title: String, author: String, url: String): Notice {
            return Notice(
                number = number,
                type = type,
                date = date,
                title = title,
                author = author,
                url = url
            )
        }
    }

    fun isNumberLessThanOrEqualTo(number: Int): Boolean {
        return this.number <= number
    }

    fun isLastInType(): Boolean {
        return this.number == 1
    }

    fun isUniversityNotice(): Boolean {
        return "대학" == this.type
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Notice

        if (number != other.number) return false
        if (type != other.type) return false
        if (date != other.date) return false
        if (title != other.title) return false
        if (author != other.author) return false
        if (url != other.url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = number
        result = 31 * result + type.hashCode()
        result = 31 * result + date.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + author.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }

    override fun toString(): String {
        return "Notice(number=$number, type='$type', date=$date, title='$title', author='$author', url='$url')"
    }

}