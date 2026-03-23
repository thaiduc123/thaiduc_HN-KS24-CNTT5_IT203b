package b1.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
    private final static String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456@";
    //việc khởi tạo kết nối liên tục mà không có cơ chế đóng (Close)
    // hoặc quản lý tập trung lại gây nguy hiểm cho hệ thống y tế vì khi
    //return DriverManager.getConnection, mỗi lần gọi method sẽ tạo connection mới => Quá tải Database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
