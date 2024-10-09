package com.dmforu.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.dmforu.admin",
        "com.dmforu.domain",
        "com.dmforu.crawling",
        "com.dmforu.storage.db.redis",
        "com.dmforu.storage.db.mysql"
    ]
)
class AdminApplication

fun main(args: Array<String>) {
    runApplication<AdminApplication>(*args)
}
