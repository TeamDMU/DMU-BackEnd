package com.dmforu.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun openAPI(): OpenAPI {

        val info = Info()
            .title("DMforU API 명세서").version("v1")
            .description("API 명세서")

        return OpenAPI()
            .components(Components())
            .info(info)
    }
}