package com.allianz.open.fasttrackexaminant.web

import com.allianz.open.fasttrackexaminant.dto.QuestionRequest
import com.allianz.open.fasttrackexaminant.service.Difficulty
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric::class)
internal class QuestionControllerTest() {

    @Autowired
    private lateinit var questionController: QuestionController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @Test
    fun `1 - Create Question Positive`() {
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", Difficulty.BEGINNER.name))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn()

        print("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
    }

    @Test
    fun `2 - Create Question Positive - 2 answers`() {
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What comes between 1 and 4 ?", "One,Two,Three,Four".split(",").toList(), listOf(1, 2), 1, "Mathematics", Difficulty.BEGINNER.name))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn()

        print("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
    }


    @Test
    fun `3 - Create Question Negative - Invalid difficulty value`() {
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", "Invalid difficulty"))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn()

        print("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
    }

    @Test
    fun `4 - Create Question Negative - Invalid correct answer`() {
        mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(5), 1, "Mathematics", "Invalid difficulty"))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest }
        }

    }
}