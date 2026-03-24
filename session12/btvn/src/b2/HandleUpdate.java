package b2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HandleUpdate {
    public void updatePatientVitals(Connection conn, int patientId, double temperature, int heartRate) throws SQLException {
        String sql = "UPDATE Vitals SET temperature = ?, heart_rate = ? WHERE p_id = ?";
        //Khi dùng Statement và nối chuỗi trực tiếp, giá trị số thực (double) sẽ được chuyển thành
        // chuỗi theo Locale của hệ điều hành
        //Trong SQL, chỉ dấu chấm mới hợp lệ cho số thập phân.
        // Vì vậy nếu hệ điều hành định dạng thành 37,5, câu lệnh SQL sẽ bị lỗi cú pháp.
        //PreparedStatement không chuyển số thành chuỗi theo Locale.
        // Khi ta gọi setDouble() hoặc setInt(), JDBC sẽ:
            //- Gửi giá trị số dưới dạng tham số nhị phân (binary) đến cơ sở dữ liệu.
            //- Database engine sẽ hiểu đúng kiểu dữ liệu (double, int) mà không cần quan tâm đến dấu chấm hay dấu phẩy.
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, temperature);   // Không lo dấu chấm/dấu phẩy
            pstmt.setInt(2, heartRate);        // Nhịp tim là số nguyên
            pstmt.setInt(3, patientId);        // ID bệnh nhân

            int rowsUpdated = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
        }
    }
}