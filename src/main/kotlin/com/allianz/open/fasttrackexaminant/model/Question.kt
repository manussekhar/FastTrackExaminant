package com.allianz.open.fasttrackexaminant.model

import com.allianz.open.fasttrackexaminant.service.Difficulty

data class Question(val questionText:String,
                    val answers : List<Answer>,
                    val pointsForCorrectAnswer: Int,
                    val topic: String,
                    val difficulty: Difficulty)