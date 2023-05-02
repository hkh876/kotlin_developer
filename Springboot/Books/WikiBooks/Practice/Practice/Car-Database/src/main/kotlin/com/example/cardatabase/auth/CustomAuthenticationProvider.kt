package com.example.cardatabase.auth

import com.example.cardatabase.service.UserDetailsServiceImpl
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    val userDetailService: UserDetailsServiceImpl
) : AuthenticationProvider {
    override fun authenticate(authentication: Authentication?): Authentication {
        val authToken = authentication as UsernamePasswordAuthenticationToken

        if (userDetailService.checkPassword(authToken.principal.toString(), authToken.credentials.toString())) {
            return authentication
        } else {
            throw BadCredentialsException("Username and password not match")
        }
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return true
    }
}