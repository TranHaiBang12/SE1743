/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class DeviceImport {
    private String deviceCode;
    private String idType;
    private String typeName;
    private double price;
    private String descript;
    private String img;

    public DeviceImport(String idType, String typeName) {
        this.idType = idType;
        this.typeName = typeName;
    }
    
    

    public DeviceImport(String deviceCode, String idType, String typeName, double price, String descript, String img) {
        this.deviceCode = deviceCode;
        this.idType = idType;
        this.typeName = typeName;
        this.price = price;
        this.descript = descript;
        this.img = img;
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
    
    
}
