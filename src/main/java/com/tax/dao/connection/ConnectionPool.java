package com.tax.dao.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool pool;
    private final DataSource dataSource;

    private ConnectionPool() throws NamingException {
        Context initialContext = new InitialContext();
        Context envContext = (Context) initialContext.lookup("java:comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/tax");
    }

    public static ConnectionPool getInstance() throws NamingException {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
