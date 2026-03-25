package b1;
import b1.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service {
    public void med(int medicineId, int patientId) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.openConnection();
            // Tắt Auto-Commit
            conn.setAutoCommit(false);
            String updateSql = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(updateSql);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();
            //ps1.executeUpdate();  Trừ thuốc, commit ngay
//            int x = 10 / 0;      Lỗi xảy ra
//            ps2.executeUpdate();  Không chạy tới
// Vì Auto-Commit = TRUE, nên câu lệnh đầu không thể rollback
// DB không biết 2 câu này là 1 transaction, dẫn tới: Trừ thuốc thành công, ko ghi lịch sử
            String insertSql = "INSERT INTO Prescription_History(patient_id, medicine_id, date) VALUES (?, ?, NOW())";
            PreparedStatement ps2 = conn.prepareStatement(insertSql);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();
            conn.commit();
            System.out.println("Cấp phát thuốc thành công");
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
