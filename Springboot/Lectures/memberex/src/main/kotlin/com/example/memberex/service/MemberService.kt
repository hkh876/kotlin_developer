package com.example.memberex.service

import com.example.memberex.domain.Member
import com.example.memberex.repository.MemberRepository
import jakarta.transaction.Transactional
import java.util.*

@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {
    // 회원 가입
    fun join(member: Member): Long {
        validateDuplicateMember(member) // 중복 회원 검증
        memberRepository.save(member)
        return member.id
    }

    private fun validateDuplicateMember(member: Member) {
        val result = memberRepository.findByName(member.name)
        if (result.isPresent) {
            throw IllegalStateException("이미 존재하는 회원 입니다.")
        }
    }

    // 전체 회원 조회
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Optional<Member> {
        return memberRepository.findById(memberId)
    }
}