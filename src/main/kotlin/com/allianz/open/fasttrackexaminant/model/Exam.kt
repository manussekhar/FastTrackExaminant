package com.allianz.open.fasttrackexaminant.model

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "EXAM")
data class Exam(
        @Id
        @GeneratedValue
        @Column(name = "ID")
        val id: Int,

        @Column(name = "NAME")
        val name: String,

        @Column(name = "NUMBER_OF_QUESTIONS")
        val numberOfQuestions: Long,

        @Column(name = "START_TIME")
        val startTime: Date,

        @Column(name = "END_TIME")
        val endTime: Date,

        @Column(name = "DURATION")
        val duration: Long,

        @Column(name = "RANDOMIZE_QUESTIONS")
        val randomizeQuestions: Boolean,

        @OneToMany(cascade = [CascadeType.ALL],orphanRemoval = true)
        @JoinColumn(name="CANDIDATE_ID")
        val candidates: List<Candidate>,

        @Column(name = "TOPIC")
        val topic: String,

        @Column(name = "DIFFICULTY")
        val difficulty: String
)