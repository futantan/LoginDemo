package com.demo.dao.impl;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import com.demo.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    @Override
    public void createUserTable() {
        Connection connection = DBUtil.getDatabaseConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY UNIQUE AUTO_INCREMENT,user_name VARCHAR(55), password VARCHAR(55), email VARCHAR(55), UNIQUE (user_name))");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DBUtil.closeDatabaseConection(connection);
        }

    }

    @Override
    public boolean addUser(User user) {
        Connection connection = DBUtil.getDatabaseConnection();
        //todo check if exist
        String sql = "SELECT * FROM user WHERE user_name='" + user.getUserName() + "'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sql = "INSERT INTO user(user_name,password,email)VALUES(?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDatabaseConection(connection);
        }
        return true;
    }

    @Override
    public User getUserByName(String name) {
        Connection connection = DBUtil.getDatabaseConnection();
        String sql = "SELECT user_name, password, email FROM user WHERE user_name='" + name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDatabaseConection(connection);
        }

        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();
        Connection connection = DBUtil.getDatabaseConnection();
        String sql = "SELECT user_name, password, email FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDatabaseConection(connection);
        }

        return users;
    }

    @Override
    public void deleteUser(String userName) {
        Connection connection = DBUtil.getDatabaseConnection();
        String sql = "DELETE FROM user WHERE user_name='" + userName + "'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDatabaseConection(connection);
        }
    }

    @Override
    public void updateUser(User user) {
        Connection connection = DBUtil.getDatabaseConnection();
        String sql = "update user set password='" + user.getPassword() + "',email='" + user.getEmail() + "' where user_name='" + user.getUserName() + "'";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeDatabaseConection(connection);
        }

    }

    public static void main(String[] args) {
        UserDao userDao = new UserDaoImpl();
        //userDao.createUserTable();
        userDao.deleteUser("zhangsan");
        userDao.deleteUser("z111hangsan");
        System.out.println(userDao.getAllUsers());
    }
}
