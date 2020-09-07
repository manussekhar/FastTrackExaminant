package com.allianz.open.fasttrackexaminant.dto

data class QuestionRequest(val questionText: String,
                           val answerTexts: List<String>,
                           val correctAnswer: List<Int>,
                           val pointsForCorrectAnswer: Int,
                           val topic: String,
                           val difficulty: String)