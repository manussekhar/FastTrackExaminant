package com.allianz.open.fasttrackexaminant.model

data class Question(val id:Int, val text:String, val topic: String, val answers : List<Int>)