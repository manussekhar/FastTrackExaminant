package com.allianz.open.fasttrackexaminant.web

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HtmlController{
    @GetMapping("/")
    fun blog(): Person{
        return Person("Manu")
    }


}

data class Person(val name:String)