package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.model.Answer
import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExamService {

    @Autowired
    lateinit var dataService: DataService

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
    ) {

        val answers: List<Answer> = answerTexts.mapIndexed { idx, value -> Answer(value, correctAnswer.contains(idx)) }

        val question = Question(questionText, answers, pointsForCorrectAnswer, topic, Difficulty.valueOf(difficulty))

        dataService.saveQuestion(question)

    }
}