package com.example.cardatabase.service

import com.example.cardatabase.domain.UserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.User.UserBuilder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder
) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        try {
            val user = userRepository.findByUsername(username)
            val builder: UserBuilder = User.withUsername(username)

            builder.password(user.password)
            builder.roles(user.role)

            return builder.build()
        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found")
        }
    }

    fun checkPassword(username: String, password: String): Boolean {
        try {
            val user = userRepository.findByUsername(username)
            if (passwordEncoder.matches(password, user.password)) {
                return true
            }

        } catch (e: Exception) {
            throw UsernameNotFoundException("User not found")
        }

        return false
    }

    fun getEncodedPassword(password: String): String {
        return passwordEncoder.encode(password)
    }
}