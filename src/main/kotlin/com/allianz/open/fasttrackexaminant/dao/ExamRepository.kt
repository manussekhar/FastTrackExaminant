package com.allianz.open.fasttrackexaminant.dao

import com.allianz.open.fasttrackexaminant.model.Exam
import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ExamRepository : CrudRepository<Exam,Int> {
}