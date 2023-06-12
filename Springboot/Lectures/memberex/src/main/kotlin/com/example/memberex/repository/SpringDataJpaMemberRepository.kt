package com.example.memberex.repository

import com.example.memberex.domain.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SpringDataJpaMemberRepository : JpaRepository<Member, Long>, MemberRepository {
    override fun findByName(name: String): Optional<Member>
}