package com.allianz.open.fasttrackexaminant.model

import javax.persistence.*

@Entity
@Table(name = "ANSWER")
data class Answer(
        @Id
        @GeneratedValue
        @Column(name = "ID")
        val id: Int,
        @Column(name = "TEXT")
        val text: String,
        @Column(name = "IS_CORRECT")
        val correctAnswer: Boolean,
        )