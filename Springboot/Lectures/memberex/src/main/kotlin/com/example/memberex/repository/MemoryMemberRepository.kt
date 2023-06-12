package com.example.memberex.repository

import com.example.memberex.domain.Member
import org.springframework.stereotype.Repository
import java.util.*

//@Repository
class MemoryMemberRepository(
    private val store: MutableMap<Long, Member> = mutableMapOf(),
    private var sequence: Long = 0L
) : MemberRepository {

    override fun save(member: Member): Member {
        member.id = ++sequence
        store[member.id] = member

        return member
    }

    override fun findById(id: Long): Optional<Member> {
        return Optional.ofNullable(store[id])
    }

    override fun findByName(name: String): Optional<Member> {
        return store.values.stream()
            .filter { member -> member.name.equals(name) }
            .findAny()
    }

    override fun findAll(): List<Member> {
        return store.values.toList()
    }
}