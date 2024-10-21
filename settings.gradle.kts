rootProject.name = "DMU-BackEnd"

include(
    "dmforu-api",
    "dmforu-admin",
    "dmforu-domain",
    "dmforu-crawling",
    "dmforu-infrastructure:fcm",
    "dmforu-infrastructure:storage:db-mysql",
    "dmforu-infrastructure:storage:db-redis"
)

pluginManagement {
    val kotlinVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}