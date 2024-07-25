package com.example.signaldetector.view.utils

import android.util.Log
import com.example.signaldetector.BuildConfig
import com.example.signaldetector.model.utils.LogKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Properties
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.AddressException
import javax.mail.internet.MimeMessage

fun sendEmail(receiverEmail: String = "arz1379n@gmail.com", content: String) {
    try {

        val stringSenderEmail = "apptests1290@gmail.com"
        val stringPasswordSenderEmail = BuildConfig.EMAIL_PASSWORD

        val stringHost = "smtp.gmail.com"
        val properties: Properties = System.getProperties()

        properties.setProperty("mail.transport.protocol", "smtp")
        properties.setProperty("mail.host", stringHost)
        properties["mail.smtp.host"] = stringHost
        properties["mail.smtp.port"] = "465"
        properties["mail.smtp.socketFactory.fallback"] = "false"
        properties.setProperty("mail.smtp.quitwait", "false")
        properties["mail.smtp.socketFactory.port"] = "465"
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        properties["mail.smtp.ssl.enable"] = "true"
        properties["mail.smtp.auth"] = "true"

        val session: Session = Session.getInstance(properties, object : Authenticator() {
            override fun getPasswordAuthentication(): javax.mail.PasswordAuthentication {
                return javax.mail.PasswordAuthentication(
                    stringSenderEmail,
                    stringPasswordSenderEmail
                )
            }
        })

        val mimeMessage = MimeMessage(session)
        mimeMessage.addRecipients(Message.RecipientType.TO, receiverEmail)

        mimeMessage.subject = "Subject: Signal Detector App email"
        mimeMessage.setText("Hello, \n\nThis is my coordination: \n\n $content")

        CoroutineScope(Dispatchers.IO).launch {
            Transport.send(mimeMessage)
        }
    } catch (e: AddressException) {
        Log.d(LogKeys.RESULT, e.message ?: "Something went wrong in address")
    } catch (e: MessagingException) {
        Log.d(LogKeys.RESULT, e.message ?: "Something went wrong in messaging")
    }
}