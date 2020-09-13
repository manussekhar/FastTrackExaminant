package com.allianz.open.fasttrackexaminant.service

import com.allianz.open.fasttrackexaminant.model.Candidate
import com.allianz.open.fasttrackexaminant.model.Exam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.core.env.Environment
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.templatemode.TemplateMode
import java.nio.charset.StandardCharsets
import java.util.*
import javax.mail.internet.MimeMessage
import kotlin.collections.HashMap


@Service
class EmailService {


    @Autowired
    private lateinit var emailSender: JavaMailSender

    @Autowired
    private lateinit var templateEngine: SpringTemplateEngine


    @Autowired
    private lateinit var env: Environment


    fun notifyCandidates(exam: Exam) {
        exam.candidates.forEach { notifyCandidate(it) }
    }

    fun notifyCandidate(candidate: Candidate) {
        val properties = HashMap<String, Any>()

        val messagePreparator = MimeMessagePreparator { mimeMessage: MimeMessage ->
            val messageHelper = MimeMessageHelper(mimeMessage)
            env.getProperty("spring.mail.username")?.let { messageHelper.setFrom(it) }
            messageHelper.setTo(candidate.email)
            messageHelper.setSubject("Exam Invitation")
            // messageHelper.addAttachment("template-cover.png", ClassPathResource("javabydeveloper-email.PNG"))
            val content = templateEngine.process("email", Context(Locale.ENGLISH, properties))
            messageHelper.setText(content, true)
        }

        emailSender.send(messagePreparator)
    }


}