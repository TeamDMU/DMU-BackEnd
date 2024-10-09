package com.dmforu.domain.notice

import java.time.LocalDate

data class Notice(
    // 공지사항 번호
    val number: Int,

    // 공지사항 종류
    val type: String,

    // 공지사항 날짜
    val date: LocalDate,

    // 공지사항 제목
    val title: String,

    // 공지사항 작성자
    val author: String,

    // 공지사항 URL
    val url: String
) {
    fun isNumberLessThanOrEqualTo(number: Int): Boolean {
        return this.number <= number
    }

    fun isLastInType(): Boolean{
        return this.number == 1
    }
}