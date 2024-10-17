dependencies {
    compileOnly(project(":dmforu-domain"))
    compileOnly(project(":dmforu-admin"))
    implementation ("com.google.firebase:firebase-admin:9.2.0")

    compileOnly("org.springframework:spring-context")
}