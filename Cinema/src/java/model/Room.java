/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Room {
    private int roomID;
    private int cinID;
    private int roomType;
    private int floor;
    private int noRowSeats;
    private int noColSeats;
    private String status;

    public Room(int roomID, int cinID, int roomType, int floor, int noRowSeats, int noColSeats, String status) {
        this.roomID = roomID;
        this.cinID = cinID;
        this.roomType = roomType;
        this.floor = floor;
        this.noRowSeats = noRowSeats;
        this.noColSeats = noColSeats;
        this.status = status;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getCinID() {
        return cinID;
    }

    public void setCinID(int cinID) {
        this.cinID = cinID;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNoRowSeats() {
        return noRowSeats;
    }

    public void setNoRowSeats(int noRowSeats) {
        this.noRowSeats = noRowSeats;
    }

    public int getNoColSeats() {
        return noColSeats;
    }

    public void setNoColSeats(int noColSeats) {
        this.noColSeats = noColSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
