package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dto.CandidateResponse
import com.allianz.open.fasttrackexaminant.dto.DriveRequest
import com.allianz.open.fasttrackexaminant.dto.DriveResponse
import com.allianz.open.fasttrackexaminant.model.Candidate
import com.allianz.open.fasttrackexaminant.model.Drive
import com.allianz.open.fasttrackexaminant.service.email.EmailService
import com.allianz.open.fasttrackexaminant.util.Constant
import com.allianz.open.fasttrackexaminant.util.validateQuestionAvailability
import com.allianz.open.fasttrackexaminant.util.validateDate
import com.allianz.open.fasttrackexaminant.util.validateDifficulty
import org.apache.commons.lang3.time.DateUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit


@Service
class DriveService {


    @Autowired
    private lateinit var dataService: DataService

    @Autowired
    private lateinit var questionService: QuestionService

    @Autowired
    private lateinit var emailService: EmailService

    fun schedule(driveRequest: DriveRequest): DriveResponse {
        val startTime = validateDate { DateUtils.parseDate(driveRequest.startTime, Constant.defaultDateFormat) }
        val endTime = validateDate { DateUtils.parseDate(driveRequest.endTime, Constant.defaultDateFormat) }
        val examDuration = calculateDuration(startTime, endTime)
        val averageTimeToAnswer = examDuration / driveRequest.numberOfQuestions


        val candidates = driveRequest.candidates.map { email ->
            val selectedQuestions = validateQuestionAvailability {
                when (driveRequest.randomizeQuestions) {
                    true -> questionService.getRandomQuestions(driveRequest, averageTimeToAnswer)
                    false -> questionService.getQuestions(driveRequest, averageTimeToAnswer)
                }
            }

            val questionsModel = selectedQuestions.joinToString() { it.id.toString() }

            Candidate(0, email, questionsModel)

        }

        val topics = driveRequest.topic.joinToString()


        val drive = Drive(
                0,
                driveRequest.name,
                driveRequest.numberOfQuestions,
                startTime,
                endTime,
                examDuration,
                driveRequest.randomizeQuestions,
                candidates,
                topics,
                validateDifficulty { driveRequest.difficulty }
        )
        val savedDrive = dataService.persistExam(drive)

        val candidateResponse = savedDrive.candidates.map { CandidateResponse(it.id, it.questions, it.email, it.status, 0) }

        emailService.notifyCandidates(savedDrive)


        return DriveResponse(savedDrive.id, candidateResponse, candidateResponse[0].questions.length)
    }

    fun retrieveDrive(id: Int): DriveResponse {
        val drive = dataService.retrieveDrive(id)
        val candidateResponse = drive.candidates.map { CandidateResponse(it.id, it.questions, it.email, it.status, it.score) }
        return DriveResponse(drive.id, candidateResponse, candidateResponse[0].questions.split(",").size)
    }


    private fun calculateDuration(startDate: Date, endDate: Date): Long = TimeUnit.MILLISECONDS.toMinutes(endDate.time - startDate.time)
}