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
public class OrderOffByDate {
    private String date;
    private List<OrderOff> of;

    public OrderOffByDate(String date, List<OrderOff> of) {
        this.date = date;
        this.of = of;
    }
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<OrderOff> getOf() {
        return of;
    }

    public void setOf(List<OrderOff> of) {
        this.of = of;
    }
    
    
}
