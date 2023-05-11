package com.example.examtestapp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.csrf().disable().cors().configurationSource { request ->
            val cors = CorsConfiguration()
            cors.allowedOrigins = listOf("*")
            cors.allowedMethods = listOf("*")
            cors.allowedHeaders = listOf("*")
            cors
        }
        return http.build()
    }
}
