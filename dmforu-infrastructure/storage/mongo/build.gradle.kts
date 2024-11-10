dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    compileOnly(project(":dmforu-domain"))

    testImplementation(project(":dmforu-domain"))
}