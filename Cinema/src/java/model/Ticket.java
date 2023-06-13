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
public class Ticket {
    private String productCode;
    private int seatType;
    private String type;
    private String scheNo;
    private int numberLeft;
    private String status;
    private int movID;
    private int formID;
    private int cinID;
    private int roomID;
    private Date startDate;
    private String startTime;
    private Date endDate;
    private String endTime;
    private int row;
    private String col;
    private double price;
    private double discount;
    private int discontinued;
    private int ID;
    private Movies movie;

    
    public Ticket(String productCode, String type, String scheNo, int numberLeft, String status) {
        this.productCode = productCode;
        this.type = type;
        this.scheNo = scheNo;
        this.numberLeft = numberLeft;
        this.status = status;
    }

    public Ticket(String productCode, int seatType, String scheNo, int numberLeft, String status, int movID, int formID, int cinID, int roomID, Date startDate, String startTime, Date endDate, String endTime, int row, String col, double price, double discount, int discontinued) {
        this.productCode = productCode;
        this.seatType = seatType;
        this.scheNo = scheNo;
        this.numberLeft = numberLeft;
        this.status = status;
        this.movID = movID;
        this.formID = formID;
        this.cinID = cinID;
        this.roomID = roomID;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.row = row;
        this.col = col;
        this.price = price;
        this.discount = discount;
        this.discontinued = discontinued;
    }

    public Ticket(int ID,  String productCode, int seatType, String scheNo, int numberLeft, String status, int row, String col, double price, double discount, int discontinued) {
        this.ID = ID;
        this.productCode = productCode;
        this.seatType = seatType;
        this.scheNo = scheNo;
        this.numberLeft = numberLeft;
        this.status = status;
        this.row = row;
        this.col = col;
        this.price = price;
        this.discount = discount;
        this.discontinued = discontinued;
    }
    
    public Ticket(int ID,int movID, Movies m, String productCode, int seatType, String scheNo, int numberLeft, String status, int row, String col, double price, double discount, int discontinued, int cinID) {
        this.movie = m;
        this.ID = ID;
        this.productCode = productCode;
        this.seatType = seatType;
        this.scheNo = scheNo;
        this.numberLeft = numberLeft;
        this.status = status;
        this.row = row;
        this.col = col;
        this.price = price;
        this.discount = discount;
        this.discontinued = discontinued;
        this.cinID = cinID;
    }

    public Movies getMovie() {
        return movie;
    }

    public void setMovie(Movies movie) {
        this.movie = movie;
    }
    
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    

    public int getSeatType() {
        return seatType;
    }

    public void setSeatType(int seatType) {
        this.seatType = seatType;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }
    
    

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScheNo() {
        return scheNo;
    }

    public void setScheNo(String scheNo) {
        this.scheNo = scheNo;
    }

    public int getNumberLeft() {
        return numberLeft;
    }

    public void setNumberLeft(int numberLeft) {
        this.numberLeft = numberLeft;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
