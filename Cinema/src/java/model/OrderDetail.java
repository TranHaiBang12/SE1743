/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class OrderDetail {
    private String orderID;
    private String productCode;
    private double discount;
    private double price;
    private int quantity;
    private Food f;

    public OrderDetail(String orderID, String productCode, double discount, double price, int quantity, Food f) {
        this.orderID = orderID;
        this.productCode = productCode;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
        this.f = f;
    }

    public Food getF() {
        return f;
    }

    public void setF(Food f) {
        this.f = f;
    }
    
    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    
}
