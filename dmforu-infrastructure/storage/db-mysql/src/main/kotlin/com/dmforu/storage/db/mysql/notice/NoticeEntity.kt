package com.dmforu.storage.db.mysql.notice

import com.dmforu.domain.notice.Notice
import jakarta.persistence.*

import java.time.LocalDate

@Table(name = "notice")
@Entity
internal class NoticeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    // 공지사항 번호
    val number: Int,

    // 공지사항 타입
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
    constructor() : this(null, 0, "", LocalDate.now(), "", "", "")

    companion object {
        fun from(notice: Notice): NoticeEntity {
            return NoticeEntity(
                number = notice.number,
                type = notice.type,
                date = notice.date,
                title = notice.title,
                author = notice.author,
                url = notice.url
            )
        }
    }

    fun toNotice(): Notice {
        return Notice.of(
            number = this.number,
            type = this.type,
            date = this.date,
            title = this.title,
            author = this.author,
            url = this.url
        )
    }
}
