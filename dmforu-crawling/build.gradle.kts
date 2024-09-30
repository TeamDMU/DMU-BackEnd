val jsoupVersion: String by project

dependencies {
    implementation("org.jsoup:jsoup:${jsoupVersion}")

    compileOnly("org.springframework:spring-context")
}