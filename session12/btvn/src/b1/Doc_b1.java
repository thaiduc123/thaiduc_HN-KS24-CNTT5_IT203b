package b1;

import b1.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doc_b1 {

    public static boolean login(String code, String pass) {
//String sql = "SELECT * FROM Doctors WHERE code = '" + code + "' AND pass = '" + pass + "'";
// nối chuỗi trực tiếp (string concatenation)
// Khi user nhập: pass = ' OR '1'='1
// SQL trở thành: SELECT * FROM Doctors WHERE code = 'abc' AND pass = '' OR '1'='1'
//'1'='1' luôn đúng, bypass login
        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, code);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
