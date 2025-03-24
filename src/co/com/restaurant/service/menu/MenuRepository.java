package co.com.restaurant.service.menu;

import co.com.restaurant.model.Menu;
import java.util.List;

public interface MenuRepository {

    /**
     * Obtiene todos los productos de la carta.
     */
    List<Menu> getAllMenuItems();

    /**
     * Busca un producto por su id.
     */
    Menu findById(int id);
}
