package dao;

import model.CartItem;
import model.Product;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    public List<CartItem> getCartByUser(int userId) {
        List<CartItem> list = new ArrayList<>();
        String sql = """
        SELECT p.*, c.quantity
        FROM Cart c
        JOIN Products p ON c.product_id = p.id
        WHERE c.user_id = ?
    """;
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(
                        rs.getString("id"),
                        rs.getString("category_id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("storage"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getDouble("price"),
                        rs.getString("description")
                );

                int quantity = rs.getInt("quantity");

                list.add(new CartItem(p, quantity));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean addToCart(int userId, String productId, int quantity) {
        String checkSql = "SELECT quantity FROM Cart WHERE user_id=? AND product_id=?";
        String insertSql = "INSERT INTO Cart(user_id, product_id, quantity) VALUES (?, ?, ?)";
        String updateSql = "UPDATE Cart SET quantity = quantity + ? WHERE user_id=? AND product_id=?";
        try (Connection conn = DatabaseConnection.openConnection()) {
            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setInt(1, userId);
            check.setString(2, productId);
            ResultSet rs = check.executeQuery();
            if (rs.next()) {
                PreparedStatement update = conn.prepareStatement(updateSql);
                update.setInt(1, quantity);
                update.setInt(2, userId);
                update.setString(3, productId);
                return update.executeUpdate() > 0;
            } else {
                PreparedStatement insert = conn.prepareStatement(insertSql);
                insert.setInt(1, userId);
                insert.setString(2, productId);
                insert.setInt(3, quantity);
                return insert.executeUpdate() > 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(int userId, String productId) {
        String sql = "DELETE FROM Cart WHERE user_id=? AND product_id=?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, productId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }
    public int getQuantity(int userId, String productId) {
        String sql = "SELECT quantity FROM Cart WHERE user_id=? AND product_id=?";
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public boolean updateQuantity(int userId, String productId, int quantity) {
        String sql = "UPDATE Cart SET quantity=? WHERE user_id=? AND product_id=?";
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, userId);
            ps.setString(3, productId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

}