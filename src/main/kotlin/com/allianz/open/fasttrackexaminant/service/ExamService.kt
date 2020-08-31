package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.model.Answer
import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class ExamService {

    @Autowired
    private lateinit var dataService: DataService

    /**
     * Persist a Question with Answers to the data source
     *
     * @param questionText
     * @param answerTexts
     * @param correctAnswer
     * @param pointsForCorrectAnswer
     * @param topic
     * @param difficulty
     */
    fun createQuestion(
            questionText: String,
            answerTexts: List<String>,
            correctAnswer: List<Int>,
            pointsForCorrectAnswer: Int,
            topic: String,
            difficulty: String
    ): Question {

        val answers = answerTexts.mapIndexed { idx, value -> Answer(value, correctAnswer.contains(idx)) }

        if (answers.all { !it.correctAnswer }) {throw IllegalArgumentException("At least one Answer must be correct.")}

        val question = Question(
                questionText,
                answers,
                pointsForCorrectAnswer,
                topic,
                try {
                    Difficulty.valueOf(difficulty)
                } catch (e: IllegalArgumentException) {
                    Difficulty.BEGINNER
                })

        dataService.saveQuestion(question)

        return question

    }
}