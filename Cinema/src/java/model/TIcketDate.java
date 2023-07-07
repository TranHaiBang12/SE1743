/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author acer
 */
public class TIcketDate {
    private int ID;
    private String dS;
    private int no;
    private String pc;
    private List<TIcketDate> tkd;
    private String type;

    public TIcketDate(int ID, String dS, int no, String pc) {
        this.ID = ID;
        this.dS = dS;
        this.no = no;
        this.pc = pc;
    }
    
    public TIcketDate(int ID, String dS, int no, String pc, String type) {
        this.ID = ID;
        this.dS = dS;
        this.no = no;
        this.pc = pc;
        this.type = type;
    }

    public TIcketDate(String dS) {
        this.dS = dS;
    }

    public TIcketDate(String dS, int no) {
        this.dS = dS;
        this.no = no;
    }
    
    public TIcketDate(String dS, int no, String pc) {
        this.dS = dS;
        this.no = no;
        this.pc = pc;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    public List<TIcketDate> getTkd() {
        return tkd;
    }

    public void setTkd(List<TIcketDate> tkd) {
        this.tkd = tkd;
    }
    
    
    

    public String getPc() {
        return pc;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }
    
    

    public String getdS() {
        return dS;
    }

    public void setdS(String dS) {
        this.dS = dS;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    
    
}
