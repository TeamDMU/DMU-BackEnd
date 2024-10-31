tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":dmforu-domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

    runtimeOnly(project(":dmforu-infrastructure:storage:mysql"))
    runtimeOnly(project(":dmforu-infrastructure:storage:mongo"))

    testImplementation("org.springframework.boot:spring-boot-starter-validation")
}