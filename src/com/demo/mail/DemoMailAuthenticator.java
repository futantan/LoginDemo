package com.demo.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class DemoMailAuthenticator extends Authenticator {
    private String userName;
    private String password;

    public DemoMailAuthenticator() {
    }

    public DemoMailAuthenticator(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
    }
}