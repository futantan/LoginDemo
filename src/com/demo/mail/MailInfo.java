package com.demo.mail;

import java.util.Properties;

public class MailInfo {
    private String mailSMTPHost;
    private String mailSMTPPort = "25";
    private String fromAddress;
    private String toAddress;
    private String userName;
    private String password;
    private boolean validate = false;
    private String subject;
    private String content;
    private String[] attachFileNames;

    public Properties getProperties() {
        Properties p = new Properties();
        p.put("mail.smtp.host", this.mailSMTPHost);
        p.put("mail.smtp.port", this.mailSMTPPort);
        p.put("mail.smtp.auth", validate ? "true" : "false");
        return p;
    }

    public String getMailSMTPHost() {
        return mailSMTPHost;
    }

    public void setMailSMTPHost(String mailSMTPHost) {
        this.mailSMTPHost = mailSMTPHost;
    }

    public String getMailSMTPPort() {
        return mailSMTPPort;
    }

    public void setMailSMTPPort(String mailSMTPPort) {
        this.mailSMTPPort = mailSMTPPort;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] attachFileNames) {
        this.attachFileNames = attachFileNames;
    }
}