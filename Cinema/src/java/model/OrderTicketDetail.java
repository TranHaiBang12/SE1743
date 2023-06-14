/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class OrderTicketDetail {
    private String orderID;
    private String productCode;
    private String seatType;
    private int seatNumber;
    private double discount;
    private double price;
    private Ticket t;

    public OrderTicketDetail(String orderID, String productCode, String seatType, int seatNumber, double discount, double price, Ticket t) {
        this.orderID = orderID;
        this.productCode = productCode;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.discount = discount;
        this.price = price;
        this.t = t;
    }

    public Ticket getT() {
        return t;
    }

    public void setT(Ticket t) {
        this.t = t;
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

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
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
    
    
    
}
