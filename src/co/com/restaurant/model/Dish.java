package co.com.restaurant.model;

/**
 * Clase que representa un Plato (Dish) en el menú.
 * Extiende de MenuItem (Herencia).
 */
public class Dish extends Menu {

    public Dish(int id, String name, double price, String category) {
        super(id, name, price, category);
    }

    /**
     * Ejemplo de Polimorfismo: sobrescribimos el método
     * calculateFinalPrice() de la clase abstracta MenuItem.
     * Aquí no aplicamos recargo, retornamos el precio normal.
     */
    @Override
    public double calculateFinalPrice() {
        return getPrice();
    }
}


