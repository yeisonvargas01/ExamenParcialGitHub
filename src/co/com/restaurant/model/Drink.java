package co.com.restaurant.model;

/**
 * Clase que representa una Bebida en el menú.
 * Extiende de Menu.
 */
public class Drink extends Menu {

    public Drink(int id, String name, double price, String category) {
        super(id, name, price, category);
    }

    /**
     * Ejemplo de Polimorfismo: sobrescribimos el método
     * calculateFinalPrice() de la clase abstracta Menu.
     * Podríamos simular un recargo, por ejemplo,
     * 5% adicional si la bebida es alcohólica
     * (dependería de la categoría).
     */
    @Override
    public double calculateFinalPrice() {
        // Si la categoría es "bebida alcohólica", sumamos un 5%.
        if ("bebida alcohólica".equalsIgnoreCase(getCategory())) {
            return getPrice() * 1.05;
        }
        return getPrice();
    }
}

