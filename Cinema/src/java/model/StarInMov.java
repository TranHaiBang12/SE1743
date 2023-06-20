/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author acer
 */
public class StarInMov {
    private int movID;
    private List<String> starName;
    private String role;

    public StarInMov(int movID, List<String> starName, String role) {
        this.movID = movID;
        this.starName = starName;
        this.role = role;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public List<String> getStarName() {
        return starName;
    }

    public void setStarName(List<String> starName) {
        this.starName = starName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
