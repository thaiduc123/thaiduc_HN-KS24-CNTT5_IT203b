package service;

import dao.CartDAO;
import dao.OrderDAO;
import model.CartItem;
import model.Order;
import model.OrderDetail;

import java.util.List;

public class OrderService {
    private CartDAO cartDAO = new CartDAO();
    private OrderDAO orderDAO = new OrderDAO();
    public boolean checkout(int userId) {
        List<CartItem> cart = cartDAO.getCartByUser(userId);
        return orderDAO.checkout(userId, cart);
    }
    public List<Order> getOrdersByUser(int userId) {
        return orderDAO.getOrdersByUser(userId);
    }
    public List<OrderDetail> getOrderDetails(int orderId) {
        return orderDAO.getDetailsByOrderId(orderId);
    }
}