/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.FormMD;
import model.MovieTime;
import model.Schedule;
import model.Tme;

/**
 *
 * @author acer
 */
public class ScheDAO extends DBContext {

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
            String sql = "SELECT scheNo, CONVERT(VARCHAR(5), CAST(startTime AS time), 108) AS startTim, CONVERT(VARCHAR(5), CAST(endTime AS time), 108) AS endTim FROM Schedule JOIN Form ON Schedule.formID = Form.formID JOIN Cinema ON Schedule.cinID = Cinema.cinID JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE movID = ? AND City = ? AND startTime >= ? AND endTime <= ? AND formName = ? AND cinName = ? ORDER BY startTime";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, city);
            st.setString(3, start);
            st.setString(4, end);
            st.setString(5, formName);
            st.setString(6, cinName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tme t = new Tme(rs.getString("scheNo"), rs.getString("startTim"), rs.getString("endTim"));
                ls.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return ls;
    }

    public Schedule getScheduleByID(String ID) {
        try {
            String sql = "SELECT *, CONVERT(VARCHAR(5), CAST(startTime AS time), 108) AS startTim, CONVERT(VARCHAR(5), CAST(endTime AS time), 108) AS endTim, CAST(startTime AS date) AS startDate, CAST(endTime AS date) AS endDate FROM Schedule WHERE scheNo = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule(rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("startTim"), rs.getString("endTim"), ID, rs.getInt("movID"), rs.getInt("formID"), rs.getInt("cinID"), rs.getInt("roomID"));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);

        }
        return null;
    }

    public List<Schedule> getScheduleByCinemaID(int ID, Date d) {
        List<Schedule> list = new ArrayList<>();
        FormDAO fmd = new FormDAO();
        MovieDAO mvd = new MovieDAO();
        try {
            String sql = "SELECT *, CONVERT(VARCHAR(5), CAST(startTime AS time), 108) AS startTim, CONVERT(VARCHAR(5), CAST(endTime AS time), 108) AS endTim, CAST(startTime AS date) AS startDate, CAST(endTime AS date) AS endDate FROM Schedule WHERE cinID = ? AND CAST(startTime AS date) = ? ORDER BY movID, formID";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ID);
            st.setDate(2, d);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule(rs.getDate("startDate"), rs.getDate("endDate"), rs.getString("startTim"), rs.getString("endTim"), rs.getString("scheNo"), rs.getInt("movID"), rs.getInt("formID"), rs.getInt("cinID"), rs.getInt("roomID"), fmd.getFormById(rs.getInt("formID")).getFormName(), mvd.getMovieById(rs.getInt("movID")).getMovName(), mvd.getMovieById(rs.getInt("movID")).getImg());
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void addSchedule(String scheNo, int movID, int formID, int cinID, int roomID, Date start, Time startTime, Date end, Time endTime) {
        try {
            String sql = "INSERT INTO Schedule (scheNo, movID, formID, cinID, roomID, startDate, endDate, startTim, endTim) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setInt(2, movID);
            st.setInt(3, formID);
            st.setInt(4, cinID);
            st.setInt(5, roomID);
            st.setDate(6, start);
            st.setDate(7, end);
            st.setTime(8, startTime);
            st.setTime(9, endTime);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updSchedule(String scheNo, int movID, int formID, int cinID, int roomID, Date start, Time startTime, Date end, Time endTime) {
        try {
            String sql = "UPDATE Schedule SET movID = ?, formID = ?, cinID = ?, roomID = ?, startDate = ?, endDate = ?, startTim = " + startTime +", endTim = ? WHERE scheNo = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setInt(2, formID);
            st.setInt(3, cinID);
            st.setInt(4, roomID);
            st.setDate(5, start);
            st.setDate(6, end);
            st.setTime(7, endTime);
            st.setString(8, scheNo);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteSchedule(String scheNo) {
        try {
            String sql = "DELETE FROM Schedule WHERE scheNo = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);           
        }
    }
    
    public List<Schedule> getAllScheduleByMovID(int movID) {
        List<Schedule> list = new ArrayList<>();
        MovieDAO mvd = new MovieDAO();
        FormDAO fmd = new FormDAO();
        CinemaDAO cnd = new CinemaDAO();
        try {
            String sql = "SELECT * FROM Schedule WHERE movID = ? ORDER BY startDate, startTim";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Schedule s = new Schedule(rs.getString("scheNo"), rs.getInt("movID"), rs.getInt("formID"), rs.getInt("cinID"), rs.getInt("roomID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getTime("startTim").toString(), rs.getTime("endTim").toString(), mvd.getMovieById(rs.getInt("movID")).getMovName(), fmd.getFormById(rs.getInt("formID")).getFormName(), cnd.getCinemaByID(rs.getInt("cinID")).getCinName(), mvd.getMovieById(rs.getInt("movID")).getImg());
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Schedule> getScheduleByPage(List<Schedule> list, int start, int end) {
        List<Schedule> list2 = new ArrayList<>();
        try {
            for (int i = start; i <= end; i++) {
                list2.add(list.get(i));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list2;
    }
    
    public Schedule getScheduleByIn4(String scheNo, int movID, Date start, Time startTim, int roomID, int cinID, int tgianChieu) {
        try {
            String sql = "SELECT * FROM Schedule WHERE scheNo = ? AND movID = ? AND roomID = ? AND cinID = ? AND startDate = ? AND (DATEDIFF(minute, startTim, '" + startTim  + "') <= '" + tgianChieu + "' OR DATEDIFF(minute, '" + startTim  +"', startTim) <= '" + tgianChieu + "') ORDER BY startDate, startTim";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setInt(2, movID);
            st.setInt(3, roomID);
            st.setInt(4, cinID);
            st.setDate(5, start);
            MovieDAO mvd = new MovieDAO();
            ResultSet rs = st.executeQuery();
            while(rs.next()) {                        
                Schedule s = new Schedule(rs.getString("scheNo"), rs.getInt("movID"), rs.getInt("formID"), rs.getInt("cinID"), rs.getInt("roomID"), rs.getDate("startDate"), rs.getDate("endDate"), rs.getTime("startTim").toString(), rs.getTime("endTim").toString(), null, null, null, null);
                return s;
            }
        } catch (Exception e) {
            System.out.println("1");
                    
            System.out.println(e);
        }
        return null;
    }
    
    public String convertTime(String date, String tme, int time) {
        try {
            String sql = "SELECT DATEADD(minute, " + time + ", '" + date + " " + tme + "') AS Time";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getDate("Time").toString() + " " + rs.getTime("Time").toString();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<String> getAllScheduleHaveTicket() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT scheNo FROM TickTypeInSche";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("scheNo"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
