package com.demo.controller;

import com.demo.mail.MailInfo;
import com.demo.mail.MailSender;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registerName = request.getParameter("userName");
        String registerMail = request.getParameter("email");

        String registerId = "" + Math.random() * Math.random();
        String url = "http://localhost:8080/EmailRegister/MailBackServlet.do?registerId=" + registerId;
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute(registerId, registerName);
        httpSession.setMaxInactiveInterval(600);

        String content = registerName + "(" + registerMail + "),您好<br/>感谢您注册XXXXX!<br/>"
                + "<b>验证您的邮箱地址注册</b><br/>请点击下面的链接来确认您的注册<br/>"
                + "<a href='" + url + "'>确认!请点击这里验证此邮件</a><br/>"
                + "如果您不能点击上述标签为“确认！”的链接，您还可以通过复制（或输入）下面的URL到地址栏中来验证您的邮件地址。"
                + "<a href='" + url + "'>" + url + "</a><br/>"
                + "如果您认为这是垃圾邮件，请忽略此邮件。";

        // todo read from config file
        MailInfo mailInfo = new MailInfo();
        mailInfo.setMailSMTPHost("smtp.163.com");
        mailInfo.setFromAddress("demologin@163.com");
        mailInfo.setToAddress(registerMail);
        mailInfo.setUserName("demologin@163.com");
        mailInfo.setPassword("123456abc");
        mailInfo.setValidate(true);
        mailInfo.setSubject("邮件主题");
        mailInfo.setContent(content);

        MailSender.sendMail(mailInfo, true); // true发送HTML格式邮件，false发送纯文本
        request.setAttribute("userName", registerName);
        request.setAttribute("email", registerMail);
        request.getRequestDispatcher("/sendMailSuccess.jsp").forward(request, response);
    }
}
