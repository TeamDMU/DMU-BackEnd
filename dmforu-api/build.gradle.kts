tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {
    implementation(project(":dmforu-domain"))
    runtimeOnly(project(":dmforu-storage:db-mysql"))

    implementation("org.springframework.boot:spring-boot-starter-web")
}