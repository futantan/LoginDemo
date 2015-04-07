package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String inputUserName = request.getParameter("userName");
        String inputPassword = request.getParameter("password");
        String inputEmail = request.getParameter("email");

        //todo check valid
        UserDao userDao = new UserDaoImpl();
        boolean isRegisterSucceed = userDao.addUser(new User(inputUserName, inputPassword, inputEmail,new Date()));
        if (isRegisterSucceed) {
            request.getSession().setAttribute("userType", "normal");
            response.sendRedirect("/content");
        }else{
            out.write("用户已存在");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
