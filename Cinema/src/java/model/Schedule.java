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
public class Schedule {
    private String cinName;
    private String cinType;
    private Date start;
    private Date end;
    private Time startTim;
    private Time endTim;

    public Schedule(String cinName, String cinType, Date start, Date end, Time startTim, Time endTim) {
        this.cinName = cinName;
        this.cinType = cinType;
        this.start = start;
        this.end = end;
        this.startTim = startTim;
        this.endTim = endTim;
    }

    public String getCinName() {
        return cinName;
    }

    public void setCinName(String cinName) {
        this.cinName = cinName;
    }

    public String getCinType() {
        return cinType;
    }

    public void setCinType(String cinType) {
        this.cinType = cinType;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Time getStartTim() {
        return startTim;
    }

    public void setStartTim(Time startTim) {
        this.startTim = startTim;
    }

    public Time getEndTim() {
        return endTim;
    }

    public void setEndTim(Time endTim) {
        this.endTim = endTim;
    }
    
    
}
