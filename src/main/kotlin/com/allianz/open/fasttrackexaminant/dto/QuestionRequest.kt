package com.allianz.open.fasttrackexaminant.dto

data class QuestionRequest(var questionText: String,
                           var answerTexts: List<String>,
                           var correctAnswer: List<Int>,
                           var pointsForCorrectAnswer: Int,
                           var topic: String,
                           var difficulty: String)