package com.demo.dao;

import com.demo.domain.User;

import java.util.ArrayList;

public interface UserDao {

    void createUserTable();

    /**
     *
     * @param user user info
     * @return true if added successfully
     *         false if user name already exists
     */
    boolean addUser(User user);

    /**
     *
     * @param name target user's name
     * @return a User instance if got it
     *         null if no user named 'name'
     */
    User getUserByName(String name);

    ArrayList<User> getAllUsers();

    void deleteUser(String userName);

    /**
     * update user's info except the name
     * @param user
     */
    void updateUser(User user);
}
