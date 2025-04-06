package co.com.restaurant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido en el restaurante.
 */
public class Order {

    private int tableNumber;
    private List<Menu> items;
    private boolean delivered;      // indica si el pedido se entregó
    private boolean canceled;       // indica si el pedido se canceló
    private double discountPercent; // hasta 10%

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.delivered = false;
        this.canceled = false;
        this.discountPercent = 0.0;
    }

    public int getTableNumber() {
        return tableNumber;
    }
    public List<Menu> getItems() {
        return items;
    }
    public boolean isDelivered() {
        return delivered;
    }
    public boolean isCanceled() {
        return canceled;
    }
    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        // Máximo 10%
        if (discountPercent > 10.0) {
            discountPercent = 10.0;
        }
        this.discountPercent = discountPercent;
    }

    public void deliver() {
        this.delivered = true;
    }

    public void cancel() {
        this.canceled = true;
    }

    /**
     * Agrega un item al pedido.
     */
    public void addItem(Menu item) {
        items.add(item);
    }

    /**
     * Calcula el total del pedido (sumando los precios finales de cada ítem).
     */
    public double calculateTotal() {
        double sum = 0;
        for (Menu item : items) {
            sum += item.calculateFinalPrice();
        }
        // Aplicar descuento (si existe)
        double discountValue = sum * (discountPercent / 100.0);
        return sum - discountValue;
    }

    @Override
    public String toString() {
        return "Pedido mesa #" + tableNumber
                + " | Items: " + items.size()
                + " | Descuento: " + discountPercent + "%";
    }
}
