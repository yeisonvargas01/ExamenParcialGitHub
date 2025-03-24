package co.com.restaurant.application;

import co.com.restaurant.model.Menu;
import co.com.restaurant.model.Order;
import co.com.restaurant.usecase.RestaurantUseCase;

import java.util.List;
import java.util.Scanner;

public class RestaurantApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantUseCase useCase = new RestaurantUseCase();

        while (true) {
            System.out.println("\n===== Sistema de Gestión de Pedidos =====");
            System.out.println("1. Ver carta");
            System.out.println("2. Realizar un pedido");
            System.out.println("3. Ver pedidos activos");
            System.out.println("4. Cerrar un pedido");
            System.out.println("5. Cancelar un pedido");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int option = 0;
            try {
                option = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            switch (option) {
                case 1 -> {
                    // Ver carta
                    List<Menu> menu = useCase.getMenuItems();
                    System.out.println("---- Carta del Restaurante ----");
                    menu.forEach(System.out::println);
                }
                case 2 -> {
                    // Realizar pedido
                    System.out.print("Número de mesa: ");
                    int tableNumber = Integer.parseInt(scanner.nextLine());
                    // Creamos (o recuperamos) el pedido
                    Order order = useCase.createOrder(tableNumber);
                    System.out.println("Pedido creado para mesa #" + tableNumber);

                    boolean addMore = true;
                    while (addMore) {
                        System.out.print("Ingrese el ID del producto a agregar (0 para terminar): ");
                        int productId = Integer.parseInt(scanner.nextLine());
                        if (productId == 0) {
                            addMore = false;
                        } else {
                            boolean added = useCase.addItemToOrder(tableNumber, productId);
                            if (added) {
                                System.out.println("Producto agregado con éxito.");
                            } else {
                                System.out.println("No se pudo agregar. Verifique el ID o si el pedido está cancelado.");
                            }
                        }
                    }
                }
                case 3 -> {
                    // Ver pedidos activos
                    List<Order> orders = useCase.getActiveOrders();
                    if (orders.isEmpty()) {
                        System.out.println("No hay pedidos activos.");
                    } else {
                        orders.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    // Cerrar un pedido
                    System.out.print("Número de mesa a cerrar: ");
                    int tableNumber = Integer.parseInt(scanner.nextLine());
                    System.out.print("¿Tiene cupón de descuento? (máx 10%). Ingrese 0 si no: ");
                    double discount = Double.parseDouble(scanner.nextLine());
                    double total = useCase.closeOrder(tableNumber, discount);
                    if (total < 0) {
                        System.out.println("No se pudo cerrar el pedido. Revisa si existe o está cancelado.");
                    } else {
                        System.out.println("Pedido cerrado. Total a pagar: $" + total);
                    }
                }
                case 5 -> {
                    // Cancelar un pedido
                    System.out.print("Número de mesa a cancelar: ");
                    int tableNumber = Integer.parseInt(scanner.nextLine());
                    boolean canceled = useCase.cancelOrder(tableNumber);
                    if (canceled) {
                        System.out.println("Pedido cancelado exitosamente.");
                    } else {
                        System.out.println("No se pudo cancelar (ya se entregó o no existe).");
                    }
                }
                case 6 -> {
                    System.out.println("Saliendo del sistema. ¡Gracias!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}

