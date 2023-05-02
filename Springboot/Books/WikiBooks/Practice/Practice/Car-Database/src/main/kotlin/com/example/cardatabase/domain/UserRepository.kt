package com.example.cardatabase.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(exported = false)
interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String?): User
}