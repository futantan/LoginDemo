package com.demo.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

public class MailSender {
    public static boolean sendMail(MailInfo mailInfo, Boolean isMime) {
        DemoMailAuthenticator authenticator = null;
        if (mailInfo.isValidate()) {
            authenticator = new DemoMailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        Properties pro = mailInfo.getProperties();
        Session session = Session.getDefaultInstance(pro, authenticator);
        try {
            Message message = new MimeMessage(session);
            Address from = new InternetAddress(mailInfo.getFromAddress());
            Address to = new InternetAddress(mailInfo.getToAddress());
            message.setFrom(from);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(mailInfo.getSubject());
            message.setSentDate(new Date());
            if (!isMime) {
                message.setText(mailInfo.getContent());
            } else {
                BodyPart html = new MimeBodyPart();
                html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
                Multipart mainPart = new MimeMultipart();
                mainPart.addBodyPart(html);
                message.setContent(mainPart);
            }
            Transport.send(message);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
