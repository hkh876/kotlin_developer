package com.example.examtestapp.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Choice(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    val content: String = "",

    @Column(length = 1000)
    val tip: String = "",

    @Column(name = "captured_tip", columnDefinition = "tinyint(1)")
    val capturedTip: Boolean = false,

    @Column(name = "image_url")
    val imageUrl: String = "",

    @Column(name = "exam_id")
    val examId: Long = 0,
)