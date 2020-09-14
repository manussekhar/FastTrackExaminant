package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dao.CandidateRepository
import com.allianz.open.fasttrackexaminant.dao.DriveRepository
import com.allianz.open.fasttrackexaminant.dao.QuestionRepository
import com.allianz.open.fasttrackexaminant.model.Drive
import com.allianz.open.fasttrackexaminant.model.Question
import com.allianz.open.fasttrackexaminant.util.validateDataAvailability
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class DataService {

    @Autowired
    private lateinit var questionRepository: QuestionRepository


    @Autowired
    private lateinit var driveRepository: DriveRepository


    fun persistQuestion(question: Question): Question {
        return questionRepository.save(question)
    }

    fun retrieveQuestion(id: Int): Question = validateDataAvailability({ questionRepository.findById(id).get() }, "Question with id $id is not present.")


    fun persistExam(drive: Drive): Drive = driveRepository.save(drive)


    fun getAllQuestions(): Iterable<Question> = questionRepository.findAll()
    fun getRandomQuestions(numberOfQuestions: Long, averageTimeToAnswer: Long, topic: List<String>, difficulty: String): List<Question> {
        return questionRepository.getQuestions(difficulty, averageTimeToAnswer, topic).shuffled().take(numberOfQuestions.toInt())
    }

    fun getQuestions(numberOfQuestions: Long, averageTimeToAnswer: Long, topic: List<String>, difficulty: String): List<Question> {
        return questionRepository.getQuestions(difficulty, averageTimeToAnswer, topic).take(numberOfQuestions.toInt())
    }

    fun retrieveDrive(id: Int): Drive = validateDataAvailability({ driveRepository.findById(id).get() }, "Failed to retrieve Drive with id: $id")

}