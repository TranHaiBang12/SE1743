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
public class Movies {
    private int movID;
    private String movName;
    private Date startDate;
    private double time;
    private String language;
    private String origin;
    private double avrRate;
    private String notes;
    private String status;
    private String studio;
    private String img;

    public Movies() {
    }

    public Movies(int movID, String movName, Date startDate, double time, String language, String origin, double avrRate, String notes, String status, String studio, String img) {
        this.movID = movID;
        this.movName = movName;
        this.startDate = startDate;
        this.time = time;
        this.language = language;
        this.origin = origin;
        this.avrRate = avrRate;
        this.notes = notes;
        this.status = status;
        this.studio = studio;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
    
    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public String getMovName() {
        return movName;
    }

    public void setMovName(String movName) {
        this.movName = movName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getAvrRate() {
        return avrRate;
    }

    public void setAvrRate(double avrRate) {
        this.avrRate = avrRate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    
    
    
}
