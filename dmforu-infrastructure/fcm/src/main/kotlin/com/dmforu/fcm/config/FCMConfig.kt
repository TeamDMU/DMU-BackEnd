package com.dmforu.fcm.config

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.core.io.ClassPathResource
import java.io.IOException

@Configuration
internal class FCMConfig {
    @Bean
    @Throws(IOException::class)
    fun someAppProdFirebaseApp(): FirebaseApp {
        val credentials = GoogleCredentials.fromStream(ClassPathResource("key/fire-base-key.json").inputStream)

        val options = FirebaseOptions
            .builder()
            .setCredentials(credentials)
            .build()

        return FirebaseApp.initializeApp(options, "DMFORU_APP_PROD")
    }

    @Bean
    @DependsOn("someAppProdFirebaseApp")
    fun firebaseMessaging(): FirebaseMessaging {
        return FirebaseMessaging.getInstance(FirebaseApp.getInstance("DMFORU_APP_PROD"))
    }

}