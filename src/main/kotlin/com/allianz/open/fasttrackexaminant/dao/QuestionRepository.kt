package com.allianz.open.fasttrackexaminant.dao

import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
@Repository
interface QuestionRepository : CrudRepository<Question,Int>{
}