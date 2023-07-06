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
public class DeviceDistribution {
    private String deviceCode;
    private String deviceBarCode;
    private int cinID;
    private String cinName;
    private int roomID;
    private Date dte;
    private Time tme;
    private String idType;
    private String typeName;
    private double price;
    private String descript;
    private String img;
    private String status;

    public DeviceDistribution(String deviceCode, String deviceBarCode, int cinID, String cinName, int roomID, Date dte, Time tme, String idType, String typeName, double price, String descript, String img) {
        this.deviceCode = deviceCode;
        this.deviceBarCode = deviceBarCode;
        this.cinID = cinID;
        this.cinName = cinName;
        this.roomID = roomID;
        this.dte = dte;
        this.tme = tme;
        this.idType = idType;
        this.typeName = typeName;
        this.price = price;
        this.descript = descript;
        this.img = img;
    }
    
    

    public DeviceDistribution(String deviceCode, String deviceBarCode, int cinID, String cinName, int roomID, Date dte, Time tme) {
        this.deviceCode = deviceCode;
        this.deviceBarCode = deviceBarCode;
        this.cinID = cinID;
        this.cinName = cinName;
        this.roomID = roomID;
        this.dte = dte;
        this.tme = tme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceBarCode() {
        return deviceBarCode;
    }

    public void setDeviceBarCode(String deviceBarCode) {
        this.deviceBarCode = deviceBarCode;
    }

    public int getCinID() {
        return cinID;
    }

    public void setCinID(int cinID) {
        this.cinID = cinID;
    }

    public String getCinName() {
        return cinName;
    }

    public void setCinName(String cinName) {
        this.cinName = cinName;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Date getDte() {
        return dte;
    }

    public void setDte(Date dte) {
        this.dte = dte;
    }

    public Time getTme() {
        return tme;
    }

    public void setTme(Time tme) {
        this.tme = tme;
    }

    

    
    
}
