package com.stachura.praca_inz.backend.service.impl;


import com.stachura.praca_inz.backend.exception.service.ServiceException;
import com.stachura.praca_inz.backend.service.MailService;
import com.stachura.praca_inz.backend.web.utils.PropertyUtil;

import java.util.Properties;

public class MailServiceImpl  {

//    private static final String
//            EMAIL = PropertyUtil.getProperty(PropertyUtil.APP_BUNDLE,"email"),
//            PASSWORD = PropertyUtil.getProperty(PropertyUtil.APP_BUNDLE,"password"),
//            HOST = PropertyUtil.getProperty(PropertyUtil.APP_BUNDLE,"host"),
//            PORT = PropertyUtil.getProperty(PropertyUtil.APP_BUNDLE,"port");
//
//    @Override
//    public void sendRegisterMessage(String to) throws ServiceException {
//        sendMessage(to, "Rejestracja", "Witamy w Systemie Benefitów!");
//    }
//
//    @Override
//    public void sendActivateAccountMessage(String to) throws ServiceException {
//        sendMessage(to, "Aktywacja konta", "Twoje konto w Systemie Benefitów zostało ponownie aktywowane.");
//    }
//
//    @Override
//    public void sendDeactivateAccountMessage(String to) throws ServiceException {
//        sendMessage(to, "Blokada konta", "Twoje konto w Systemie Benefitów zostało zablokowane.");
//    }
//
//    @Override
//    public void sendVerifyAccountMessage(String to) throws ServiceException {
//        sendMessage(to, "Weryfikacja", "Twoje konto w Systemie Benefitów zostało zweryfikowane.");
//    }
//
//    private void sendMessage(String to, String subject, String text) throws ServiceException {
//        Properties props = new Properties();
//        props.put("mail.smtp.user", EMAIL);
//        props.put("mail.smtp.host", HOST);
//        props.put("mail.smtp.PORT", PORT);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.ssl.enable", "true");
//        props.put("mail.smtp.socketFactory.PORT", PORT);
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//
//        try {
//            Authenticator auth = new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(EMAIL, PASSWORD);
//                }
//            };
//            Session session = Session.getInstance(props, auth);
//            MimeMessage msg = new MimeMessage(session);
//            msg.setText(text);
//            msg.setSubject(subject);
//            msg.setFrom(new InternetAddress(EMAIL));
//            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            Transport.send(msg);
//        } catch (MessagingException e) {
//            throw ServiceException.createServiceException(ServiceException.MAIL_ERROR, e);
//        }
//    }
}
