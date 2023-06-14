/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author acer
 */
public class OrderByDate {
    private String date;
    private List<Order> o;

    public OrderByDate(String date, List<Order> o) {
        this.date = date;
        this.o = o;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Order> getO() {
        return o;
    }

    public void setO(List<Order> o) {
        this.o = o;
    }
    
    
}
