package com.allianz.open.fasttrackexaminant.dto

data class DriveRequest(var name: String,
                        var topic: List<String>,
                        var difficulty: String,
                        var candidates: List<String>,
                        var numberOfQuestions: Long,
                        var startTime: String,
                        var endTime: String,
                        var randomizeQuestions: Boolean)