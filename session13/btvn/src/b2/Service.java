package b2;

import b1.utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Service {
    public void thanhToan(int patientId, int invoiceId, double amount) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.openConnection();
            conn.setAutoCommit(false);
            String sql1 = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setDouble(1, amount);
            ps1.setInt(2, patientId);
            ps1.executeUpdate();

            // Test lỗi
           // String sql2 = "UPDATE Invoicess SET status = 'PAID' WHERE invoice_id = ?";
            String sql2 = "UPDATE Invoices SET status = 'PAID' WHERE invoice_id = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, invoiceId);
            ps2.executeUpdate();
            conn.commit();
            System.out.println(" Thanh toán thành công");
        //chỉ System.out.println không commit không rollback không đóng transaction đúng cách
        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Đã rollback!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
//Khi xảy ra lỗi trong Transaction mà chỉ in log (System.out.println) là chưa đủ.
//Cần phải gọi rollback() để đưa dữ liệu về trạng thái ban đầu và giải phóng transaction.
//Nếu không, connection sẽ bị treo và gây ảnh hưởng đến hệ thống.
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}