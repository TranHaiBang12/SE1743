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
    private String startTim;
    private String endTim;
    private String scheNo;
    private int movID;
    private int formID;
    private int cinID;
    private int roomID;

    public Schedule(String cinName, String cinType, Date start, Date end, String startTim, String endTim) {
        this.cinName = cinName;
        this.cinType = cinType;
        this.start = start;
        this.end = end;
        this.startTim = startTim;
        this.endTim = endTim;
    }

    public Schedule(Date start, Date end, String startTim, String endTim, String scheNo, int movID, int formID, int cinID, int roomID) {

        this.start = start;
        this.end = end;
        this.startTim = startTim;
        this.endTim = endTim;
        this.scheNo = scheNo;
        this.movID = movID;
        this.formID = formID;
        this.cinID = cinID;
        this.roomID = roomID;
    }

    public String getScheNo() {
        return scheNo;
    }

    public void setScheNo(String scheNo) {
        this.scheNo = scheNo;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public int getFormID() {
        return formID;
    }

    public void setFormID(int formID) {
        this.formID = formID;
    }

    public int getCinID() {
        return cinID;
    }

    public void setCinID(int cinID) {
        this.cinID = cinID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
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

    public String getStartTim() {
        return startTim;
    }

    public void setStartTim(String startTim) {
        this.startTim = startTim;
    }

    public String getEndTim() {
        return endTim;
    }

    public void setEndTim(String endTim) {
        this.endTim = endTim;
    }
    
    
}
