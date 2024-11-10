package com.dmforu.sqs

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sqs.SqsAsyncClient

@Configuration
class AWSConfig(
    @Value("\${cloud.aws.credentials.access-key}")
    val accessKey: String,

    @Value("\${cloud.aws.credentials.secret-key}")
    val secretKey: String,

    @Value("\${cloud.aws.sqs.queue.url}")
    val queueUrl: String,

    @Value("\${cloud.aws.sqs.queue.message-delay-seconds}")
    val messageDelaySecs: Int,
) {

    @Bean
    fun sqsAsyncClient(): SqsAsyncClient {
        return SqsAsyncClient.builder()
            .region(Region.AP_NORTHEAST_2)
            .credentialsProvider(createAwsCredentialsProvider())
            .build()
    }

    private fun createAwsCredentialsProvider(): StaticCredentialsProvider {
        val basicAwsCredentials = AwsBasicCredentials.create(accessKey, secretKey)

        return StaticCredentialsProvider.create(basicAwsCredentials)
    }
}