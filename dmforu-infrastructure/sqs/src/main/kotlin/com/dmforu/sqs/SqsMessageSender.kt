package com.dmforu.sqs

import com.dmforu.domain.message.MessageSender
import com.dmforu.domain.message.NoticeMessage
import com.dmforu.sqs.SqsMessageMapper.createAttributes
import com.dmforu.sqs.SqsMessageMapper.createRequest
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.sqs.SqsAsyncClient
import software.amazon.awssdk.services.sqs.model.SendMessageResponse
import software.amazon.awssdk.services.sqs.model.SqsException

@Component
class SqsMessageSender(
    private val sqsAsyncClient: SqsAsyncClient,
    private val awsConfig: AWSConfig,
) : MessageSender {

    override fun sendNoticeMessage(message: NoticeMessage, tokens: List<String>) {
        val attributes = createAttributes(
            tokens = tokens,
            title = message.title,
            type = message.type,
            url = message.url
        )

        val request = createRequest(
            content = message.body,
            attributes = attributes,
            queueUrl = awsConfig.queueUrl,
            delaySeconds = awsConfig.messageDelaySecs
        )

        try {
            val future = sqsAsyncClient.sendMessage(request)

            future.whenComplete { sendMessageResponse: SendMessageResponse, throwable: Throwable? ->
                if (throwable != null) {
                    throw SqsMessageSendException("[SQS Async Client] 메세지 전송에 실패하였습니다.", throwable)
                }
            }
        } catch (sqsException: SqsException) {
            throw SqsMessageSendException("[SQS] 메세지 전송에 실패하였습니다.", sqsException)
        }
    }


}