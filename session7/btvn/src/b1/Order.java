package b1;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private double total;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }
}