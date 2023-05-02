package com.example.cardatabase.auth

import com.example.cardatabase.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class AuthenticationFilter(
    val jwtService: JwtService
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Authorization 헤더에서 토큰을 가져옴
        val jws = request.getHeader(HttpHeaders.AUTHORIZATION)

        if (jws != null) {
            // 토큰을 확인하고 사용자를 얻음
            val user = jwtService.getAuthUser(request)
            val authentication = UsernamePasswordAuthenticationToken(
                user,
                null,
                emptyList()
            )

            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}