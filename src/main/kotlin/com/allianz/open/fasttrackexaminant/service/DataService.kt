package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dao.QuestionRepository
import com.allianz.open.fasttrackexaminant.dto.QuestionRequest
import com.allianz.open.fasttrackexaminant.dto.QuestionResponse
import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.NoSuchElementException

@Service
class DataService {

    @Autowired
    private lateinit var questionRepository: QuestionRepository

    fun persistQuestion(question: Question): QuestionResponse {
        return QuestionResponse(questionRepository.save(question).id)
    }

    fun retrieveQuestion(id: Int): Question {
        return try {
            questionRepository.findById(id).get()
        } catch (e: NoSuchElementException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Question with id $id is not present.")
        }
    }

}