package co.com.restaurant.usecase;

import co.com.restaurant.model.Menu;
import co.com.restaurant.model.Order;
import co.com.restaurant.service.menu.MenuRepository;
import co.com.restaurant.service.menu.InFileMenuManager;
import co.com.restaurant.service.order.InMemoryOrderManager;
import co.com.restaurant.service.order.OrderRepository;

import java.util.List;

/**
 * Clase que orquesta la lógica de negocio del restaurante
 * (Caso de uso principal).
 */
public class RestaurantUseCase {

    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    public RestaurantUseCase() {
        // Inicializamos las implementaciones reales:
        this.menuRepository = new InFileMenuManager();
        this.orderRepository = new InMemoryOrderManager();
    }

    /**
     * Retorna todos los productos de la carta.
     */
    public List<Menu> getMenuItems() {
        return menuRepository.getAllMenuItems();
    }

    /**
     * Crea un pedido para la mesa dada.
     */
    public Order createOrder(int tableNumber) {
        // Verificamos si esa mesa ya tiene un pedido abierto
        Order existing = orderRepository.findOrderByTable(tableNumber);
        if (existing != null && !existing.isCanceled()) {
            // Significa que ya hay un pedido activo para esa mesa
            return existing;
        }
        Order newOrder = new Order(tableNumber);
        orderRepository.addOrder(newOrder);
        return newOrder;
    }

    /**
     * Agrega un producto al pedido asociado a la mesa.
     */
    public boolean addItemToOrder(int tableNumber, int menuItemId) {
        Order order = orderRepository.findOrderByTable(tableNumber);
        if (order != null && !order.isCanceled()) {
            Menu item = menuRepository.findById(menuItemId);
            if (item != null) {
                order.addItem(item);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna los pedidos activos (no cancelados).
     */
    public List<Order> getActiveOrders() {
        return orderRepository.getAllOrders().stream()
                .filter(o -> !o.isCanceled())
                .toList();
    }

    /**
     * Cierra un pedido, marcándolo como entregado y calculando el total.
     * Aplica descuento si discountPercent > 0. Retorna el total calculado.
     */
    public double closeOrder(int tableNumber, double discountPercent) {
        Order order = orderRepository.findOrderByTable(tableNumber);
        if (order == null) {
            return -1; // no existe
        }
        if (order.isCanceled()) {
            return -1; // pedido cancelado
        }
        // set descuento (max 10%)
        order.setDiscountPercent(discountPercent);
        // marcar como entregado
        order.deliver();
        // calcular total
        return order.calculateTotal();
    }

    /**
     * Cancela un pedido si todavía no fue entregado.
     */
    public boolean cancelOrder(int tableNumber) {
        // se elimina solo si no está entregado
        return orderRepository.removeOrder(tableNumber);
    }
}

