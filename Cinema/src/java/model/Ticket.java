/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Ticket {
    private String productCode;
    private String type;
    private String scheNo;
    private int numberLeft;
    private String status;

    public Ticket(String productCode, String type, String scheNo, int numberLeft, String status) {
        this.productCode = productCode;
        this.type = type;
        this.scheNo = scheNo;
        this.numberLeft = numberLeft;
        this.status = status;
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
