package com.allianz.open.fasttrackexaminant.service.email

import com.allianz.open.fasttrackexaminant.model.Candidate
import com.allianz.open.fasttrackexaminant.model.Drive
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.core.task.TaskExecutor
import org.springframework.stereotype.Service


@Service
class EmailService {


    @Autowired
    private lateinit var taskExecutor: TaskExecutor

    @Autowired
    private lateinit var applicationContext: ApplicationContext


    fun notifyCandidates(drive: Drive) {
        drive.candidates.forEach { notifyCandidate(it) }
    }

    fun notifyCandidate(candidate: Candidate) {
        val postman = applicationContext.getBean(Postman::class.java, candidate)
        taskExecutor.execute(postman)
    }


}