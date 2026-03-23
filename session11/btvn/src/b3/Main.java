package b3;

import b1.utils.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã giường: ");
        String bedId = sc.nextLine();

        Connection conn = null;
        PreparedStatement ps = null;

        try {
//            executeUpdate() trả về số dòng bị ảnh hưởng nhưng bỏ qua => luôn in “thành công”
            conn = DBContext.getConnection();
            String sql = "UPDATE beds SET bed_status = ? WHERE bed_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "Đang sử dụng");
            ps.setString(2, bedId);
            int rowsAffected = ps.executeUpdate();
            //if (rowsAffected == 0) => "Mã giường không tồn tại"
            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Mã giường không tồn tại");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}