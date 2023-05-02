package com.example.cardatabase.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JwtService {
    fun getToken(username: String): String {
        val expiredDate = Date(System.currentTimeMillis() + EXPIRATION_TIME)
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(expiredDate)
            .signWith(SECRET_KEY)
            .compact()
    }

    fun getAuthUser(request: HttpServletRequest): String? {
        val token = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (token.isNotEmpty()) {
            val user = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token.replace(PREFIX, ""))
                .body
                .subject

            if (user.isNotEmpty()) {
                return user
            }
        }

        return null
    }

    companion object {
        const val EXPIRATION_TIME = 24 * 60 * 60 * 1000 // 24 hours
        const val PREFIX = "Bearer"
        val SECRET_KEY: Key = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    }
}