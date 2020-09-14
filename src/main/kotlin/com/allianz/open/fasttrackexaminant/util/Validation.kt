package com.allianz.open.fasttrackexaminant.util

import com.allianz.open.fasttrackexaminant.Main
import com.allianz.open.fasttrackexaminant.model.Question
import com.allianz.open.fasttrackexaminant.service.Difficulty
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.text.ParseException
import java.util.*

private val logger = LoggerFactory.getLogger(Main::class.java)
fun validateDate(body: () -> Date): Date {
    try {
        return body()
    } catch (e: ParseException) {
        throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Date validation failed", e)
    }
}

fun validateDifficulty(body: () -> String): String {
    return try {
        body()
    } catch (e: IllegalArgumentException) {
        logger.warn("Invalid difficulty received. Defaulting to BEGINNER")
        Difficulty.BEGINNER.name
    }
}

fun validateQuestionAvailability(body: () -> List<Question>): List<Question> {
    val questions = body()
    if (questions.isEmpty()) throw ResponseStatusException(HttpStatus.NOT_FOUND, "Questions not for the supplied criteria")
    return questions
}

fun <T>validateDataAvailability(body: () -> T, message: String): T {

     try {
        return body()
    } catch (e: NoSuchElementException) {
        throw ResponseStatusException(HttpStatus.NOT_FOUND, message)
    }

}
