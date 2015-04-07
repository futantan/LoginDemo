package com.demo.domain;

import java.util.Date;

public class User {

    private String userName;
    private String password;
    private String email;
    private Date registerDate;
    private boolean isConfirmed;

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean isConfirmed) {
        this.isConfirmed = isConfirmed;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {

    }

    public User(String userName, String password, String email, Date registerDate) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
    }

    public User(String userName, String password, String email, Date registerDate, boolean isConfirmed) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.registerDate = registerDate;
        this.isConfirmed = isConfirmed;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", registerDate=" + registerDate +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
