/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author acer
 */
public class Timekeeping {
    private int empID;
    private Time startWork;
    private Time endWork;
    private int shiftID;
    private int onLeave;
    private Date date;
    private Employee e;
    private Shift s;
    private String dateS;

    public Timekeeping(int empID, Time startWork, Time endWork, int shiftID, int onLeave, Date date, Employee e, Shift s) {
        this.empID = empID;
        this.startWork = startWork;
        this.endWork = endWork;
        this.shiftID = shiftID;
        this.onLeave = onLeave;
        this.date = date;
        this.e = e;
        this.s = s;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }
    
    

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public Time getStartWork() {
        return startWork;
    }

    public void setStartWork(Time startWork) {
        this.startWork = startWork;
    }

    public Time getEndWork() {
        return endWork;
    }

    public void setEndWork(Time endWork) {
        this.endWork = endWork;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public int getOnLeave() {
        return onLeave;
    }

    public void setOnLeave(int onLeave) {
        this.onLeave = onLeave;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getE() {
        return e;
    }

    public void setE(Employee e) {
        this.e = e;
    }

    public Shift getS() {
        return s;
    }

    public void setS(Shift s) {
        this.s = s;
    }
    
    
}
