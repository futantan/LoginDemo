package com.demo.dao.impl;

import com.demo.dao.AdminDao;
import com.demo.domain.Admin;
import com.demo.utils.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDaoImpl  implements AdminDao{
    @Override
    public Admin getAdminByName(String name) {
        Connection connection = DBUtil.getDatabaseConnection();
        String sql = "SELECT name, password FROM admin WHERE name='" + name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                Admin admin = new Admin();
                admin.setAdminName(resultSet.getString(1));
                admin.setPassword(resultSet.getString(2));
                return admin;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDatabaseConection(connection);
        }

        return null;
    }
}
