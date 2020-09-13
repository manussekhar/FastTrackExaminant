package com.allianz.open.fasttrackexaminant.dao

import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Int> {
    @Query("SELECT * FROM QUESTION q WHERE q.DIFFICULTY = ?1 " +
            "AND q.AVG_ANSWERING_TIME <= ?2 and q.TOPIC_ID IN " +
            "(select t.id from TOPIC t WHERE t.name = ?3) ORDER BY q.ID DESC",
            nativeQuery = true)
    fun getQuestions(difficulty: String, averageTimeToAnswer: Long, topic: List<String>): List<Question>
}