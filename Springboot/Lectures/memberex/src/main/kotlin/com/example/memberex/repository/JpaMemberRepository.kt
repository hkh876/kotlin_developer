package com.example.memberex.repository

import com.example.memberex.domain.Member
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository
import java.util.*

//@Repository
class JpaMemberRepository(
    private val entityManager: EntityManager
) : MemberRepository {
    override fun save(member: Member): Member {
        entityManager.persist(member)
        return member
    }

    override fun findById(id: Long): Optional<Member> {
        val member = entityManager.find(Member::class.java, id)
        return Optional.ofNullable(member)
    }

    override fun findByName(name: String): Optional<Member> {
        val result = entityManager.createQuery(
            "select m from Member m where m.name = :name",
            Member::class.java
        ).setParameter("name", name)
            .resultList

        return result.stream().findAny()
    }

    override fun findAll(): List<Member> {
        return entityManager.createQuery("select m from Member m", Member::class.java)
            .resultList
    }
}