val jsoupVersion: String by project

dependencies {
    compileOnly(project(":dmforu-domain"))

    implementation("org.jsoup:jsoup:${jsoupVersion}")

    testImplementation(project(":dmforu-domain"))
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
    testImplementation("org.assertj:assertj-core")
}