package com.dmforu.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.dmforu.api",
        "com.dmforu.domain",
        "com.dmforu.crawling"
    ]
)
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
