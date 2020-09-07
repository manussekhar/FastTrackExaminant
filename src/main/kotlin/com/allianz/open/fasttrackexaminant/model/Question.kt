package com.allianz.open.fasttrackexaminant.model

import com.allianz.open.fasttrackexaminant.service.Difficulty
import javax.persistence.*

@Entity
@Table(name = "QUESTION")
data class Question(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "question_id")
        val id: Int,
        val questionText: String,
        val pointsForCorrectAnswer: Int,
        val topic: String,
        val difficulty: String,
        @OneToMany(cascade = [CascadeType.ALL],orphanRemoval = true)
        @JoinColumn(name="answer_id")
        val answers: List<Answer>? = null
)