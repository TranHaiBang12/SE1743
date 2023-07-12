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
public class Account {
    private String UserName;
    private String Gender;
    private Date Dob;
    private String Phone;
    private String Email;
    private String City;
    private int role;
    private String Password;
    
    private int numBuy;
    private int numBuyOnl;
    private int numBuyOff;
    private String pcOnl;
    private String pcOff;
    private int point;
    private int pointUse;
    private String pcPointUse;
    private int numRate;
    
    private int active;

    public Account(String UserName, int numBuy, int numBuyOnl, int numBuyOff, String pcOnl, String pcOff, int point, int pointUse, String pcPointUse, int numRate) {
        this.UserName = UserName;
        this.numBuy = numBuy;
        this.numBuyOnl = numBuyOnl;
        this.numBuyOff = numBuyOff;
        this.pcOnl = pcOnl;
        this.pcOff = pcOff;
        this.point = point;
        this.pointUse = pointUse;
        this.pcPointUse = pcPointUse;
        this.numRate = numRate;
    }
    
    public Account(String UserName, int numBuy, int numBuyOnl, int numBuyOff, String pcOnl, String pcOff, int point, int pointUse, String pcPointUse, int numRate, int active) {
        this.UserName = UserName;
        this.numBuy = numBuy;
        this.numBuyOnl = numBuyOnl;
        this.numBuyOff = numBuyOff;
        this.pcOnl = pcOnl;
        this.pcOff = pcOff;
        this.point = point;
        this.pointUse = pointUse;
        this.pcPointUse = pcPointUse;
        this.numRate = numRate;
        this.active = active;
    }
    
    

    public Account(String UserName, String Password, int role) {
        this.UserName = UserName;
        this.Password = Password;
        this.role = role;
    }

    public Account(String UserName, String Gender, Date Dob,  String Phone, String Email, String City, int role, String Password) {
        this.UserName = UserName;
        this.Gender = Gender;
        this.Dob = Dob;
        this.Phone = Phone;
        this.Email = Email;
        this.City = City;
        this.role = role;
        this.Password = Password;
    }
    
    public Account(String UserName, String Gender, Date Dob,  String Phone, String Email, String City, int role, String Password, int active) {
        this.UserName = UserName;
        this.Gender = Gender;
        this.Dob = Dob;
        this.Phone = Phone;
        this.Email = Email;
        this.City = City;
        this.role = role;
        this.Password = Password;
        this.active = active;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    
    public int getNumBuy() {
        return numBuy;
    }

    public void setNumBuy(int numBuy) {
        this.numBuy = numBuy;
    }

    public int getNumBuyOnl() {
        return numBuyOnl;
    }

    public void setNumBuyOnl(int numBuyOnl) {
        this.numBuyOnl = numBuyOnl;
    }

    public int getNumBuyOff() {
        return numBuyOff;
    }

    public void setNumBuyOff(int numBuyOff) {
        this.numBuyOff = numBuyOff;
    }

    public String getPcOnl() {
        return pcOnl;
    }

    public void setPcOnl(String pcOnl) {
        this.pcOnl = pcOnl;
    }

    public String getPcOff() {
        return pcOff;
    }

    public void setPcOff(String pcOff) {
        this.pcOff = pcOff;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPointUse() {
        return pointUse;
    }

    public void setPointUse(int pointUse) {
        this.pointUse = pointUse;
    }

    public String getPcPointUse() {
        return pcPointUse;
    }

    public void setPcPointUse(String pcPointUse) {
        this.pcPointUse = pcPointUse;
    }

    public int getNumRate() {
        return numRate;
    }

    public void setNumRate(int numRate) {
        this.numRate = numRate;
    }
    
    

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }


    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public Date getDob() {
        return Dob;
    }

    public void setDob(Date Dob) {
        this.Dob = Dob;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
}
