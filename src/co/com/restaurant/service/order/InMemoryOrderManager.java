package co.com.restaurant.service.order;

import co.com.restaurant.model.Order;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOrderManager implements OrderRepository {

    private final List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Order findOrderByTable(int tableNumber) {
        return orders.stream()
                .filter(o -> o.getTableNumber() == tableNumber && !o.isCanceled())
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public boolean removeOrder(int tableNumber) {
        // solo se elimina si no está entregado
        return orders.removeIf(o -> o.getTableNumber() == tableNumber && !o.isDelivered());
    }
}
