package sk.bumaza.cvstories.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component

@Component
class EmailService(
    val emailSender: JavaMailSender
) {

    @Value("\${spring.mail.username}")
    private val senderEmail : String = ""

    fun sendSimpleMessage(to: String?, subject: String, text: String){
        val message = SimpleMailMessage()
        message.setFrom(senderEmail)
        message.setTo(to)
        message.setSubject(subject)
        message.setText(text)

        emailSender.send(message)
    }

}