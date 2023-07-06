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
public class DeviceError {
    private String deviceCode;
    private String deviceBarCode;
    private int cinID;
    private String cinName;
    private int roomID;
    private Date dteDetected;
    private Time tmeDetected;
    private Date dteFixed;
    private Time tmeFixed;
    private double costIncured;

    public DeviceError(String deviceCode, String deviceBarCode, int cinID, String cinName, int roomID, Date dteDetected, Time tmeDetected, Date dteFixed, Time tmeFixed, double costIncured) {
        this.deviceCode = deviceCode;
        this.deviceBarCode = deviceBarCode;
        this.cinID = cinID;
        this.cinName = cinName;
        this.roomID = roomID;
        this.dteDetected = dteDetected;
        this.tmeDetected = tmeDetected;
        this.dteFixed = dteFixed;
        this.tmeFixed = tmeFixed;
        this.costIncured = costIncured;
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

    public Date getDteDetected() {
        return dteDetected;
    }

    public void setDteDetected(Date dteDetected) {
        this.dteDetected = dteDetected;
    }

    public Time getTmeDetected() {
        return tmeDetected;
    }

    public void setTmeDetected(Time tmeDetected) {
        this.tmeDetected = tmeDetected;
    }

    public Date getDteFixed() {
        return dteFixed;
    }

    public void setDteFixed(Date dteFixed) {
        this.dteFixed = dteFixed;
    }

    public Time getTmeFixed() {
        return tmeFixed;
    }

    public void setTmeFixed(Time tmeFixed) {
        this.tmeFixed = tmeFixed;
    }

    public double getCostIncured() {
        return costIncured;
    }

    public void setCostIncured(double costIncured) {
        this.costIncured = costIncured;
    }
    
    
}
