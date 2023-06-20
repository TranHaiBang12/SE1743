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
public class AccountPoint {
    private String username;
    private int point;
    private String orderID;
    private Date date;
    private Time time;
    private String status;

    public AccountPoint(String username, int point) {
        this.username = username;
        this.point = point;
    }

    public AccountPoint(String username, int point, String orderID, Date date, Time time) {
        this.username = username;
        this.point = point;
        this.orderID = orderID;
        this.date = date;
        this.time = time;
    }

    public AccountPoint(String username, int point, String orderID, Date date, Time time, String status) {
        this.username = username;
        this.point = point;
        this.orderID = orderID;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    
    
}
