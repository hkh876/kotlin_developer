package com.example.examtestapp.domain

import jakarta.persistence.*

@Entity
class Exam (
    @Id
    var id: Long = 0,
    val question: String = "",
    val correctNumber: Int = 0,

    @Column(name = "save_status", columnDefinition = "tinyint(1)")
    var saveStatus: Boolean = false,

    @Column(name = "has_passage", columnDefinition = "tinyint(1)")
    val hasPassage: Boolean = false,

    @Column(name = "image_url")
    val imageUrl: String = "",

    @Column(name = "question_type")
    val questionType: String = "",

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name="exam_id")
    val choices: List<Choice> = listOf(),
)
