package com.example.examtestapp.web

import com.example.examtestapp.domain.Exam
import com.example.examtestapp.domain.ExamDto
import com.example.examtestapp.domain.ExamRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class ExamController(
    val examRepository: ExamRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(ExamController::class.java)

    @RequestMapping(value = [EXAM_GET_ALL_URL], method = [RequestMethod.GET])
    fun getExams(
        @RequestParam(name = "type", required = false) questionType: String?,
        @RequestParam(name = "save_status", required = false) saveStatus: Boolean?,
        @RequestParam(name = "id", required = false) examId: Long?
    ): ResponseEntity<Any?> {
        return if (saveStatus == true) {
            ResponseEntity.ok(examRepository.findBySaveStatus(saveStatus))
        } else {
            if (questionType?.isNotEmpty() == true) {
                ResponseEntity.ok(examRepository.findByQuestionType(questionType))
            } else if (examId != null) {
                ResponseEntity.ok(mutableListOf(examRepository.findById(examId)))
            }
            else {
                ResponseEntity.badRequest().build()
            }
        }
    }

    @RequestMapping(value = [EXAM_PATCH_SAVE_STATUS_BY_ID_URL], method = [RequestMethod.PATCH])
    fun patchExams(@RequestBody request: ExamDto, @PathVariable examId: Long): ResponseEntity<Any?> {
        logger.info("request id : $examId")

        val exam = examRepository.findById(examId)
        return if (exam.isPresent) {
            val targetExam = exam.get()
            targetExam.saveStatus = request.saveStatus
            examRepository.save(targetExam)

            ResponseEntity.ok(targetExam)
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    companion object {
        private const val EXAM_DOMAIN_BASE_URL = "/api/v1"
        const val EXAM_GET_ALL_URL = "$EXAM_DOMAIN_BASE_URL/exams"
        const val EXAM_PATCH_SAVE_STATUS_BY_ID_URL = "$EXAM_DOMAIN_BASE_URL/exams/{examId}"
    }
}