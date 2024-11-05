package com.dmforu.admin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.scheduling.annotation.EnableScheduling
import java.util.*

@SpringBootApplication(
    scanBasePackages = [
        "com.dmforu.admin",
        "com.dmforu.fcm",
        "com.dmforu.storage.db.mongo",
        "com.dmforu.storage.db.mysql"
    ]
)
@EnableScheduling
class AdminApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"))
    SpringApplicationBuilder(AdminApplication::class.java)
        .run(*args)
}
