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

    public Account() {
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
