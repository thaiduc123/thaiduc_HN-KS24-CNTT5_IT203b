package dao;

import java.sql.*;

public class Order {
    public int createOrder(int userId, Connection conn) throws SQLException {
        String sql = "INSERT INTO orders(user_id) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, userId);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return -1;
    }
    public void insertOrderDetails(int orderId, int productId, int quantity, double price, Connection conn) throws SQLException {
        String sql = "INSERT INTO order_details(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, orderId);
        ps.setInt(2, productId);
        ps.setInt(3, quantity);
        ps.setDouble(4, price);
        ps.executeUpdate();
    }
}