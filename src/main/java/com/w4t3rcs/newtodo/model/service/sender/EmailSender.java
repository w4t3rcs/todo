package com.w4t3rcs.newtodo.model.service.sender;

import com.w4t3rcs.newtodo.model.common.ServiceSender;
import com.w4t3rcs.newtodo.model.entity.message.TextMessage;
import com.w4t3rcs.newtodo.model.properties.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailSender")
public class EmailSender implements ServiceSender {
    private final JavaMailSender mailSender;
    private final MessageProperties messageProperties;

    @Autowired
    public EmailSender(JavaMailSender mailSender, MessageProperties messageProperties) {
        this.mailSender = mailSender;
        this.messageProperties = messageProperties;
    }

    @Override
    public void send(TextMessage textMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        if (textMessage.getSenderAddress() != null) message.setFrom(textMessage.getSenderAddress());
        message.setTo(textMessage.getRecipientAddress());
        message.setSubject(textMessage.getMessageSubject(messageProperties));
        message.setText(textMessage.getMessageBody(messageProperties));
        mailSender.send(message);
    }
}
