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
public class TimekeepingMonth {
    private int month;
    private List<Timekeeping> list;

    public TimekeepingMonth(int month, List<Timekeeping> list) {
        this.month = month;
        this.list = list;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public List<Timekeeping> getList() {
        return list;
    }

    public void setList(List<Timekeeping> list) {
        this.list = list;
    }
    
    
}
