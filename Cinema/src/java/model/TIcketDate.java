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
    private String dS;
    private int no;
    private String pc;
    private List<TIcketDate> tkd;
    

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
