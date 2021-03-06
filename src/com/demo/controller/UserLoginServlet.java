package com.demo.controller;


import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.domain.User;
import com.demo.utils.CipherUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String inputUserName = request.getParameter("userName");
        String inputPassword = request.getParameter("password");

        UserDao userDao = new UserDaoImpl();
        User userInDB = userDao.getUserByName(inputUserName);

        if (userInDB != null && userInDB.getPassword().equals(inputPassword)) {
            request.getSession().setAttribute("userType", "normal");
            request.getSession().setAttribute("user", userInDB);
            System.out.println("~~~~~~~~~~~~~~~");
            response.sendRedirect("/content.jsp");
        }

        out.write("用户名密码不对");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        userDao.addUser(new User("test", CipherUtil.generatePassword("123").toUpperCase(), "aaa@bbb.com",new Date()));
        System.out.println(userDao.getUserByName("test1"));
    }
}
