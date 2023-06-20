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
public class DirectorInMov {
    private int movID;
    private List<String> directorName;

    public DirectorInMov(int movID, List<String> directorName) {
        this.movID = movID;
        this.directorName = directorName;
    }

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public List<String> getDirectorName() {
        return directorName;
    }

    public void setDirectorName(List<String> directorName) {
        this.directorName = directorName;
    }
    
    
}
