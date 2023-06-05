/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.MovieTime;
import model.Schedule;
import model.Tme;

/**
 *
 * @author acer
 */
public class ScheDAO extends DBContext{
    public List<Schedule> getMoviesBySchedule(int id, String city, String start, String end, String formName) {
        List<Schedule> ls = new ArrayList<>();
        try {
            String sql = "SELECT *, CAST(startTime AS date) AS startDate, CAST(endTime AS date) AS endDate, CAST(startTime AS time) AS startTim, CAST(endTime AS time) AS endTim FROM Schedule JOIN Form ON Schedule.formID = Form.formID JOIN Cinema ON Schedule.cinID = Cinema.cinID JOIN CinemaType ON Cinema.cinType= CinemaType.ctypeID WHERE movID = ? AND City = ? AND startTime >= ? AND endTime <= ? AND formName =  ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, city);
            st.setString(3, start);
            st.setString(4, end);
            st.setString(5, formName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule(rs.getString("cinName"), rs.getString("ctypeName"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getTime("startTim"), rs.getTime("endTim"));
                ls.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
                    
        }
        return ls;
    }
    
    public List<String> getCinNameBySchedule(int id, String city, String start, String end, String formName) {
        List<String> ls = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT cinName FROM Schedule JOIN Form ON Schedule.formID = Form.formID JOIN Cinema ON Schedule.cinID = Cinema.cinID JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE movID = ? AND City = ? AND startTime >= ? AND endTime <= ? AND formName = ? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, city);
            st.setString(3, start);
            st.setString(4, end);
            st.setString(5, formName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ls.add(rs.getString("cinName"));
            }
        } catch (Exception e) {
            System.out.println(e);
                    
        }
        return ls;
    }
    
    public List<Tme> getMovieTimeBySchedule(int id, String city, String start, String end, String formName, String cinName) {
        List<Tme> ls = new ArrayList<>();
        try {
            String sql = "SELECT CONVERT(VARCHAR(5), CAST(startTime AS time), 108) AS startTim, CONVERT(VARCHAR(5), CAST(endTime AS time), 108) AS endTim FROM Schedule JOIN Form ON Schedule.formID = Form.formID JOIN Cinema ON Schedule.cinID = Cinema.cinID JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE movID = ? AND City = ? AND startTime >= ? AND endTime <= ? AND formName = ? AND cinName = ? ORDER BY startTime";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, city);
            st.setString(3, start);
            st.setString(4, end);
            st.setString(5, formName);
            st.setString(6, cinName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tme t = new Tme(rs.getString("startTim"), rs.getString("endTim"));
                ls.add(t);                   
            }
        } catch (Exception e) {
            System.out.println(e);
                    
        }
        return ls;
    }
}
