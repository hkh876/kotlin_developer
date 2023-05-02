package com.example.cardatabase

import com.example.cardatabase.domain.Owner
import com.example.cardatabase.domain.OwnerRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OwnerRepositoryTest {
    @Autowired
    private lateinit var ownerRepository: OwnerRepository

    @Test
    fun saveOwner() {
        ownerRepository.save(
            Owner(
                firstName = "Lucy",
                lastName = "Smith"
            )
        )
        assertThat(ownerRepository.findByFirstName("Lucy").isPresent).isTrue
    }

    @Test
    fun deleteOwner() {
        saveOwner()

        ownerRepository.deleteAll()
        assertThat(ownerRepository.count()).isEqualTo(0)
    }
}