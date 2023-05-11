package com.example.examtestapp.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RestResource

@RestResource(exported = false)
interface ExamRepository : CrudRepository<Exam, Long> {
    fun findBySaveStatus(saveStatus: Boolean): MutableList<Exam>
    fun findByQuestionType(questionType: String): MutableList<Exam>
}