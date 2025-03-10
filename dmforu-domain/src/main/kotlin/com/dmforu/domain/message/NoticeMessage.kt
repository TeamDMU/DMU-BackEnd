package com.dmforu.domain.message

import com.dmforu.domain.Generated
import com.dmforu.domain.notice.Notice

class NoticeMessage {
    val title: String
    val type: String
    val body: String
    val url: String

    companion object {
        fun createUniversityNoticeMessage(notice: Notice, keyword: String): NoticeMessage {
            return NoticeMessage(notice, keyword)
        }

        fun createDepartmentNoticeMessage(notice: Notice): NoticeMessage {
            return NoticeMessage(notice)
        }
    }

    private constructor(notice: Notice, keyword: String) {
        title = initTitleFrom(keyword)
        type = notice.type
        body = notice.title
        url = notice.url
    }

    // 학과 공지사항의 경우, "[ 컴퓨터소프트웨어공학과 ] 키워드 알림 도착" 형식으로 제목을 만들어줘야 함
    private constructor(notice: Notice) {
        title = initTitleFrom(notice.type)
        type = notice.type
        body = notice.title
        url = notice.url
    }

    private fun initTitleFrom(keyword: String): String {
        val addedPrefixTitle = StringBuilder()
        return addedPrefixTitle
            .append("[ ").append(keyword).append(" ] ")
            .append(" 키워드 알림 도착").toString()
    }

    @Generated
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NoticeMessage

        if (title != other.title) return false
        if (type != other.type) return false
        if (body != other.body) return false
        if (url != other.url) return false

        return true
    }

    @Generated
    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + url.hashCode()
        return result
    }

    @Generated
    override fun toString(): String {
        return "NoticeMessage(title='$title', type='$type', body='$body', url='$url')"
    }
}

