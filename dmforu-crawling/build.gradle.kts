val jsoupVersion: String by project

dependencies {
    compileOnly(project(":dmforu-domain"))
    implementation("org.jsoup:jsoup:${jsoupVersion}")

    compileOnly("org.springframework:spring-context")
}