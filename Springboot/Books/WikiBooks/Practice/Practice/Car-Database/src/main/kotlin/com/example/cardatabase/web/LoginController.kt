package com.example.cardatabase.web

import com.example.cardatabase.domain.AccountCredentials
import com.example.cardatabase.service.JwtService
import com.example.cardatabase.service.JwtService.Companion.PREFIX
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(
    val jwtService: JwtService,
    val authenticationProvider: AuthenticationProvider
) {
    @RequestMapping(value = ["/login"], method = [RequestMethod.POST])
    fun getToken(@RequestBody credentials: AccountCredentials): ResponseEntity<Any?> {
        val creds = UsernamePasswordAuthenticationToken(credentials.username, credentials.password)
        val auth = authenticationProvider.authenticate(creds)

        // 토큰 생성
        val jwts = jwtService.getToken(auth.name)

        return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, "$PREFIX $jwts")
            .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, EXPOSE_HEADERS)
            .build()
    }

    companion object {
        const val EXPOSE_HEADERS = "Authorization"
    }
}