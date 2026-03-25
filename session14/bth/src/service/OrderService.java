package service;

import dao.Order;
import dao.Product;
import utils.DatabaseConnection;

import java.sql.Connection;

public class OrderService {
    private final Product product = new Product();
    private final Order order = new Order();
    public void placeOrder(int userId, int productId, int quantity) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.openConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            int stock = product.getStockForUpdate(productId, conn);
            if (stock < quantity) {
                throw new RuntimeException("Hết hàng!");
            }
            int newStock = stock - quantity;
            product.updateStock(productId, newStock, conn);
            int orderId = order.createOrder(userId, conn);
            order.insertOrderDetails(orderId, productId, quantity, 100, conn);
            conn.commit();
            System.out.println("Order thành công user: " + userId);
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
                System.out.println("Rollback: " + userId);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}