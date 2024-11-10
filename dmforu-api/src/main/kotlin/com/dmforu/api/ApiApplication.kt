package com.dmforu.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.util.*

@ConfigurationPropertiesScan
@SpringBootApplication(
    scanBasePackages = [
        "com.dmforu.api",
        "com.dmforu.domain",
        "com.dmforu.storage.db.mongo",
        "com.dmforu.storage.db.mysql"
    ]
)
class ApiApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    runApplication<ApiApplication>(*args)
}
