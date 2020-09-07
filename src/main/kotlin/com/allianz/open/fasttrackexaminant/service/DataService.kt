package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dao.QuestionRepository
import com.allianz.open.fasttrackexaminant.dto.QuestionResponse
import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DataService {

    @Autowired
    private lateinit var questionRepository: QuestionRepository

    fun saveQuestion(question: Question): QuestionResponse{
        return QuestionResponse(questionRepository.save(question).id)
    }

    fun retrieveQuestion(id:Int):Question{
        return questionRepository.findById(id).get()
    }
}