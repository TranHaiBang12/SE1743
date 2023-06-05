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
public class MovieTime {
    private String name;
    private String type;
    private List<Tme> time;

    public MovieTime(String name, String type, List<Tme> time) {
        this.name = name;
        this.time = time;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tme> getTime() {
        return time;
    }

    public void setTime(List<Tme> time) {
        this.time = time;
    }
    
    
}
