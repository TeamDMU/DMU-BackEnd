package com.dmforu.admin.presentation

import com.dmforu.admin.message.MessageService
import com.dmforu.admin.presentation.request.SendNoticeMessageRequest
import com.dmforu.domain.notice.Notice
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
class PushMessageController(
    private val messageService: MessageService,
) {
    @PostMapping("/api/v1/messages/notice")
    fun sendMessageToEvery(@RequestBody request: SendNoticeMessageRequest) {
        val notice = Notice.of(
            number = 0,
            type = request.type,
            date = LocalDate.now(),
            title = request.title,
            author = "관리자",
            url = request.url
        )

        messageService.sendTestNoticeMessage(request.token, notice)
    }

}