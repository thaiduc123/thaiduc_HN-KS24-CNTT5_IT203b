package model;

public class OrderDetail {
    private int id;
    private int orderId;
    private String productId;
    private int quantity;
    private double price;
    private String productName;
    public OrderDetail() {}

    public OrderDetail(int id, int orderId, String productId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public int getOrderId() { return orderId; }
    public String getProductId() { return productId; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setOrderId(int orderId) { this.orderId = orderId; }
    public void setProductId(String productId) { this.productId = productId; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }
    public String getProductName() { return productName;}
    public void setProductName(String productName) {this.productName = productName;}
}