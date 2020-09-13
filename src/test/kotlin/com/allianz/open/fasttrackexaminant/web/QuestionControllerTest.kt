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
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put

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
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", Difficulty.BEGINNER.name,2))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }.andReturn()

        println("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
    }

    @Test
    fun `2 - Create Question Positive - 2 answers`() {
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What comes between 1 and 4 ?", "One,Two,Three,Four".split(",").toList(), listOf(1, 2), 1, "Mathematics", Difficulty.BEGINNER.name,2))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }.andReturn()

        println("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
    }

    @Test
    fun `3 - Create Question Negative - Invalid difficulty value`() {
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", "Invalid difficulty",2))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }.andReturn()

        println("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
    }

    @Test
    fun `4 - Create Question Negative - Invalid correct answer`() {
        mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(5), 1, "Mathematics", "Invalid difficulty",2))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest }
        }.andDo { print() }.andReturn()

    }


    @Test
    fun `5 - Retrieve Question Positive`() {
        println("--" + this::`5 - Retrieve Question Positive`.name + "--")
        println("---- Create Question ----")
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", Difficulty.BEGINNER.name,2))
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }.andReturn()

        val id = mapper.readTree(result.response.contentAsString).get("questionId")
        println("Question Created with ID: $id")
        println("---- Retrieve Question ----")

        mockMvc.get("/Question/{id}", id).andExpect {
            status { isOk }
            content {
                contentType(MediaType.APPLICATION_JSON)
                json("{" +
                        "'questionText':'What is 1+1 ?'," +
                        "'pointsForCorrectAnswer':1," +
                        "'topic':{'name':'Mathematics'}," +
                        "'difficulty':'BEGINNER'," +
                        "'answeringTime':2," +
                        "'answers':[" +
                        "{'text':'One','correctAnswer':false}," +
                        "{'text':'Two','correctAnswer':true}," +
                        "{'text':'Three','correctAnswer':false}," +
                        "{'text':'Four','correctAnswer':false}" +
                        "]" +
                        "}")
            }
        }.andDo { print() }.andReturn()

    }

    @Test
    fun `6 - Retrieve Question Negative - non existing ID`() {
        val id = 12345
        mockMvc.get("/Question/{id}", id).andExpect {
            status { isNotFound }
        }.andDo { print() }.andReturn()

    }

    @Test
    fun `7 - Update Question Positive - Update Question Text`() {

        println("---- Create Question ----")
        val questionRequest = QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", Difficulty.BEGINNER.name,2)
        val result = mockMvc.post("/Question") {
            content = mapper.writeValueAsString(questionRequest)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }.andReturn()

        val id = mapper.readTree(result.response.contentAsString).get("questionId")
        println("Question Created with ID: $id")

        println("---- Update Question ----")

        questionRequest.questionText = "One Plus One Equals ?"

        mockMvc.put("/Question/{id}", id) {
            content = mapper.writeValueAsString(questionRequest)
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }.andReturn()

        println("---- Retrieve Question ----")

        mockMvc.get("/Question/{id}", id)
                .andExpect {
                    status { isOk }
                    content {
                        contentType(MediaType.APPLICATION_JSON)
                        json("{" +
                                "'questionText':'One Plus One Equals ?'," +
                                "'pointsForCorrectAnswer':1," +
                                "'topic':{'name':'Mathematics'}," +
                                "'difficulty':'BEGINNER'," +
                                "'answeringTime':2," +
                                "'answers':[" +
                                "{'text':'One','correctAnswer':false}," +
                                "{'text':'Two','correctAnswer':true}," +
                                "{'text':'Three','correctAnswer':false}," +
                                "{'text':'Four','correctAnswer':false}" +
                                "]" +
                                "}")
                    }
                }.andDo { print() }.andReturn()

    }
}