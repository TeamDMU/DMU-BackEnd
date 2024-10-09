package com.dmforu.api.controller.v1.response

import com.dmforu.domain.notice.Notice
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate

data class NoticeResponse(
    @Schema(description = "날짜", example = "2024-05-09")
    val date: LocalDate,

    @Schema(description = "제목", example = "시흥시인재양성재단 24년 상반기 장학생 선발 안내")
    val title: String,

    @Schema(description = "작성자", example = "최지예")
    val author: String,

    @Schema(
        description = "URL",
        example = "https://www.dongyang.ac.kr/bbs/dongyang/7/124420/artclView.do?layout=unknown"
    )
    val url: String,
) {
    companion object {
        fun form(notice: Notice): NoticeResponse {
            return NoticeResponse(
                date = notice.date,
                title = notice.title,
                author = notice.author,
                url = notice.url
            )
        }
    }
}