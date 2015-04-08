package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MailBackServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registerId = request.getParameter("registerId");
        if (registerId == null) {
            request.getRequestDispatcher("/index.html").forward(request, response);
            return;
        }

        String registerName = (String) request.getSession().getAttribute(registerId);

        if (registerName == null || registerName.equals("")) {
            request.getRequestDispatcher("/index.html").forward(request, response);
            return;
        }

        UserDao userDao = new UserDaoImpl();
        User user = userDao.getUserByName(registerName);
        user.setConfirmed(true);
        userDao.updateUser(user);
        System.out.println("!!!!!!UPDATE");
        request.setAttribute("registerName", registerName);
        request.getRequestDispatcher("/registerSuccess.jsp").forward(request, response);
    }
}
