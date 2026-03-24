package b3;

import b1.utils.DatabaseConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.openConnection()) {
            // Chuẩn bị gọi Stored Procedure
            CallableStatement cstmt = conn.prepareCall("{call GET_SURGERY_FEE(?, ?)}");
            // Tham số IN: surgery_id
            cstmt.setInt(1, 505);
            // Tham số OUT: total_cost (DECIMAL trong SQL)
            //bắt buộc phải đăng ký tham số đó bằng registerOutParameter()
            // trước khi thực thi (execute() hoặc executeUpdate()).
                //- JDBC cần biết kiểu dữ liệu trả về để chuẩn bị vùng nhớ và ánh xạ dữ liệu từ DB sang Java.
                //- Nếu không đăng ký, driver không biết tham số số mấy là OUT,
                    // cũng không biết kiểu dữ liệu, dẫn đến lỗi
            cstmt.registerOutParameter(2, Types.DECIMAL);
            // Thực thi
            cstmt.execute();
            // Lấy kết quả OUT
            double cost = cstmt.getDouble(2);
            System.out.println("Chi phí phẫu thuật: " + cost);
            cstmt.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
