package co.com.restaurant.service.menu;

import co.com.restaurant.model.Drink;
import co.com.restaurant.model.Dish;
import co.com.restaurant.model.Menu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InFileMenuManager implements MenuRepository {

    private List<Menu> menuItems;
    private static final String FILE_PATH = "menu.txt";

    public InFileMenuManager() {
        this.menuItems = new ArrayList<>();
        loadMenuFromFile();
    }

    private void loadMenuFromFile() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    // asumiendo que la primera línea es encabezado
                    firstLine = false;
                    continue;
                }
                // Estructura: ID|NAME|CATEGORY|PRICE|TYPE
                String[] data = line.split("\\|");
                if (data.length == 5) {
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String category = data[2];
                    double price = Double.parseDouble(data[3]);
                    String type = data[4]; // "dish" o "beverage"

                    Menu item;
                    if ("dish".equalsIgnoreCase(type)) {
                        item = new Dish(id, name, price, category);
                    } else {
                        item = new Drink(id, name, price, category);
                    }
                    menuItems.add(item);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la carta: " + e.getMessage());
        }
    }

    @Override
    public List<Menu> getAllMenuItems() {
        return menuItems;
    }

    @Override
    public Menu findById(int id) {
        return menuItems.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
