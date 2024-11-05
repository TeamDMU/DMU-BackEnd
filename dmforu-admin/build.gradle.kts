tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":dmforu-domain"))
    implementation(project(":dmforu-crawling"))

    runtimeOnly(project(":dmforu-infrastructure:fcm"))
    runtimeOnly(project(":dmforu-infrastructure:storage:mysql"))
    runtimeOnly(project(":dmforu-infrastructure:storage:mongo"))
    runtimeOnly(project(":dmforu-support:monitoring"))

    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}