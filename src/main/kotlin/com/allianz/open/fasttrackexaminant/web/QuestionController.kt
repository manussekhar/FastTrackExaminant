package com.allianz.open.fasttrackexaminant.web

import com.allianz.open.fasttrackexaminant.dto.QuestionRequest
import com.allianz.open.fasttrackexaminant.dto.QuestionResponse
import com.allianz.open.fasttrackexaminant.model.Question
import com.allianz.open.fasttrackexaminant.service.QuestionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class QuestionController {

    @Autowired
    private lateinit var questionService: QuestionService

    @PostMapping("/Question")
    fun createQuestion(@RequestBody question: QuestionRequest): QuestionResponse = questionService.createQuestion(question)

    @GetMapping("/Question/{id}")
    fun getQuestion(@PathVariable id: Int): Question = questionService.retrieveQuestion(id)

    @PutMapping("/Question/{id}")
    fun updateQuestion(@RequestBody question: QuestionRequest, @PathVariable id: Int): QuestionResponse = questionService.updateQuestion(question, id)
}