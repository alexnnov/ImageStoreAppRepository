package com.netcracker.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    private String driver;
    private String user;
    private String password;
    private String url;

    public JDBCConnection(String driver, String user, String password, String url) {
        this.driver = driver;
        this.user = user;
        this.password = password;
        this.url = url;
    }

    public Connection getConnection() {

        Connection connection = null;
        try {

            Class.forName(driver);

            connection = DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;

    }

}
