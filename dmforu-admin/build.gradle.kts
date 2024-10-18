tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":dmforu-domain"))
    implementation(project(":dmforu-crawling"))

    runtimeOnly(project(":dmforu-fcm"))
    runtimeOnly(project(":dmforu-storage:db-mysql"))
    runtimeOnly(project(":dmforu-storage:db-redis"))
}