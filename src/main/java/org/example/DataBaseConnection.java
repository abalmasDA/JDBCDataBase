package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/some_data_base";
    private static final String userName = "root";
    private static final String password = "XXX";

    public static String getUrl() {
        return url;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getPassword() {
        return password;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getUrl(), getUserName(), getPassword());
    }


}
