/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.DirectorInMov;
import model.MovieGenre;
import model.StarInMov;

/**
 *
 * @author acer
 */
public class DiStaGenreMovDAO extends DBContext {
    
    public void dltAllDir(int movID) {
        try {
            String sql = "DELETE FROM Directors WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltAllStar(int movID) {
        try {
            String sql = "DELETE FROM Stars WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltAllGenre(int movID) {
        try {
            String sql = "DELETE FROM Genre WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public DirectorInMov getAllDirectorByMovID(int movID) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Directors WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("directorName"));

            }
            DirectorInMov dim = new DirectorInMov(movID, list);
            return dim;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public StarInMov getAllStarByMovID(int movID) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Stars WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("starName"));
            }
            StarInMov sim = new StarInMov(movID, list, null);
            return sim;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public MovieGenre getAllGenreByMovID(int movID) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT Genre.*, genreName FROM Genre JOIN MoviesGenre ON Genre.genreID = MoviesGenre.genreID WHERE movID =  ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("genreName"));
            }
            MovieGenre mg = new MovieGenre(movID, 0, list);
            return mg;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
