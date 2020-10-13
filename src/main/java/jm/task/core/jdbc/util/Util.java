package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection = null;
    public static final String URL = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow&useSSL=false";
    public static final String NAME = "root";
    public static final String PASSWORD = "1e2e3e4e";
    public Util() throws SQLException{

    }
    public static Connection connection() throws  SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
}
