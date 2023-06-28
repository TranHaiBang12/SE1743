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
public class MovieGenre {
    private int movID;
    private int genreID;
    private List<String> genreName;
    private String genre;

    public MovieGenre(int genreID, String genre) {
        this.genreID = genreID;
        this.genre = genre;
    }
    
    

    public MovieGenre(int movID, int genreID, List<String> genreName) {
        this.movID = movID;
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    

    public int getMovID() {
        return movID;
    }

    public void setMovID(int movID) {
        this.movID = movID;
    }

    public int getGenreID() {
        return genreID;
    }

    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    public List<String> getGenreName() {
        return genreName;
    }

    public void setGenreName(List<String> genreName) {
        this.genreName = genreName;
    }
    
    
}
