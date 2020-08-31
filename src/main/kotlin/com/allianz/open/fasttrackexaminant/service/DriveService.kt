package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.dao.DataService
import com.allianz.open.fasttrackexaminant.model.Drive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DriveService @Autowired constructor(private val dataService: DataService){


    fun createDrive(name:String, topic:String): Drive {
        return Drive(name,dataService.getQuestionsForDrive(topic));
    }
}