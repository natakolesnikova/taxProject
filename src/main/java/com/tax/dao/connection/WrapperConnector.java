package com.tax.dao.connection;

import java.sql.*;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class WrapperConnector {
    private static volatile WrapperConnector wrapperConnector;
    private Connection connection;
    private WrapperConnector() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("db/database");
            String url = resourceBundle.getString("db.url");
            String user = resourceBundle.getString("db.user");
            String pass = resourceBundle.getString("db.password");
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(url, user, pass);
        } catch (MissingResourceException e) {
            System.err.println("properties file is missing " + e);
        } catch (SQLException e) {
            System.err.println("not obtained connection " + e);
        }
    }

    public static WrapperConnector getInstance() {
        if (wrapperConnector == null) {
            synchronized (WrapperConnector.class) {
                if (wrapperConnector == null) {
                    wrapperConnector = new WrapperConnector();
                }
            }
        }
        return wrapperConnector;
    }

    public PreparedStatement getStatement(String sql) throws SQLException {
        if (connection != null) {
            PreparedStatement statement =  connection.prepareStatement(sql);
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    public void closePrepareStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("statement is null" + e);
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(" wrong connection " + e);
            }
        }
    }

}
