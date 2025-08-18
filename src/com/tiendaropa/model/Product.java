/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tiendaropa.model;

/**
 * Clase que representa un producto de la tienda de ropa online.
 * Contiene los atributos básicos de un producto como id, nombre, 
 * precio, categoría, etc.
 *
 * @author Equipo TiendaRopa
 * @version 1.0
 * @since 2025-08-16
 */
public class Product {
    
    private String id;
    private String name;
    private String description;
    private double price;
    private String category;
    private String size;
    private String color;
    private int stock;
    
    /**
     * Constructor que inicializa un producto con todos sus atributos.
     * 
     * @param id Identificador único del producto
     * @param name Nombre del producto
     * @param description Descripción detallada del producto
     * @param price Precio base del producto antes de descuentos
     * @param category Categoría a la que pertenece (ej: camiseta, pantalón)
     * @param size Talla del producto
     * @param color Color del producto
     * @param stock Cantidad disponible en inventario
     */
    public Product(String id, String name, String description, double price, 
                  String category, String size, String color, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.size = size;
        this.color = color;
        this.stock = stock;
    }
    
    /**
     * Constructor mínimo para crear un producto con solo los datos esenciales.
     * 
     * @param id Identificador único del producto
     * @param name Nombre del producto
     * @param price Precio base del producto
     */
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = "";
        this.category = "General";
        this.size = "Unico";
        this.color = "N/A";
        this.stock = 0;
    }
    
    /**
     * Calcula el precio final aplicando el descuento correspondiente.
     * Utiliza el Singleton DiscountManager para obtener el precio con descuento.
     * 
     * @param discountType Tipo de descuento a aplicar
     * @return Precio con descuento aplicado
     */
    public double getFinalPrice(String discountType) {
        return com.tiendaropa.singleton.DiscountManager.getInstance().applyDiscount(price, discountType);
    }
    
    // Getters y Setters
    
    /**
     * Obtiene el identificador del producto.
     * 
     * @return Identificador único del producto
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del producto.
     * 
     * @param id Nuevo identificador para el producto
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return Nombre del producto
     */
    public String getName() {
        return name;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param name Nuevo nombre para el producto
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtiene la descripción del producto.
     * 
     * @return Descripción detallada del producto
     */
    public String getDescription() {
        return description;
    }

    /**
     * Establece la descripción del producto.
     * 
     * @param description Nueva descripción para el producto
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el precio base del producto.
     * 
     * @return Precio sin descuentos
     */
    public double getPrice() {
        return price;
    }

    /**
     * Establece el precio base del producto.
     * 
     * @param price Nuevo precio para el producto
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Obtiene la categoría del producto.
     * 
     * @return Categoría a la que pertenece el producto
     */
    public String getCategory() {
        return category;
    }

    /**
     * Establece la categoría del producto.
     * 
     * @param category Nueva categoría para el producto
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Obtiene la talla del producto.
     * 
     * @return Talla del producto
     */
    public String getSize() {
        return size;
    }

    /**
     * Establece la talla del producto.
     * 
     * @param size Nueva talla para el producto
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Obtiene el color del producto.
     * 
     * @return Color del producto
     */
    public String getColor() {
        return color;
    }

    /**
     * Establece el color del producto.
     * 
     * @param color Nuevo color para el producto
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtiene el stock disponible del producto.
     * 
     * @return Cantidad disponible en inventario
     */
    public int getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible del producto.
     * 
     * @param stock Nueva cantidad disponible en inventario
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Representación en texto del objeto producto.
     * 
     * @return Cadena de texto con los datos principales del producto
     */
    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + 
               ", category=" + category + ", size=" + size + ", color=" + color + 
               ", stock=" + stock + '}';
    }
}