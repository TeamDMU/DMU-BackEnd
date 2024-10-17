package com.dmforu.admin.message

import com.dmforu.admin.message.filter.keywordFilter
import com.dmforu.domain.notice.Notice
import com.dmforu.domain.subscribe.SubscribeReader
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val subscribeReader: SubscribeReader,
    private val messageSender: MessageSender,
) {

    fun sendNoticeMessage(notice: Notice) {
        if (notice.isUniversityNotice()) {
            sendUniversityNoticeMessage(notice)
        }

        sendDepartmentNoticeMessage(notice)
    }

    private fun sendUniversityNoticeMessage(notice: Notice) {
        val keyword = keywordFilter(notice.title) ?: return

        val tokens = subscribeReader.getTokensBySubscribedToKeyword(keyword = keyword)

        val message = NoticeMessage.createUniversityNoticeMessage(notice = notice, keyword = keyword)

        messageSender.sendNoticeMessage(message = message, tokens = tokens)
    }

    private fun sendDepartmentNoticeMessage(notice: Notice) {
        val tokens = subscribeReader.getTokensBySubscribedToDepartment(department = notice.type)

        val message = NoticeMessage.createDepartmentNoticeMessage(notice = notice)

        messageSender.sendNoticeMessage(message = message, tokens = tokens)
    }
}