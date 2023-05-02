package com.example.cardatabase.domain

import jakarta.persistence.*

@Entity
class Car (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val brand: String = "",
    val model: String = "",
    val color: String = "",
    val registerNumber: String = "",

    @Column(name="`year`")
    val year: Int = 0,
    val price: Int = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="owner")
    val owner: Owner,
)