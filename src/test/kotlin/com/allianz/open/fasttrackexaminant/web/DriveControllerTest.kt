package com.allianz.open.fasttrackexaminant.web

import com.allianz.open.fasttrackexaminant.dto.DriveRequest
import com.allianz.open.fasttrackexaminant.service.Difficulty
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.Alphanumeric::class)
internal class DriveControllerTest {
    @Autowired
    private lateinit var driveController: DriveController

    @Autowired
    private lateinit var questionController: QuestionController

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper


    @Test
    fun `1 - Create Exam Positive`() {
//        println("--" + this::`1 - Create Exam Positive`.name + "--")
//        println("---- Create Question ----")
//        val result = mockMvc.post("/Question") {
//            content = mapper.writeValueAsString(QuestionRequest("What is 1+1 ?", "One,Two,Three,Four".split(",").toList(), listOf(1), 1, "Mathematics", Difficulty.BEGINNER.name,2))
//            contentType = MediaType.APPLICATION_JSON
//        }.andExpect {
//            status { isOk }
//            content { contentType(MediaType.APPLICATION_JSON) }
//        }.andDo { print() }.andReturn()
//
//        println("Question Created with ID: " + mapper.readTree(result.response.contentAsString).get("questionId"))
//
//        println("---- Creating an Exam ----")
        //mockMvc.post("/Exam")

        println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(DriveRequest("Drive -1", listOf("Mathematics"),Difficulty.BEGINNER.name, listOf("a@b.com"),1,"2020-12-12 02:12:12","2020-12-12 03:12:12",true)))




    }
}