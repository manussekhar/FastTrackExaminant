package com.allianz.open.fasttrackexaminant.web

import com.allianz.open.fasttrackexaminant.dto.QuestionDTO
import com.allianz.open.fasttrackexaminant.service.ExamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("exam")
class ExamController {

    @Autowired
    private lateinit var examService: ExamService

    @PostMapping("/createQuestion")
    fun createQuestion(@RequestBody question: QuestionDTO): ResponseEntity<String> {
        examService.createQuestion(question)
        return ResponseEntity.accepted().body("Question created")
    }
}