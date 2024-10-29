dependencies {
    compileOnly(project(":dmforu-domain"))

    implementation("com.google.firebase:firebase-admin:9.2.0")
    testImplementation(project(":dmforu-domain"))
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
}