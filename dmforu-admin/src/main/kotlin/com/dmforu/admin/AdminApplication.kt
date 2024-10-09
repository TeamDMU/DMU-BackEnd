package com.dmforu.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(
    scanBasePackages = [
        "com.dmforu.admin",
        "com.dmforu.domain",
        "com.dmforu.crawling",
        "com.dmforu.storage.db.redis",
        "com.dmforu.storage.db.mysql"
    ]
)
@EnableScheduling
class AdminApplication

fun main(args: Array<String>) {
    runApplication<AdminApplication>(*args)
}
