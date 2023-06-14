/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;

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
    private String type;
    private String scheNo;
    private String cinName;
    private int roomID;
    private String movName;
    private String formName;
    private Date startDate;
    private String startTime;

    public OrderTicketDetail(String orderID, String productCode, String seatType, int seatNumber, double discount, double price) {
        this.orderID = orderID;
        this.productCode = productCode;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.discount = discount;
        this.price = price;
    }

    public OrderTicketDetail(String orderID, String productCode, String seatType, int seatNumber, double discount, double price, String type, String scheNo, String cinName, int roomID, String movName, String formName, Date startDate, String startTime) {
        this.orderID = orderID;
        this.productCode = productCode;
        this.seatType = seatType;
        this.seatNumber = seatNumber;
        this.discount = discount;
        this.price = price;
        this.type = type;
        this.scheNo = scheNo;
        this.cinName = cinName;
        this.roomID = roomID;
        this.movName = movName;
        this.formName = formName;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScheNo() {
        return scheNo;
    }

    public void setScheNo(String scheNo) {
        this.scheNo = scheNo;
    }

    public String getCinName() {
        return cinName;
    }

    public void setCinName(String cinName) {
        this.cinName = cinName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getMovName() {
        return movName;
    }

    public void setMovName(String movName) {
        this.movName = movName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
