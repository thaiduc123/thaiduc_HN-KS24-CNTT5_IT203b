import utils.DatabaseConnection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection con = DatabaseConnection.openConnection();
        try {
            //b1: tat auto commit
            con.setAutoCommit(false);
            //b2: thuc thi code
            String sqlWidthDraw = "update accounts set balance = balance - ? where id = ?";
            String sqlTransfer = """
                    insert into transfers
                    (sender_id, receive_id, amount, message, transfer_date) 
                    value (?,?,?,?,?)
                    """;
            String sqlDeposit = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

            double amount = 9000000.0;
            int senderId = 1;
            int receiveId = 2;

            PreparedStatement preparedWithDraw = con.prepareStatement(sqlWidthDraw);
            preparedWithDraw.setDouble(1, amount);
            preparedWithDraw.setInt(2, senderId);
            PreparedStatement preparedTransfer = con.prepareStatement(sqlTransfer);
            preparedTransfer.setInt(1, senderId);
            preparedTransfer.setInt(2, receiveId);
            preparedTransfer.setDouble(3, amount);
            preparedTransfer.setString(4, "Cầm lấy mà tiêu đi e, không phải nghĩ");
            preparedTransfer.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            PreparedStatement preparedDeposit = con.prepareStatement(sqlDeposit);
            preparedDeposit.setDouble(1, amount);
            preparedDeposit.setInt(2, receiveId);

            preparedWithDraw.executeUpdate();
            preparedTransfer.executeUpdate();
            preparedDeposit.executeUpdate();

            // B3: Commit
            con.commit();
        } catch (SQLException e) {
            // B4: Rollback
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println(e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}