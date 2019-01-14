package com.stachura.praca_inz.backend.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendSimpleMessageUsingTemplate(String to,
                                        String subject,
                                        SimpleMailMessage template,
                                        String... templateArgs);


    void sendMessageWithLink(String to, String subject, String text);

    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text,
                                   String pathToAttachment);

}
