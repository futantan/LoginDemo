package com.demo.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String userName;
    private static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            //in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
    }

    public static Connection getDatabaseConnection() {

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void closeDatabaseConection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
