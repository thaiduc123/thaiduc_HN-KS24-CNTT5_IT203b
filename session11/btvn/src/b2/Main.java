package b2;

import b1.utils.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
//        ResultSet rs = stmt.executeQuery("SELECT medicine_name, stock FROM Pharmacy");
//
//        if (rs.next()) {
//            System.out.println("Thuốc: " + rs.getString("medicine_name"));
//        }
//if chỉ chạy 1 lần duy nhất
//Trong khi yêu cầu là: in toàn bộ danh sách thuốc
// rs.next() trong if chỉ nhảy đến dòng 1, in xong kết thúc luôn, các dòng sau bị bỏ qua
        try {
            conn = DBContext.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT medicine_name, stock FROM Pharmacy";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(
                        "Thuốc: " + rs.getString("medicine_name") +
                                " | Tồn kho: " + rs.getInt("stock")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}