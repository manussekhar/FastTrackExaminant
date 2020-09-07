package com.allianz.open.fasttrackexaminant.model

import javax.persistence.*

@Entity
@Table(name = "ANSWER")
data class Answer(
        @Id
        @GeneratedValue
        val id: Int,
        val text: String,
        val correctAnswer: Boolean,
        )