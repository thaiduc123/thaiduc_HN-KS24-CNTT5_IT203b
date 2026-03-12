package repository;

import model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {

    private List<Order> orders = new ArrayList<>();

    public void add(Order order){
        orders.add(order);
    }

    public Optional<Order> findById(String id){
        return orders.stream()
                .filter(o -> o.getOrderId().equals(id))
                .findFirst();
    }

    public List<Order> getAll(){
        return orders;
    }
}