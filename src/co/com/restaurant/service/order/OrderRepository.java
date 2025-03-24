package co.com.restaurant.service.order;

import co.com.restaurant.model.Order;

import java.util.List;

public interface OrderRepository {

    void addOrder(Order order);
    Order findOrderByTable(int tableNumber);
    List<Order> getAllOrders();
    boolean removeOrder(int tableNumber);
}
