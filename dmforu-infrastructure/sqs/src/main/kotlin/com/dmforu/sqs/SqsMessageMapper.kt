package com.dmforu.sqs

import software.amazon.awssdk.services.sqs.model.MessageAttributeValue
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

object SqsMessageMapper {

    fun createRequest(content: String, attributes: Map<String, MessageAttributeValue>, queueUrl: String, delaySeconds: Int): SendMessageRequest {
        return SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .delaySeconds(delaySeconds)
            .messageAttributes(attributes)
            .messageBody(content)
            .build()
    }

    fun createAttributes(
        tokens: List<String>,
        title: String,
        type: String,
        url: String,
    ): Map<String, MessageAttributeValue> {
        val attributes = mutableMapOf<String, MessageAttributeValue>()

        attributes["tokens"] = MessageAttributeValue.builder().stringValue(tokens.toString()).dataType("String").build()
        attributes["title"] = MessageAttributeValue.builder().stringValue(title).dataType("String").build()
        attributes["type"] = MessageAttributeValue.builder().stringValue(type).dataType("String").build()
        attributes["url"] = MessageAttributeValue.builder().stringValue(url).dataType("String").build()

        return attributes
    }
}