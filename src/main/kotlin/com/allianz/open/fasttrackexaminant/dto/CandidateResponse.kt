package com.allianz.open.fasttrackexaminant.dto

data class CandidateResponse(val id: Int,
                             val questions: String,
                             val email: String,
                             val status: String,
                             val score: Int)