tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":dmforu-domain"))
    implementation(project(":dmforu-crawling"))
    implementation(project(":dmforu-support:monitoring"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    runtimeOnly(project(":dmforu-infrastructure:sqs"))
    runtimeOnly(project(":dmforu-infrastructure:storage:mysql"))
    runtimeOnly(project(":dmforu-infrastructure:storage:mongo"))

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}