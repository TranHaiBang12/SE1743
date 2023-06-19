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
public class OrderOff {

    private String orderID;
    private String userName;
    private String name;

    private String phone;
    private int empID;
    private int cinID;
    
    private String paymentType;
    private Date paymentDate;
    private Time paymentTime;
    private double totalAmount;
    private String cinName;
    private String empName;


    public OrderOff(String orderID, String userName, String name, String phone, int empID, int cinID, String paymentType, Date paymentDate, Time paymentTime, double totalAmount, String cinName) {
        this.orderID = orderID;
        this.userName = userName;
        this.name = name;
        this.phone = phone;
        this.empID = empID;
        this.cinID = cinID;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.totalAmount = totalAmount;
        this.cinName = cinName;
    }

    public OrderOff(String orderID, String userName, String name, String phone, int empID, int cinID, String paymentType, Date paymentDate, Time paymentTime) {
        this.orderID = orderID;
        this.userName = userName;
        this.name = name;
        this.phone = phone;
        this.empID = empID;
        this.cinID = cinID;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
    }
    
    public OrderOff(String orderID, String userName, String name, String phone, int empID, int cinID, String paymentType, Date paymentDate, Time paymentTime, String cinName, String empName) {
        this.orderID = orderID;
        this.userName = userName;
        this.name = name;
        this.phone = phone;
        this.empID = empID;
        this.cinID = cinID;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.paymentTime = paymentTime;
        this.cinName = cinName;
        this.empName = empName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
    
    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getCinID() {
        return cinID;
    }

    public void setCinID(int cinID) {
        this.cinID = cinID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Time getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Time paymentTime) {
        this.paymentTime = paymentTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCinName() {
        return cinName;
    }

    public void setCinName(String cinName) {
        this.cinName = cinName;
    }

    
}
