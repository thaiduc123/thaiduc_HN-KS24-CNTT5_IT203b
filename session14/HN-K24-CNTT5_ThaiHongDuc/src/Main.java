import utils.DatabaseConnection;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String senderId = "ACC01";
        String receiverId = "ACC02";
        double amount = 10;
        try (Connection conn = DatabaseConnection.openConnection()) {
            conn.setAutoCommit(false);
            String check = "select Balance from Accounts where AccountId = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(check)) {
                preparedStatement.setString(1, senderId);
                ResultSet rs = preparedStatement.executeQuery();
                if (!rs.next()) {
                    System.out.println("Tài khoản không tồn tại");
                    conn.rollback();
                    return;
                }
                double balance = rs.getDouble("Balance");
                if (balance < amount) {
                    System.out.println("Số dư không đủ");
                    conn.rollback();
                    return;
                }
            }
            String update = "{call sp_UpdateBalance(?, ?)}";
            try (CallableStatement callableStatement = conn.prepareCall(update)) {
                callableStatement.setString(1, senderId);
                callableStatement.setDouble(2, -amount);
                callableStatement.executeUpdate();
                callableStatement.setString(1, receiverId);
                callableStatement.setDouble(2, amount);
                callableStatement.executeUpdate();
            }
            conn.commit();
            System.out.println("Chuyển khoản thành công");
//            String r = "select AccountId, FullName, Balance from Accounts where AccountId IN (?, ?)";
//            try
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}