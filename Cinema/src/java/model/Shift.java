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
public class Shift {
    private int shiftID;
    private int empID;
    private Time startShift;
    private Time endShift;
    private Date startDate;
    private Date endDate;

    public Shift(int shiftID, int empID, Time startShift, Time endShift, Date startDate, Date endDate) {
        this.shiftID = shiftID;
        this.empID = empID;
        this.startShift = startShift;
        this.endShift = endShift;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getShiftID() {
        return shiftID;
    }

    public void setShiftID(int shiftID) {
        this.shiftID = shiftID;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public Time getStartShift() {
        return startShift;
    }

    public void setStartShift(Time startShift) {
        this.startShift = startShift;
    }

    public Time getEndShift() {
        return endShift;
    }

    public void setEndShift(Time endShift) {
        this.endShift = endShift;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
}
