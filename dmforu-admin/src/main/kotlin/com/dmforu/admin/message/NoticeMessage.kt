package com.dmforu.admin.message

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
}

