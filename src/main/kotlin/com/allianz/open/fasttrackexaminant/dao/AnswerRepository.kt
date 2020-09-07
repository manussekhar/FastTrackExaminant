package com.allianz.open.fasttrackexaminant.dao

import com.allianz.open.fasttrackexaminant.model.Answer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AnswerRepository : CrudRepository<Answer, Int> {
}