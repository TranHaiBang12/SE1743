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
public class FormTime {
    private String type;
    private List<Tme> time;

    public FormTime(String type, List<Tme> time) {
        this.type = type;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Tme> getTime() {
        return time;
    }

    public void setTime(List<Tme> time) {
        this.time = time;
    }
    
    
}
