package com.dmforu.admin.message

import com.dmforu.domain.notice.Notice
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class NoticeListener (
    private val messageService: MessageService
) {
    @EventListener
    fun onNoticeMessageSendEventHandler(notice: Notice) {
        messageService.sendNoticeMessage(notice)
    }
}