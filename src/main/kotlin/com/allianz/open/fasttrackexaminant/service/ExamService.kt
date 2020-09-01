package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dto.QuestionDTO
import com.allianz.open.fasttrackexaminant.model.Answer
import com.allianz.open.fasttrackexaminant.model.Question
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExamService {

    @Autowired
    private lateinit var dataService: DataService

    private val logger =  LoggerFactory.getLogger(ExamService::class.java)


    /**
     * Persist a Question with Answers to the data source
     *
     * @param questionDTO
     * @return Question Model
     */
    fun createQuestion(questionDTO: QuestionDTO): Question {

        val answers = questionDTO.answerTexts.mapIndexed { idx, value -> Answer(value, questionDTO.correctAnswer.contains(idx)) }

        if (answers.all { !it.correctAnswer }) {
            throw IllegalArgumentException("At least one Answer must be correct.")
        }

        val question = Question(
                questionDTO.questionText,
                answers,
                questionDTO.pointsForCorrectAnswer,
                questionDTO.topic,
                try {
                    Difficulty.valueOf(questionDTO.difficulty)
                } catch (e: IllegalArgumentException) {
                    logger.warn("Invalid difficulty received. Defaulting to BEGINNER")
                    Difficulty.BEGINNER
                }
        )

        dataService.saveQuestion(question)

        return question

    }
}