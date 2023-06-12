package com.example.memberex.repository

import com.example.memberex.domain.Member
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class MemoryMemberRepositoryTest {
    @Autowired
    private lateinit var repository: JpaMemberRepository

    @Test
    fun save() {
        val member = Member(name = "spring")
        repository.save(member)

        val result = repository.findById(member.id).get()
        assertThat(result).isEqualTo(member)
    }

    @Test
    fun findByName() {
        val member1 = Member(name = "spring1")
        repository.save(member1)

        val member2 = Member(name = "spring2")
        repository.save(member2)

        val result = repository.findByName("spring1").get()
        assertThat(result).isEqualTo(member1)
    }

    @Test
    fun findAll() {
        val member1 = Member(name = "spring1")
        repository.save(member1)

        val member2 = Member(name = "spring2")
        repository.save(member2)

        val result = repository.findAll()
        assertThat(result.size).isEqualTo(2)
    }
}