package com.example.memberex

import com.example.memberex.repository.MemberRepository
import com.example.memberex.service.MemberService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringConfig(
    private val memberRepository: MemberRepository
) {
    @Bean
    fun memberService(): MemberService {
        return MemberService(memberRepository)
    }
}