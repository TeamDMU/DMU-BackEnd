plugins {
    id("jacoco-report-aggregation")
}

dependencies {
    jacocoAggregation(project(":dmforu-api"))
    jacocoAggregation(project(":dmforu-admin"))
}

tasks.testCodeCoverageReport {
    reports {
        csv.required = false
        xml.required = true
    }
    classDirectories.setFrom(
        files(
            listOf(
                project(":dmforu-api"),
                project(":dmforu-admin"),
                project(":dmforu-domain"),
                project(":dmforu-crawling"),

                project(":dmforu-infrastructure:fcm"),
                project(":dmforu-infrastructure:storage:mysql"),
                project(":dmforu-infrastructure:storage:mongo"),
            ).map {
                it.fileTree("${it.buildDir}/classes/kotlin/main") {
                    exclude(
                        "**/*Application*",
                        "**/*Config*",
                        "**/*Dto*",
                        "**/*Error*",
                        "**/request/**",
                        "**/response/**",
                        "**/*Request*",
                        "**/*Response*",
                        "**/*Interceptor*",
                        "**/*Exception*",
                        "**/*TestSupport*"
                    )
                }

            }
        )
    )
}