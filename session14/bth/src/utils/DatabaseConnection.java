package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/flashsale";
    private static final String USER = "root";
    private static final String PASSWORD = "123456@";

    public static Connection openConnection(){
        Connection conn = null;
        //khai bao cho class biet driver
        try {
            Class.forName(DRIVER);

            conn= DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
