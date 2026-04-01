package service;

import dao.CartDAO;
import dao.ProductDAO;
import model.CartItem;
import model.Product;
//import model.Product;

import java.util.List;

public class CartService {
    private CartDAO dao = new CartDAO();
    private ProductDAO productDAO = new ProductDAO();
    public List<CartItem> getCart(int userId) {
        return dao.getCartByUser(userId);
    }
    public Product findProductById(String productId) {
        return productDAO.findById(productId);
    }
    public boolean addToCart(int userId, String productId, int quantity) {
        return dao.addToCart(userId, productId, quantity);
    }
    public int getQuantity(int userId, String productId) {
        return dao.getQuantity(userId, productId);
    }
    public boolean updateQuantity(int userId, String productId, int quantity) {
        return dao.updateQuantity(userId, productId, quantity);
    }
    public boolean delete(int userId, String productId) {
        return dao.delete(userId, productId);
    }
}