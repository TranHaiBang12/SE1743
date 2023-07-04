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
    private int ID;
    private int movID;
    private String movName;
    private Date startDate;
    private double time;
    private String language;
    private String origin;
    private Date endDate;
    
    private String notes;
    private String status;
    private String studio;
    private String img;
    private int noRate5;
    private int noRate4;
    private int noRate3;
    private int noRate2;
    private int noRate1;
    
    private double pRate5;
    private double pRate4;
    private double pRate3;
    private double pRate2;
    private double pRate1;
    
    private int sumRate;
    private int noRate;
    private double avrRate;
    
    private int numTickSell;
    private int allNumTick;
    private String pcNumTickSell;

    public Movies() {
    }

    public Movies(int ID, int movID, String movName, Date startDate, double time, String language, String origin, double avrRate, String notes, String status, String studio, String img) {
        this.ID = ID;
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
    
    public Movies(int ID, int movID, String movName, Date startDate, double time, String language, String origin, double avrRate, String notes, String status, String studio, String img, Date endDate) {
        this.ID = ID;
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
        this.endDate = endDate;
    }

    public int getNumTickSell() {
        return numTickSell;
    }

    public void setNumTickSell(int numTickSell) {
        this.numTickSell = numTickSell;
    }

    public int getAllNumTick() {
        return allNumTick;
    }

    public void setAllNumTick(int allNumTick) {
        this.allNumTick = allNumTick;
    }

    public String getPcNumTickSell() {
        return pcNumTickSell;
    }

    public void setPcNumTickSell(String pcNumTickSell) {
        this.pcNumTickSell = pcNumTickSell;
    }
    
    

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    

    public double getpRate5() {
        return pRate5;
    }

    public void setpRate5(double pRate5) {
        this.pRate5 = pRate5;
    }

    public double getpRate4() {
        return pRate4;
    }

    public void setpRate4(double pRate4) {
        this.pRate4 = pRate4;
    }

    public double getpRate3() {
        return pRate3;
    }

    public void setpRate3(double pRate3) {
        this.pRate3 = pRate3;
    }

    public double getpRate2() {
        return pRate2;
    }

    public void setpRate2(double pRate2) {
        this.pRate2 = pRate2;
    }

    public double getpRate1() {
        return pRate1;
    }

    public void setpRate1(double pRate1) {
        this.pRate1 = pRate1;
    }
    
    

    public int getNoRate5() {
        return noRate5;
    }

    public void setNoRate5(int noRate5) {
        this.noRate5 = noRate5;
    }

    public int getNoRate4() {
        return noRate4;
    }

    public void setNoRate4(int noRate4) {
        this.noRate4 = noRate4;
    }

    public int getNoRate3() {
        return noRate3;
    }

    public void setNoRate3(int noRate3) {
        this.noRate3 = noRate3;
    }

    public int getNoRate2() {
        return noRate2;
    }

    public void setNoRate2(int noRate2) {
        this.noRate2 = noRate2;
    }

    public int getNoRate1() {
        return noRate1;
    }

    public void setNoRate1(int noRate1) {
        this.noRate1 = noRate1;
    }

    public int getSumRate() {
        return sumRate;
    }

    public void setSumRate(int sumRate) {
        this.sumRate = sumRate;
    }

    public int getNoRate() {
        return noRate;
    }

    public void setNoRate(int noRate) {
        this.noRate = noRate;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
