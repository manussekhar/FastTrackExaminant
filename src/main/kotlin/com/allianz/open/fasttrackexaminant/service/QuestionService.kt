package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dto.QuestionRequest
import com.allianz.open.fasttrackexaminant.dto.QuestionResponse
import com.allianz.open.fasttrackexaminant.model.Answer
import com.allianz.open.fasttrackexaminant.model.Question
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException


@Service
class QuestionService {

    @Autowired
    private lateinit var dataService: DataService

    private val logger = LoggerFactory.getLogger(QuestionService::class.java)


    /**
     * Persist a Question with Answers to the data source
     *
     * @param questionRequest
     * @return Question Model
     */
    fun createQuestion(questionRequest: QuestionRequest): QuestionResponse {

        val question = map(questionRequest)

        return dataService.persistQuestion(question)

    }

    fun updateQuestion(questionRequest: QuestionRequest, id: Int): QuestionResponse {
        val question = map(questionRequest, id)
        return dataService.persistQuestion(question)
    }

    fun retrieveQuestion(id: Int): Question {
        return dataService.retrieveQuestion(id)
    }


    private fun map(questionRequest: QuestionRequest, id: Int = 0): Question {
        val answers = questionRequest.answerTexts.mapIndexed { idx, value -> Answer(0, value, questionRequest.correctAnswer.contains(idx)) }

        if (answers.all { !it.correctAnswer }) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "At least one Answer must be correct.")
        }

        val question = Question(id,
                questionRequest.questionText,
                questionRequest.pointsForCorrectAnswer,
                questionRequest.topic,
                try {
                    Difficulty.valueOf(questionRequest.difficulty).name
                } catch (e: IllegalArgumentException) {
                    logger.warn("Invalid difficulty received. Defaulting to BEGINNER")
                    Difficulty.BEGINNER.name
                }, answers
        )
        return question
    }
}