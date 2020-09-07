package com.allianz.open.fasttrackexaminant.web

import com.allianz.open.fasttrackexaminant.dto.QuestionRequest
import com.allianz.open.fasttrackexaminant.dto.QuestionResponse
import com.allianz.open.fasttrackexaminant.service.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class QuestionController {

    @Autowired
    private lateinit var questionService: QuestionService

    @PostMapping("/Question")
    fun createQuestion(@RequestBody question: QuestionRequest): QuestionResponse {
        return questionService.createQuestion(question)
    }
}