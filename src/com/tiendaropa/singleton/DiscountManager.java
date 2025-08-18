/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tiendaropa.singleton;

/**
 * Implementación del patrón Singleton para la gestión de descuentos.
 * Esta clase es responsable de aplicar diferentes tipos de descuentos
 * a los productos de la tienda de ropa online.
 * 
 * @author Equipo TiendaRopa
 * @version 1.0
 * @since 2025-08-16
 */
public class DiscountManager {
    
    // Instancia única (privada, estática y final)
    private static final DiscountManager instance = new DiscountManager();
    
    /**
     * Constructor privado para evitar la instanciación directa de la clase.
     * Parte esencial del patrón Singleton.
     */
    private DiscountManager() {
        // Inicialización de recursos si es necesario
        System.out.println("DiscountManager inicializado.");
    }
    
    /**
     * Método estático que proporciona el punto de acceso global a la instancia única.
     * 
     * @return La instancia única de DiscountManager
     */
    public static DiscountManager getInstance() {
        return instance;
    }
    
    /**
     * Aplica un descuento al precio proporcionado según el tipo de descuento.
     * 
     * @param price Precio original del producto
     * @param discountType Tipo de descuento a aplicar
     * @return El precio con el descuento aplicado
     */
    public double applyDiscount(double price, String discountType) {
        switch (discountType) {
            case "SEASONAL":
                return price * 0.8; // 20% de descuento
            case "MEMBER":
                return price * 0.9; // 10% de descuento
            case "SPECIAL":
                return price * 0.7; // 30% de descuento
            default:
                return price; // Sin descuento
        }
    }
}