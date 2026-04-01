package dao;

import model.CartItem;
import model.Order;
import model.OrderDetail;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public boolean checkout(int userId, List<CartItem> cartItems) {
        String insertOrder = "INSERT INTO Orders(user_id, status, total_amount, created_at) VALUES (?, 'PENDING', ?, NOW())";
        String insertDetail = "INSERT INTO OrderDetails(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        String updateStock = "UPDATE Products SET stock = stock - ? WHERE id = ?";
        String clearCart = "DELETE FROM Cart WHERE user_id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.openConnection();
            conn.setAutoCommit(false);
            double total = 0;
            for (CartItem item : cartItems) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
            PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, userId);
            psOrder.setDouble(2, total);
            psOrder.executeUpdate();
            ResultSet rs = psOrder.getGeneratedKeys();
            rs.next();
            int orderId = rs.getInt(1);
            for (CartItem item : cartItems) {
                String productId = item.getProduct().getId();
                int qty = item.getQuantity();
                PreparedStatement checkStock = conn.prepareStatement(
                        "SELECT stock FROM Products WHERE id=? FOR UPDATE"
                );
                checkStock.setString(1, productId);
                ResultSet rsStock = checkStock.executeQuery();
                if (rsStock.next()) {
                    int stock = rsStock.getInt("stock");
                    if (qty > stock) {
                        conn.rollback();
                        System.err.println("Sản phẩm " + productId + " không đủ hàng!");
                        return false;
                    }
                }
                PreparedStatement psDetail = conn.prepareStatement(insertDetail);
                psDetail.setInt(1, orderId);
                psDetail.setString(2, productId);
                psDetail.setInt(3, qty);
                psDetail.setDouble(4, qty * item.getProduct().getPrice());
                psDetail.executeUpdate();
                PreparedStatement psStock = conn.prepareStatement(updateStock);
                psStock.setInt(1, qty);
                psStock.setString(2, productId);
                psStock.executeUpdate();
            }
            PreparedStatement psClear = conn.prepareStatement(clearCart);
            psClear.setInt(1, userId);
            psClear.executeUpdate();
            conn.commit();
            return true;
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception ex) {}
            e.printStackTrace();
        }
        return false;
    }
    public List<Order> getOrdersByUser(int userId) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE user_id = ? ORDER BY created_at DESC";
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("status"),
                        rs.getDouble("total_amount"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<OrderDetail> getDetailsByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();

        String sql = """
        SELECT p.name, od.quantity, od.price
        FROM OrderDetails od
        JOIN Products p ON od.product_id = p.id
        WHERE od.order_id = ?
    """;

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderDetail d = new OrderDetail();
                d.setProductName(rs.getString("name"));
                d.setQuantity(rs.getInt("quantity"));
                d.setPrice(rs.getDouble("price"));
                list.add(d);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}