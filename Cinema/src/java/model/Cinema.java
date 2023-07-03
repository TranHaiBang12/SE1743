/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Cinema {
    private int ID;
    private int cinID;
    private String cinName;
    private int cinType;
    private String city;
    private String street;
    private String address;
    private int noRoom;
    private String fax;
    private String hotline;
    private String managerPhone;
    private String status;
    private String ctypeName;

    public Cinema(int cinID, String cinName) {
        this.cinID = cinID;
        this.cinName = cinName;
    }
    
    

    public Cinema(int ID, int cinID, String cinName, int cinType, String city, String street, String address, int noRoom, String fax, String hotline, String managerPhone, String status, String ctypeName) {
        this.ID = ID;
        this.cinID = cinID;
        this.cinName = cinName;
        this.cinType = cinType;
        this.city = city;
        this.street = street;
        this.address = address;
        this.noRoom = noRoom;
        this.fax = fax;
        this.hotline = hotline;
        this.managerPhone = managerPhone;
        this.status = status;
        this.ctypeName = ctypeName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public int getCinType() {
        return cinType;
    }

    public void setCinType(int cinType) {
        this.cinType = cinType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNoRoom() {
        return noRoom;
    }

    public void setNoRoom(int noRoom) {
        this.noRoom = noRoom;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCtypeName() {
        return ctypeName;
    }

    public void setCtypeName(String ctypeName) {
        this.ctypeName = ctypeName;
    }
    
    
}
