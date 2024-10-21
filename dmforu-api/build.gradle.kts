tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":dmforu-domain"))

    runtimeOnly(project(":dmforu-infrastructure:storage:db-mysql"))
    runtimeOnly(project(":dmforu-infrastructure:storage:db-redis"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
}