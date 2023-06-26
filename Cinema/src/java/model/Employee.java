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
public class Employee {
    private int empID;
    private String lastName;
    private String firstName;
    private String gender;
    private String dob;
    private String address;
    private String cccd;
    private String phone;
    private String email;
    private String hiredDate;
    private String position;
    private int cinID;
    private int managerID;
    private String img;
    private double salary;
    private String username;
    private int role;
    private String password;
    private String cinName;

    public Employee(int empID, String lastName, String firstName, String gender, String dob, String address, String cccd, String phone, String email, String hiredDate, String position, int cinID, int managerID, String img, double salary) {
        this.empID = empID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.hiredDate = hiredDate;
        this.position = position;
        this.cinID = cinID;
        this.managerID = managerID;
        this.img = img;
        this.salary = salary;
    }

    public Employee(int empID, String lastName, String firstName, String gender, String dob, String address, String cccd, String phone, String email, String hiredDate, String position, int cinID, int managerID, String img, double salary, String username, int role, String password) {
        this.empID = empID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.hiredDate = hiredDate;
        this.position = position;
        this.cinID = cinID;
        this.managerID = managerID;
        this.img = img;
        this.salary = salary;
        this.username = username;
        this.role = role;
        this.password = password;
    }
    
    public Employee(int empID, String lastName, String firstName, String gender, String dob, String address, String cccd, String phone, String email, String hiredDate, String position, int cinID, int managerID, String img, double salary, String username, int role, String password, String cinName) {
        this.empID = empID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.hiredDate = hiredDate;
        this.position = position;
        this.cinID = cinID;
        this.managerID = managerID;
        this.img = img;
        this.salary = salary;
        this.username = username;
        this.role = role;
        this.password = password;
        this.cinName = cinName;
    }

    public String getCinName() {
        return cinName;
    }

    public void setCinName(String cinName) {
        this.cinName = cinName;
    }
    
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(String hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCinID() {
        return cinID;
    }

    public void setCinID(int cinID) {
        this.cinID = cinID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
}
