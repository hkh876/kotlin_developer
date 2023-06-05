package com.example.restaurantsearch.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        http.cors{}
        return http.build()
    }

    @Bean
    fun corsConfigurationSource() : CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration()

        config.allowedOrigins = listOf("*")
        config.allowedMethods = listOf("*")
        config.allowedHeaders = listOf("*")
        config.allowCredentials = false
        config.applyPermitDefaultValues()

        source.registerCorsConfiguration("/**", config)

        return source
    }
}