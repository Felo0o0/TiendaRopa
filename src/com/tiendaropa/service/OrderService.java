/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tiendaropa.service;

import com.tiendaropa.model.Order;
import com.tiendaropa.model.Product;
import com.tiendaropa.singleton.DiscountManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servicio que gestiona las operaciones relacionadas con pedidos.
 * Utiliza el patrón Singleton DiscountManager para aplicar descuentos.
 *
 * @author Equipo TiendaRopa
 * @version 1.0
 * @since 2025-08-16
 */
public class OrderService {
    
    private Map<String, Order> orders;
    
    /**
     * Constructor que inicializa la colección de pedidos.
     */
    public OrderService() {
        this.orders = new HashMap<>();
    }
    
    /**
     * Crea un nuevo pedido para un cliente específico.
     * 
     * @param customerId Identificador del cliente
     * @return El pedido creado
     */
    public Order createOrder(String customerId) {
        Order order = new Order(customerId);
        orders.put(order.getId(), order);
        return order;
    }
    
    /**
     * Agrega un producto a un pedido existente.
     * 
     * @param orderId Identificador del pedido
     * @param product Producto a añadir
     * @return true si se añadió correctamente, false si el pedido no existe
     */
    public boolean addProductToOrder(String orderId, Product product) {
        if (orders.containsKey(orderId)) {
            orders.get(orderId).addProduct(product);
            return true;
        }
        return false;
    }
    
    /**
     * Calcula el precio total de un pedido aplicando descuentos.
     * Utiliza el Singleton DiscountManager para aplicar los descuentos.
     * 
     * @param orderId Identificador del pedido
     * @param discountType Tipo de descuento a aplicar
     * @return El total con descuento o -1 si el pedido no existe
     */
    public double calculateOrderTotal(String orderId, String discountType) {
        if (orders.containsKey(orderId)) {
            Order order = orders.get(orderId);
            order.setDiscountType(discountType);
            order.calculateTotal();
            return order.getTotalAmount();
        }
        return -1;
    }
    
    /**
     * Cambia el estado de un pedido.
     * 
     * @param orderId Identificador del pedido
     * @param newStatus Nuevo estado para el pedido
     * @return true si se actualizó correctamente, false si el pedido no existe
     */
    public boolean updateOrderStatus(String orderId, String newStatus) {
        if (orders.containsKey(orderId)) {
            orders.get(orderId).setStatus(newStatus);
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene un pedido por su identificador.
     * 
     * @param orderId Identificador del pedido
     * @return El pedido o null si no existe
     */
    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
    
    /**
     * Obtiene todos los pedidos almacenados.
     * 
     * @return Lista con todos los pedidos
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
    
    /**
     * Elimina un pedido.
     * 
     * @param orderId Identificador del pedido a eliminar
     * @return true si se eliminó correctamente, false si el pedido no existe
     */
    public boolean deleteOrder(String orderId) {
        if (orders.containsKey(orderId)) {
            orders.remove(orderId);
            return true;
        }
        return false;
    }
    
    /**
     * Aplica un descuento específico a un precio.
     * Utiliza directamente el Singleton DiscountManager.
     * 
     * @param price Precio original
     * @param discountType Tipo de descuento a aplicar
     * @return Precio con descuento aplicado
     */
    public double applyDiscountToPrice(double price, String discountType) {
        // Ejemplo de uso directo del Singleton
        return DiscountManager.getInstance().applyDiscount(price, discountType);
    }
}