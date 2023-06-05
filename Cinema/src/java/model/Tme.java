/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;

/**
 *
 * @author acer
 */
public class Tme {
    private String scheNo;
    private String start;
    private String end;

    public Tme(String scheNo, String start, String end) {
        this.scheNo = scheNo;
        this.start = start;
        this.end = end;
    }

    public String getScheNo() {
        return scheNo;
    }

    public void setScheNo(String scheNo) {
        this.scheNo = scheNo;
    }
    
    

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
    
    
}
