/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tiendaropa.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un pedido en la tienda de ropa online.
 * Contiene información sobre los productos solicitados, cliente,
 * fechas y estado del pedido.
 *
 * @author Equipo TiendaRopa
 * @version 1.0
 * @since 2025-08-16
 */
public class Order {
    
    private String id;
    private String customerId;
    private Date orderDate;
    private List<Product> items;
    private String status;
    private double totalAmount;
    private String discountType;
    
    /**
     * Constructor que inicializa un pedido con todos sus atributos.
     * 
     * @param id Identificador único del pedido
     * @param customerId Identificador del cliente que realizó el pedido
     * @param orderDate Fecha en que se realizó el pedido
     * @param status Estado actual del pedido (pendiente, enviado, entregado, etc.)
     * @param discountType Tipo de descuento aplicado al pedido
     */
    public Order(String id, String customerId, Date orderDate, String status, String discountType) {
        this.id = id;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.status = status;
        this.discountType = discountType;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }
    
    /**
     * Constructor básico que inicializa un pedido solo con el ID del cliente.
     * Establece valores predeterminados para los demás atributos.
     * 
     * @param customerId Identificador del cliente que realiza el pedido
     */
    public Order(String customerId) {
        this.id = "ORD-" + System.currentTimeMillis();
        this.customerId = customerId;
        this.orderDate = new Date();
        this.status = "Pendiente";
        this.discountType = "NONE";
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }
    
    /**
     * Añade un producto al pedido y recalcula el total.
     * 
     * @param product Producto a añadir al pedido
     */
    public void addProduct(Product product) {
        items.add(product);
        calculateTotal();
    }
    
    /**
     * Elimina un producto del pedido y recalcula el total.
     * 
     * @param productId ID del producto a eliminar
     * @return true si el producto fue eliminado, false si no se encontró
     */
    public boolean removeProduct(String productId) {
        boolean removed = items.removeIf(product -> product.getId().equals(productId));
        if (removed) {
            calculateTotal();
        }
        return removed;
    }
    
    /**
     * Calcula el total del pedido aplicando los descuentos correspondientes.
     * Utiliza el Singleton DiscountManager para aplicar el descuento.
     */
    public void calculateTotal() {
        double sum = 0.0;
        for (Product product : items) {
            sum += product.getFinalPrice(discountType);
        }
        this.totalAmount = sum;
    }
    
    // Getters y Setters
    
    /**
     * Obtiene el identificador del pedido.
     * 
     * @return Identificador único del pedido
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del pedido.
     * 
     * @param id Nuevo identificador para el pedido
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el identificador del cliente.
     * 
     * @return Identificador del cliente que realizó el pedido
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Establece el identificador del cliente.
     * 
     * @param customerId Nuevo identificador del cliente
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * Obtiene la fecha del pedido.
     * 
     * @return Fecha en que se realizó el pedido
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Establece la fecha del pedido.
     * 
     * @param orderDate Nueva fecha para el pedido
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Obtiene la lista de productos en el pedido.
     * 
     * @return Lista de productos que componen el pedido
     */
    public List<Product> getItems() {
        return items;
    }

    /**
     * Establece la lista de productos del pedido y recalcula el total.
     * 
     * @param items Nueva lista de productos para el pedido
     */
    public void setItems(List<Product> items) {
        this.items = items;
        calculateTotal();
    }

    /**
     * Obtiene el estado actual del pedido.
     * 
     * @return Estado del pedido (pendiente, enviado, entregado, etc.)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Establece el estado del pedido.
     * 
     * @param status Nuevo estado para el pedido
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtiene el monto total del pedido con descuentos aplicados.
     * 
     * @return Monto total del pedido
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Obtiene el tipo de descuento aplicado al pedido.
     * 
     * @return Tipo de descuento aplicado
     */
    public String getDiscountType() {
        return discountType;
    }

    /**
     * Establece el tipo de descuento y recalcula el total.
     * 
     * @param discountType Nuevo tipo de descuento a aplicar
     */
    public void setDiscountType(String discountType) {
        this.discountType = discountType;
        calculateTotal();
    }
    
    /**
     * Representación en texto del objeto pedido.
     * 
     * @return Cadena de texto con los datos principales del pedido
     */
    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customerId=" + customerId + 
               ", orderDate=" + orderDate + ", status=" + status + 
               ", totalAmount=" + totalAmount + ", items=" + items.size() + "}";
    }
}
