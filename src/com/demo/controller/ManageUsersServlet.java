package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;
import com.demo.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class ManageUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType").equals("normal")) {
            response.sendRedirect("/adminLogin.html");
        } else {
            UserDao userDao = new UserDaoImpl();
            ArrayList<User> users = userDao.getAllUsers();

            request.setAttribute("users", users);
            RequestDispatcher rd = request.getRequestDispatcher("/manage.jsp");
            rd.forward(request, response);
        }
    }
}
