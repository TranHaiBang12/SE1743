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
    private Schedule s;
    private List<Tme> time;
    private List<FormTime> ft;

    public MovieTime(String name, String type, List<Tme> time) {
        this.name = name;
        this.time = time;
        this.type = type;
    }

    public MovieTime(String name, Schedule s, List<FormTime> ft) {
        this.name = name;
        this.s = s;
        this.ft = ft;
    }
    
    
    
    public MovieTime(String name, String type, List<Tme> time, Schedule s) {
        this.name = name;
        this.time = time;
        this.type = type;
        this.s = s;
    }

    public List<FormTime> getFt() {
        return ft;
    }

    public void setFt(List<FormTime> ft) {
        this.ft = ft;
    }
    
    

    public Schedule getS() {
        return s;
    }

    public void setS(Schedule s) {
        this.s = s;
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
