package com.example.memberex.repository

import com.example.memberex.domain.Member
import java.util.Optional

interface MemberRepository {
    fun save(member: Member) : Member
    fun findById(id: Long) : Optional<Member>
    fun findByName(name: String): Optional<Member>
    fun findAll() : List<Member>
}