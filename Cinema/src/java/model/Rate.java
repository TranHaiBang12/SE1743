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
public class Rate {
    private String userName;
    private int movID;
    private String comments;
    private int rate;
    private String status;
    private String displayName;
    private Date date;

    public Rate(String userName, int movID, String comments, int rate, String status, String displayName) {
        this.userName = userName;
        this.movID = movID;
        this.comments = comments;
        this.rate = rate;
        this.status = status;
        this.displayName = displayName;
    }
    
    public Rate(String userName, int movID, String comments, int rate, String status, String displayName, Date date) {
        this.userName = userName;
        this.movID = movID;
        this.comments = comments;
        this.rate = rate;
        this.status = status;
        this.displayName = displayName;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    
}
