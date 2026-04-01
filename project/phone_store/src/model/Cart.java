package model;

public class Cart {
    private int userId;
    private String productId;
    private int quantity;
    public Cart(int userId, String productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }
    public int getUserId() { return userId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
}