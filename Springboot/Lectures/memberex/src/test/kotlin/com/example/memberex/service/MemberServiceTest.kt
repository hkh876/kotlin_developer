package com.example.memberex.service

import com.example.memberex.domain.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired

class MemberServiceTest {
    @Autowired
    private lateinit var memberService: MemberService

    @Test
    fun 회원가입() {
        // given
        val member = Member(name = "hello")

        // when
        val saveId = memberService.join(member)

        // then
        val findMember = memberService.findOne(saveId).get()
        assertThat(findMember.name).isEqualTo(member.name)
    }

    @Test
    fun 중복_회원_예외() {
        // given
        val member1 = Member(name = "spring")
        val member2 = Member(name = "spring")

        // when
        memberService.join(member1)

        // then
        val e: IllegalStateException = assertThrows<IllegalStateException> { memberService.join(member2) }
        assertThat(e.message).isEqualTo("이미 존재하는 회원 입니다.")
    }
}