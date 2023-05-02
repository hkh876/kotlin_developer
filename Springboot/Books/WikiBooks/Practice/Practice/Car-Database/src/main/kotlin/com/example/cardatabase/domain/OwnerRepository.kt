package com.example.cardatabase.domain

import org.springframework.data.repository.CrudRepository
import java.util.*

interface OwnerRepository : CrudRepository<Owner, Long> {
    // For test
    fun findByFirstName(firstName: String): Optional<Owner>
}