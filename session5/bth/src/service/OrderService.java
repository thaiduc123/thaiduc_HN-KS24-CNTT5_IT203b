package service;

import model.MenuItem;
import model.Order;
import repository.OrderRepository;

public class OrderService {

    private OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }

    public void createOrder(Order order){
        repo.add(order);
    }

    public void addItemToOrder(Order order, MenuItem item, int quantity){
        order.addItem(item, quantity);
    }

    public double calculateRevenue(){
        return repo.getAll()
                .stream()
                .mapToDouble(Order::calculateTotal)
                .sum();
    }
}