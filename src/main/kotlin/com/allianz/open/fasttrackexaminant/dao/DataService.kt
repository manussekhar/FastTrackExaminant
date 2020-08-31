package com.allianz.open.fasttrackexaminant.dao

import com.allianz.open.fasttrackexaminant.model.Exam
import com.allianz.open.fasttrackexaminant.model.Question
import org.springframework.stereotype.Component

@Component
class DataService {

    fun getQuestionsForDrive(topic:String): Exam{
        val disabledNos = intArrayOf(1, 2, 3, 4)
        val answers : List<Int> =disabledNos.toList()
        val question : Question = Question(1,"","",answers)

        return Exam(1,"",disabledNos.toList())
    }
}