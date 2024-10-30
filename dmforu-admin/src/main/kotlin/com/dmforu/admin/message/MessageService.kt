package com.dmforu.admin.message

import com.dmforu.domain.message.MessageSender
import com.dmforu.domain.message.NoticeMessage
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.subscribe.SubscribeReader
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val subscribeReader: SubscribeReader,
    private val keywordFilter: KeywordFilter,
    private val messageSender: MessageSender,
) {

    fun sendNoticeMessage(notice: Notice) {
        if (notice.isUniversityNotice()) {
            sendUniversityNoticeMessage(notice)

            return
        }

        sendDepartmentNoticeMessage(notice)
    }

    private fun sendUniversityNoticeMessage(notice: Notice) {
        val keyword = keywordFilter.extractKeywordFrom(notice.title) ?: return

        val tokens = subscribeReader.getTokensBySubscribedToKeyword(keyword = keyword)

        if (tokens.isEmpty()) {
            return
        }

        val message = NoticeMessage.createUniversityNoticeMessage(notice = notice, keyword = keyword)

        messageSender.sendNoticeMessage(message = message, tokens = tokens)
    }

    private fun sendDepartmentNoticeMessage(notice: Notice) {
        val tokens = subscribeReader.getTokensBySubscribedToDepartment(department = notice.type)

        if (tokens.isEmpty()) {
            return
        }

        val message = NoticeMessage.createDepartmentNoticeMessage(notice = notice)

        messageSender.sendNoticeMessage(message = message, tokens = tokens)
    }


}