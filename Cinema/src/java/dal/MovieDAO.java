/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Movies;

/**
 *
 * @author acer
 */
public class MovieDAO extends DBContext{
    public List<Movies> getAllMoviesNowShowing() {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies WHERE movID = 1";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Movies m = new Movies(rs.getInt("movid"), rs.getString("movname"), rs.getDate("startdate"), rs.getDouble("time(min)"), rs.getString("language"), rs.getString("origin"), rs.getDouble("avrrate"), rs.getString("notes"), rs.getString("status"), rs.getString("studio"), rs.getString("img").substring(1));
                System.out.println(m.getMovName());
                mv.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return mv;
    }
    
    public List<Movies> getAllMoviesNotShownYet() {
        List<Movies> mv = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Movies WHERE Status = N'Chưa chiếu'";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Movies m = new Movies(rs.getInt("movID"), rs.getString("movName"), rs.getDate("StartDate"), rs.getDouble("Time"), rs.getString("Language"), rs.getString("Origin"), rs.getDouble("AvrRate"), rs.getString("Notes"), rs.getString("Status"), rs.getString("Studio"), rs.getString("Img"));
                mv.add(m);
            }
        } catch (Exception e) {
        }
        return mv;
    }
}
