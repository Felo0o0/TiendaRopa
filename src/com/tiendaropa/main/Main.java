/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tiendaropa.main;

import com.tiendaropa.model.Order;
import com.tiendaropa.model.Product;
import com.tiendaropa.service.OrderService;
import com.tiendaropa.singleton.DiscountManager;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase principal que inicia la aplicacion y demuestra el uso del patron Singleton
 * a traves de un menu interactivo.
 *
 * @author Equipo TiendaRopa
 * @version 1.1
 * @since 2025-08-17
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static final OrderService orderService = new OrderService();
    private static final String[] DISCOUNT_TYPES = {"NONE", "SEASONAL", "MEMBER", "SPECIAL"};
    private static final String[] PRODUCT_CATEGORIES = {"Camisetas", "Pantalones", "Vestidos", "Chaquetas", "Zapatos"};
    private static final String[] PRODUCT_COLORS = {"Rojo", "Azul", "Negro", "Blanco", "Verde"};
    private static final String[] PRODUCT_SIZES = {"XS", "S", "M", "L", "XL"};

    /**
     * Metodo principal que inicia la aplicacion con un menu interactivo.
     *
     * @param args Argumentos de linea de comandos
     */
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE TIENDA DE ROPA ONLINE ===");
        System.out.println("Demostracion del patron Singleton");

        // Demostracion del patron Singleton
        DiscountManager discountManager1 = DiscountManager.getInstance();
        DiscountManager discountManager2 = DiscountManager.getInstance();

        // Verificacion de que ambas variables apuntan a la misma instancia
        System.out.println("Son la misma instancia " + (discountManager1 == discountManager2));
        System.out.println("Referencia 1: " + discountManager1);
        System.out.println("Referencia 2: " + discountManager2);
        System.out.println("\nPresione Enter para continuar...");
        scanner.nextLine();

        int opcion;
        do {
            mostrarMenu();
            opcion = obtenerOpcion();

            switch (opcion) {
                case 1:
                    crearOrdenAleatoria();
                    break;
                case 2:
                    crearCincoOrdenesAleatorias();
                    break;
                case 3:
                    mostrarTodasLasOrdenes();
                    break;
                case 4:
                    aplicarDescuentoAOrden();
                    break;
                case 5:
                    cambiarEstadoDeOrden();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema. Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion no valida. Intente nuevamente.");
            }

            if (opcion != 6) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }

        } while (opcion != 6);

        System.out.println("Gracias por utilizar el Sistema de Tienda de Ropa Online");
        scanner.close();
    }

    /**
     * Muestra el menu principal de opciones.
     */
    private static void mostrarMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Crear una orden de compra aleatoria");
        System.out.println("2. Crear cinco ordenes de compra aleatorias");
        System.out.println("3. Mostrar todas las ordenes");
        System.out.println("4. Aplicar descuento a una orden");
        System.out.println("5. Cambiar estado de una orden");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    /**
     * Obtiene la opcion seleccionada por el usuario.
     *
     * @return Numero de opcion seleccionada
     */
    private static int obtenerOpcion() {
        try {
            int opcion = Integer.parseInt(scanner.nextLine());
            return opcion;
        } catch (NumberFormatException e) {
            return 0; // Opcion invalida
        }
    }

    /**
     * Crea una orden de compra con productos aleatorios.
     */
    private static void crearOrdenAleatoria() {
        String customerId = "C" + (random.nextInt(100) + 1);
        Order order = orderService.createOrder(customerId);

        // Agregar entre 1 y 5 productos aleatorios
        int numProducts = random.nextInt(5) + 1;
        for (int i = 0; i < numProducts; i++) {
            Product product = generarProductoAleatorio();
            orderService.addProductToOrder(order.getId(), product);
        }

        System.out.println("\n=== ORDEN CREADA ===");
        System.out.println("ID de la orden: " + order.getId());
        System.out.println("Cliente: " + order.getCustomerId());
        System.out.println("Fecha: " + order.getOrderDate());
        System.out.println("Productos: " + order.getItems().size());
        System.out.println("Total: $" + String.format("%,.0f", order.getTotalAmount()) + " CLP");
    }

    /**
     * Crea cinco ordenes de compra con productos aleatorios.
     */
    private static void crearCincoOrdenesAleatorias() {
        System.out.println("\n=== CREANDO 5 ORDENES ALEATORIAS ===");

        for (int i = 0; i < 5; i++) {
            String customerId = "C" + (random.nextInt(100) + 1);
            Order order = orderService.createOrder(customerId);
            
            // Modificar el ID para que sea numerico de hasta 6 digitos
            String nuevoId = String.format("%06d", random.nextInt(1000000));
            order.setId(nuevoId);

            // Agregar entre 1 y 5 productos aleatorios
            int numProducts = random.nextInt(5) + 1;
            for (int j = 0; j < numProducts; j++) {
                Product product = generarProductoAleatorio();
                orderService.addProductToOrder(order.getId(), product);
            }

            System.out.println("\nOrden #" + (i+1));
            System.out.println("ID: " + order.getId());
            System.out.println("Cliente: " + order.getCustomerId());
            System.out.println("Productos: " + order.getItems().size());
            System.out.println("Total: $" + String.format("%,.0f", order.getTotalAmount()) + " CLP");
        }
    }

    /**
     * Muestra todas las ordenes existentes en el sistema.
     */
    private static void mostrarTodasLasOrdenes() {
        System.out.println("\n=== TODAS LAS ORDENES ===");

        if (orderService.getAllOrders().isEmpty()) {
            System.out.println("No hay ordenes en el sistema.");
            return;
        }

        for (Order order : orderService.getAllOrders()) {
            System.out.println("\nID: " + order.getId());
            System.out.println("Cliente: " + order.getCustomerId());
            System.out.println("Fecha: " + order.getOrderDate());
            System.out.println("Estado: " + order.getStatus());
            System.out.println("Tipo de descuento: " + order.getDiscountType());
            System.out.println("Productos: " + order.getItems().size());
            System.out.println("Total: $" + String.format("%,.0f", order.getTotalAmount()) + " CLP");

            // Mostrar productos de la orden
            System.out.println("Productos:");
            for (Product product : order.getItems()) {
                System.out.println("  - " + product.getName() + " ($" +
                        String.format("%,.0f", product.getPrice()) + " CLP)");
            }
        }
    }

    /**
     * Aplica un descuento a una orden existente.
     */
    private static void aplicarDescuentoAOrden() {
        System.out.println("\n=== APLICAR DESCUENTO A ORDEN ===");

        if (orderService.getAllOrders().isEmpty()) {
            System.out.println("No hay ordenes en el sistema.");
            return;
        }

        // Mostrar IDs de ordenes disponibles
        System.out.println("Ordenes disponibles:");
        for (Order order : orderService.getAllOrders()) {
            System.out.println("- " + order.getId() + " (Cliente: " + order.getCustomerId() +
                    ", Total actual: $" + String.format("%,.0f", order.getTotalAmount()) + " CLP)");
        }

        System.out.print("\nIngrese el ID de la orden: ");
        String orderId = scanner.nextLine();

        Order order = orderService.getOrder(orderId);
        if (order == null) {
            System.out.println("Orden no encontrada.");
            return;
        }

        // Mostrar tipos de descuento disponibles
        System.out.println("\nTipos de descuento disponibles:");
        System.out.println("1. NONE - Sin descuento");
        System.out.println("2. SEASONAL - Descuento de temporada (20%)");
        System.out.println("3. MEMBER - Descuento de miembro (10%)");
        System.out.println("4. SPECIAL - Descuento especial (30%)");

        System.out.print("Seleccione un tipo de descuento (1-4): ");
        int discountOption;
        try {
            discountOption = Integer.parseInt(scanner.nextLine());
            if (discountOption < 1 || discountOption > 4) {
                System.out.println("Opcion no valida. No se aplico ningun descuento.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no valida. No se aplico ningun descuento.");
            return;
        }

        String discountType = DISCOUNT_TYPES[discountOption - 1];
        double originalTotal = order.getTotalAmount();
        double discountedTotal = orderService.calculateOrderTotal(orderId, discountType);

        System.out.println("\nDescuento aplicado correctamente:");
        System.out.println("Tipo de descuento: " + discountType);
        System.out.println("Total original: $" + String.format("%,.0f", originalTotal) + " CLP");
        System.out.println("Total con descuento: $" + String.format("%,.0f", discountedTotal) + " CLP");
        System.out.println("Ahorro: $" + String.format("%,.0f", (originalTotal - discountedTotal)) + " CLP");
    }

    /**
     * Cambia el estado de una orden existente.
     */
    private static void cambiarEstadoDeOrden() {
        System.out.println("\n=== CAMBIAR ESTADO DE ORDEN ===");

        if (orderService.getAllOrders().isEmpty()) {
            System.out.println("No hay ordenes en el sistema.");
            return;
        }

        // Mostrar IDs de ordenes disponibles
        System.out.println("Ordenes disponibles:");
        for (Order order : orderService.getAllOrders()) {
            System.out.println("- " + order.getId() + " (Cliente: " + order.getCustomerId() +
                    ", Estado actual: " + order.getStatus() + ")");
        }

        System.out.print("\nIngrese el ID de la orden: ");
        String orderId = scanner.nextLine();

        Order order = orderService.getOrder(orderId);
        if (order == null) {
            System.out.println("Orden no encontrada.");
            return;
        }

        // Mostrar estados disponibles
        System.out.println("\nEstados disponibles:");
        System.out.println("1. Pendiente");
        System.out.println("2. Procesando");
        System.out.println("3. Enviado");
        System.out.println("4. Entregado");
        System.out.println("5. Cancelado");

        System.out.print("Seleccione un nuevo estado (1-5): ");
        int statusOption;
        try {
            statusOption = Integer.parseInt(scanner.nextLine());
            if (statusOption < 1 || statusOption > 5) {
                System.out.println("Opcion no valida. No se cambio el estado.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada no valida. No se cambio el estado.");
            return;
        }

        String[] estados = {"Pendiente", "Procesando", "Enviado", "Entregado", "Cancelado"};
        String newStatus = estados[statusOption - 1];

        orderService.updateOrderStatus(orderId, newStatus);
        System.out.println("\nEstado actualizado correctamente:");
        System.out.println("Orden: " + orderId);
        System.out.println("Nuevo estado: " + newStatus);
    }

    /**
     * Genera un producto con caracteristicas aleatorias.
     *
     * @return Producto generado aleatoriamente
     */
    private static Product generarProductoAleatorio() {
        String id = "P" + (random.nextInt(1000) + 1);
        String category = PRODUCT_CATEGORIES[random.nextInt(PRODUCT_CATEGORIES.length)];
        String name = generarNombreProducto(category);
        double price = 20000 + random.nextInt(40001); // Entre 20.000 y 60.000 CLP
        String color = PRODUCT_COLORS[random.nextInt(PRODUCT_COLORS.length)];
        String size = PRODUCT_SIZES[random.nextInt(PRODUCT_SIZES.length)];
        int stock = random.nextInt(100) + 1;

        Product product = new Product(id, name, price);
        product.setCategory(category);
        product.setColor(color);
        product.setSize(size);
        product.setStock(stock);
        product.setDescription("Producto de " + category + " en color " + color + ", talla " + size);

        return product;
    }

    /**
     * Genera un nombre de producto basado en su categoria.
     *
     * @param category Categoria del producto
     * @return Nombre generado para el producto
     */
    private static String generarNombreProducto(String category) {
        String[] prefijos = {"Elegante", "Casual", "Moderno", "Clasico", "Deportivo", "Formal"};
        String prefijo = prefijos[random.nextInt(prefijos.length)];

        switch (category) {
            case "Camisetas":
                String[] tiposCamiseta = {"Polo", "Manga corta", "Manga larga", "Estampada", "Basica"};
                return prefijo + " " + category.substring(0, category.length() - 1) + " " +
                        tiposCamiseta[random.nextInt(tiposCamiseta.length)];
            case "Pantalones":
                String[] tiposPantalon = {"Vaquero", "Chino", "Deportivo", "Formal", "Cargo"};
                return prefijo + " " + category.substring(0, category.length() - 1) + " " +
                        tiposPantalon[random.nextInt(tiposPantalon.length)];
            case "Vestidos":
                String[] tiposVestido = {"Cocktail", "Casual", "Noche", "Verano", "Fiesta"};
                return prefijo + " " + category.substring(0, category.length() - 1) + " de " +
                        tiposVestido[random.nextInt(tiposVestido.length)];
            case "Chaquetas":
                String[] tiposChaqueta = {"Cuero", "Vaquera", "Bomber", "Acolchada", "Impermeable"};
                return prefijo + " " + category.substring(0, category.length() - 1) + " de " +
                        tiposChaqueta[random.nextInt(tiposChaqueta.length)];
            case "Zapatos":
                String[] tiposZapato = {"Deportivos", "Formales", "Casuales", "Botas", "Sandalias"};
                return prefijo + " " + category + " " + tiposZapato[random.nextInt(tiposZapato.length)];
            default:
                return prefijo + " " + category;
        }
    }
}