package co.com.restaurant.model;

/**
 * Clase abstracta que representa la app del menú.
 * Demuestra Abstracción (clase abstracta)
 * y Encapsulamiento (atributos privados y getters/setters).
 */
public abstract class Menu {

    private int id;
    private String name;
    private double price;
    private String category; // "entrada", "plato fuerte", "bebida", "postre", etc.

    public Menu(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getCategory() {
        return category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Ejemplo de método que podría ser sobrescrito
     * para calcular algún recargo distinto dependiendo
     * del tipo de producto (Dish o Drink).
     */
    public abstract double calculateFinalPrice();

    @Override
    public String toString() {
        return id + ". " + name + " (" + category + ") - $" + price;
    }
}



