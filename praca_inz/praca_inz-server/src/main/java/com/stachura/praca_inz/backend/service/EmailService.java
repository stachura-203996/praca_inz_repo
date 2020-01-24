package com.stachura.praca_inz.backend.service;

public interface EmailService {

    void sendSimpleMessage(String to,
                           String subject,
                           String text);


    void sendMessageWithLink(String to, String subject, String text);


}
