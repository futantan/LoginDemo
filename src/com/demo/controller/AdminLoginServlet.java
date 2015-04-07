package com.demo.controller;

import com.demo.dao.AdminDao;
import com.demo.dao.impl.AdminDaoImpl;
import com.demo.domain.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AdminLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String inputUserName = request.getParameter("userName");
        String inputPassword = request.getParameter("password");

        AdminDao adminDao = new AdminDaoImpl();
        Admin adminInDB = adminDao.getAdminByName(inputUserName);

        if (adminInDB != null && adminInDB.getPassword().equals(inputPassword)) {
            out.write("用户名密码right");

        } else {
            out.write("用户名密码不对");

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
