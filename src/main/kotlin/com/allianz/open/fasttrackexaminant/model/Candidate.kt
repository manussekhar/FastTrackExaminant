package com.allianz.open.fasttrackexaminant.model

import javax.persistence.*

@Entity
@Table(name = "CANDIDATE")
data class Candidate(@Id
                     @GeneratedValue(strategy = GenerationType.AUTO)
                     @Column(name = "ID")
                     val id: Int,
                     @Column(name = "EMAIL")
                     val email: String,
                     @Column(name = "QUESTIONS")
                     val questions: String)