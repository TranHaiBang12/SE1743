/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class TIcketDate {
    private Date dS;
    private int no;

    public TIcketDate(Date dS) {
        this.dS = dS;
    }

    public TIcketDate(Date dS, int no) {
        this.dS = dS;
        this.no = no;
    }

    public Date getdS() {
        return dS;
    }

    public void setdS(Date dS) {
        this.dS = dS;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
    
    
}
