package com.allianz.open.fasttrackexaminant.service

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric::class)
class ExamServiceTest {

    @MockkBean
    private lateinit var dataService: DataService


    @Autowired
    private lateinit var examService: ExamService

    @Test
    fun `1 - Create Question Positive`() {
        every { dataService.saveQuestion(any()) } returns Unit

        val question = examService.createQuestion("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", Difficulty.BEGINNER.name)

        Assertions.assertEquals("What is 1+1 ?", question.questionText)
        Assertions.assertEquals(4, question.answers.size)
        Assertions.assertEquals(true, question.answers[1].correctAnswer)
        Assertions.assertEquals(false, question.answers[0].correctAnswer)
    }

    @Test
    fun `2 - Create Question Positive - 2 answers`() {
        every { dataService.saveQuestion(any()) } returns Unit

        val question = examService.createQuestion("What comes between 1 and 4 ?", "One,Two,Three,Four".split(",").toList(), listOf(1,2), 1, "Mathematics", Difficulty.BEGINNER.name)

        Assertions.assertEquals(false, question.answers[0].correctAnswer)
        Assertions.assertEquals(true, question.answers[1].correctAnswer)
        Assertions.assertEquals(true, question.answers[2].correctAnswer)
        Assertions.assertEquals(false, question.answers[3].correctAnswer)

    }

    @Test
    fun `3 - Create Question Negative - Invalid difficulty value`() {
        every { dataService.saveQuestion(any()) } returns Unit

        val question = examService.createQuestion("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", "Invalid difficulty")

        Assertions.assertEquals(Difficulty.BEGINNER, question.difficulty)
    }

    @Test
    fun `4 - Create Question Negative - Invalid correct answer`() {
        every { dataService.saveQuestion(any()) } returns Unit

        assertThrows<IllegalArgumentException>{examService.createQuestion("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(5), 1, "Mathematics", "Invalid difficulty")}
    }
}