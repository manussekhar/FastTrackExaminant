package com.allianz.open.fasttrackexaminant.service.email

import com.allianz.open.fasttrackexaminant.dao.CandidateRepository
import com.allianz.open.fasttrackexaminant.model.Candidate
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.core.env.Environment
import org.springframework.mail.MailException
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Component
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import java.util.*
import javax.mail.internet.MimeMessage
import kotlin.collections.HashMap

@Component
@Scope("prototype")
class Postman(var candidate: Candidate) : Runnable {
    @Autowired
    private lateinit var candidateRepository: CandidateRepository


    @Autowired
    private lateinit var emailSender: JavaMailSender

    @Autowired
    private lateinit var templateEngine: SpringTemplateEngine


    @Autowired
    private lateinit var env: Environment

    private val LOGGER: org.slf4j.Logger = LoggerFactory.getLogger(Postman::class.java)

    override fun run() {
        LOGGER.info("Called from thread")

        val properties = HashMap<String, Any>()

        val messagePreparator = MimeMessagePreparator { mimeMessage: MimeMessage ->
            val messageHelper = MimeMessageHelper(mimeMessage)
            env.getProperty("spring.mail.username")?.let { messageHelper.setFrom(it) }
            messageHelper.setTo(this.candidate.email)
            messageHelper.setSubject("Exam Invitation")
            // messageHelper.addAttachment("template-cover.png", ClassPathResource("javabydeveloper-email.PNG"))
            val content = templateEngine.process("email", Context(Locale.ENGLISH, properties))
            messageHelper.setText(content, true)
        }


        try {
            emailSender.send(messagePreparator)
            candidate.status = "SCHEDULED_INVITATION_SENT"

        } catch (e: MailException) {
            LOGGER.error("Failed to send exam Invitation", e)
            candidate.status = "SCHEDULED_INVITATION_FAILED"
        }

        candidateRepository.save(candidate)
    }

}