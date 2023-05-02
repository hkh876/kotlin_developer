package com.example.cardatabase.domain

import jakarta.persistence.*

@Entity
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "owner")
    val car: List<Car> = listOf(),
)