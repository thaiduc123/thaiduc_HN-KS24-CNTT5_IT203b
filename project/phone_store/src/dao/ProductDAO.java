package dao;

import model.Product;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
//    public String getLastId() {
//        String sql = "SELECT id FROM Products ORDER BY id DESC LIMIT 1";
//
//        try (Connection conn = DatabaseConnection.openConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            if (rs.next()) {
//                return rs.getString("id");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public List<Product> getAvailableProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE stock > 0";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getString("id"),
                        rs.getString("category_id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("storage"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

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
                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public Product findById(String id) {
        String sql = "SELECT * FROM Products WHERE id = ?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Product(
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
            }

        } catch (Exception e) {}
        return null;
    }
    public boolean insert(Product p) {
        String sql = "INSERT INTO Products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getId());
            ps.setString(2, p.getCategoryId());
            ps.setString(3, p.getName());
            ps.setString(4, p.getBrand());
            ps.setString(5, p.getStorage());
            ps.setString(6, p.getColor());
            ps.setInt(7, p.getStock());
            ps.setDouble(8, p.getPrice());
            ps.setString(9, p.getDescription());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(Product p) {
        String sql = "UPDATE Products SET category_id=?, name=?, brand=?, storage=?, color=?, stock=?, price=?, description=? WHERE id=?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCategoryId());
            ps.setString(2, p.getName());
            ps.setString(3, p.getBrand());
            ps.setString(4, p.getStorage());
            ps.setString(5, p.getColor());
            ps.setInt(6, p.getStock());
            ps.setDouble(7, p.getPrice());
            ps.setString(8, p.getDescription());
            ps.setString(9, p.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM Products WHERE id=?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public List<Product> searchByName(String keyword) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Product(
                        rs.getString("id"),
                        rs.getString("category_id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("storage"),
                        rs.getString("color"),
                        rs.getInt("stock"),
                        rs.getDouble("price"),
                        rs.getString("description")
                ));
            }

        } catch (Exception e) {}
        return list;
    }
}