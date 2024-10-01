val jsoupVersion: String by project

dependencies {
    implementation(project(":dmforu-domain"))
    implementation("org.jsoup:jsoup:${jsoupVersion}")

    compileOnly("org.springframework:spring-context")
}