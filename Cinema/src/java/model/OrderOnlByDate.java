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
public class OrderOnlByDate {
    private String date;
    private List<OrderOnl> o;
    private List<OrderOff> of;

    public OrderOnlByDate(String date, List<OrderOnl> o) {
        this.date = date;
        this.o = o;
    }
    

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<OrderOnl> getO() {
        return o;
    }

    public void setO(List<OrderOnl> o) {
        this.o = o;
    }
    
    
}
