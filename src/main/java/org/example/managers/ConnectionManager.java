package org.example.managers;

import java.sql.*;

public class ConnectionManager {

//    private static final String DB_USERNAME = "s465521";
//    private static final String DB_PASSWORD = "wSeLlI0A3tb2Ct45";
//    private static final String DB_URL = "jdbc:postgresql://localhost:5432/studs?useUnicode=yes&characterEncoding=UTF-8";

    private static final String DB_USERNAME = "s465521";
    private static final String DB_PASSWORD = "wSeLlI0A3tb2Ct45";
    private static final String DB_URL = "jdbc:postgresql://localhost:5433/studs";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public static void execute(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        statement.execute(query);
    }

    public static ResultSet executeQuery(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    public static int executeUpdate(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
}
