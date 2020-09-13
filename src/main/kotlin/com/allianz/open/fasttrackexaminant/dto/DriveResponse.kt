package com.allianz.open.fasttrackexaminant.dto

data class DriveResponse(val id: Int, val candidates: List<CandidateResponse>, val numberOfQuestionsForEachCandidate: Int, val messages: List<String>)