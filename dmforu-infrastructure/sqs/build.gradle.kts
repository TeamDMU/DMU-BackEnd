dependencies {
    compileOnly(project(":dmforu-domain"))

    implementation(enforcedPlatform("software.amazon.awssdk:bom:2.21.20"))
    implementation("software.amazon.awssdk:sqs")

    testImplementation(project(":dmforu-domain"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}