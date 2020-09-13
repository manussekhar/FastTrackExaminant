package com.allianz.open.fasttrackexaminant.model

import javax.persistence.*

@Entity(name = "TOPIC")
@Table(name = "TOPIC")
class Topic(@Id
            @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name = "ID")
            val id: Int,
            @Column(name = "NAME")
            val name: String)