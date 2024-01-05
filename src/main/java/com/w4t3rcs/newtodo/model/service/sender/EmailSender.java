package com.w4t3rcs.newtodo.model.service.sender;

import com.w4t3rcs.newtodo.model.common.ServiceSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSender implements ServiceSender {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(Object from, Object to, Object subject, Object body) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (from != null) message.setFrom(from.toString());
        message.setTo(to.toString());
        message.setSubject(subject.toString());
        message.setText(body.toString());
        mailSender.send(message);
    }
}
