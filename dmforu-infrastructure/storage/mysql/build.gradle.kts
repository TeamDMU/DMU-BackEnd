allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.18.0")

    compileOnly(project(":dmforu-domain"))

    runtimeOnly ("com.mysql:mysql-connector-j")

    testImplementation("com.h2database:h2")
    testImplementation(project(":dmforu-domain"))
}