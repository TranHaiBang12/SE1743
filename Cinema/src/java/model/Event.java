/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 *
 * @author acer
 */
public class Event {
    private int eventCode;
    private String eventName;
    private String eventContent;
    private int eventType;
    private Date startDate;
    private Time startTime;
    private Date endDate;
    private Time endTime;
    private int numDateEx;
    private int applyO;
    private String img;
    private int discontinued;
    
    private String startS;
    private String endS;
    
    private List<Cinema> cin;
    private double discount;
    private List<String> product;
    private String type;
    
    private String orderID;
    
    private int idType;
    private String typeName;
    private int movID;

    public Event(int eventCode, int movID) {
        this.eventCode = eventCode;
        this.movID = movID;
    }
    
    

    public Event(int eventCode, String orderID) {
        this.eventCode = eventCode;
        this.orderID = orderID;
    }
    
    

    public Event(int eventCode, double discount) {
        this.eventCode = eventCode;
        this.discount = discount;

    }
    
    public Event(int eventCode, double discount, String type) {
        this.eventCode = eventCode;
        this.discount = discount;
        this.type = type;
    }

    public Event(int eventCode, List<String> product) {
        this.eventCode = eventCode;
        this.product = product;
    }
    
    

    public Event(int eventCode, String eventName, String eventContent, int eventType, Date startDate, Time startTime, Date endDate, Time endTime, int numDateEx, int applyO, String img, int discontinued) {
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.eventContent = eventContent;
        this.eventType = eventType;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.numDateEx = numDateEx;
        this.applyO = applyO;
        this.img = img;
        this.discontinued = discontinued;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }
    
    
    
    public List<Cinema> getCin() {
        return cin;
    }

    public void setCin(List<Cinema> cin) {
        this.cin = cin;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public List<String> getProduct() {
        return product;
    }

    public void setProduct(List<String> product) {
        this.product = product;
    }

    
    
    

    public String getStartS() {
        return startS;
    }

    public void setStartS(String startS) {
        this.startS = startS;
    }

    public String getEndS() {
        return endS;
    }

    public void setEndS(String endS) {
        this.endS = endS;
    }
    
    

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getNumDateEx() {
        return numDateEx;
    }

    public void setNumDateEx(int numDateEx) {
        this.numDateEx = numDateEx;
    }

    public int getApplyO() {
        return applyO;
    }

    public void setApplyO(int applyO) {
        this.applyO = applyO;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }
    
    
}
