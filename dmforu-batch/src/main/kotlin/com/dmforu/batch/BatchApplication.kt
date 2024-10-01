package com.dmforu.batch

import com.dmforu.crawling.diet.DietParser
import com.dmforu.domain.diet.DietRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.dmforu.domain",
        "com.dmforu.crawling",
        "com.dmforu.storage.db.redis"
    ]
)
class BatchApplication

fun main(args: Array<String>) {
    runApplication<BatchApplication>(*args)
}
