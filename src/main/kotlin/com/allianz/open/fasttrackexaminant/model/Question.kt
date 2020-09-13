package com.allianz.open.fasttrackexaminant.model

import javax.persistence.*

@Entity(name = "QUESTION")
@Table(name = "QUESTION")
data class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        val id: Int,

        @Column(name = "TEXT")
        val questionText: String,

        @Column(name = "POINTS_FOR_CORRECT_ANSWER")
        val pointsForCorrectAnswer: Int,

        @OneToOne(cascade = [CascadeType.ALL],orphanRemoval = true)
        @JoinColumn(name="TOPIC_ID")
        val topic: Topic,

        @Column(name = "DIFFICULTY")
        val difficulty: String,

        @Column(name = "AVG_ANSWERING_TIME")
        val averageTimeToAnswer : Long,

        @OneToMany(cascade = [CascadeType.ALL],orphanRemoval = true)
        @JoinColumn(name="ANSWER_ID")
        val answers: List<Answer>? = null
)