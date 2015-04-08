package com.demo.controller;

import com.demo.dao.UserDao;
import com.demo.dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userType").equals("normal")) {
            response.sendRedirect("/adminLogin.html");
        } else {
            String inputUserName = request.getParameter("userName");
            String inputAction = request.getParameter("action");
            if (inputUserName != null && inputAction != null) {
                if (inputAction.equals("edit")) {

                }
                if (inputAction.equals("delete")) {
                    UserDao userDao = new UserDaoImpl();
                    userDao.deleteUser(inputUserName);
                    response.sendRedirect("/manage");
                }
            } else {
                //todo 网页出错处理
            }
        }
    }
}
