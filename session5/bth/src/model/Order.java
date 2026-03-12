package model;

import java.util.HashMap;
import java.util.Map;

public class Order {

    private String orderId;
    private Map<MenuItem, Integer> items = new HashMap<>();
    private OrderStatus status = OrderStatus.PENDING;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public void addItem(MenuItem item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }

    public double calculateTotal() {
        return items.entrySet()
                .stream()
                .mapToDouble(e -> e.getKey().calculatePrice() * e.getValue())
                .sum();
    }

    public String getOrderId() {
        return orderId;
    }

    public Map<MenuItem, Integer> getItems() {
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderStatus getStatus() {
        return status;
    }
}