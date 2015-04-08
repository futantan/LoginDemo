package com.demo.dao.impl;

import com.demo.dao.UserDao;
import com.demo.domain.User;
import com.demo.utils.DBUtil;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean addUser(User user) {
        Connection connection = DBUtil.getDatabaseConnection();

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

        sql = "INSERT INTO user(user_name,password,email,regist_time)VALUES(?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setTimestamp(4, new Timestamp(user.getRegisterDate().getTime()));
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
        String sql = "SELECT user_name, password, email, isConfirmed FROM user WHERE user_name='" + name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setConfirmed(resultSet.getBoolean(4));
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
        String sql = "SELECT user_name, password, email,isConfirmed FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString(1));
                user.setPassword(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setConfirmed(resultSet.getBoolean(4));
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
        String sql = "update user set password='" + user.getPassword() +
                "',email='" + user.getEmail() +
                "',isConfirmed=" + user.isConfirmed() +
                " where user_name='" + user.getUserName() + "'";
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
        ////userDao.createUserTable();
        //userDao.deleteUser("zhangsan");
        //userDao.deleteUser("z111hangsan");
        //System.out.println(userDao.getAllUsers());
        User a = new User("zhang", "123", "a@a.com", new Date());
        System.out.println(userDao.getUserByName("zhang"));
        User b = new User("li", "123", "B@B.com", new Date(), true);
        userDao.addUser(b);
    }
}
