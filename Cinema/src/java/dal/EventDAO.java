/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.Event;

/**
 *
 * @author acer
 */
public class EventDAO extends DBContext{
    public List<Event> getAllEventContinued() {
        List<Event> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Event WHERE Discontinued = 0";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), rs.getString("Name"), rs.getString("Content"), rs.getInt("Type"), rs.getDate("startDate"), rs.getTime("startDate"), rs.getDate("endDate"), rs.getTime("endDate"), rs.getInt("numDateCanEx"), rs.getInt("applyOther"), rs.getString("Img"), rs.getInt("Discontinued"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Event getEventByCode(int code) {
        try {
            String sql = "SELECT * FROM Event WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            CinemaDAO cnd = new CinemaDAO();
            List<Cinema> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), rs.getString("Name"), rs.getString("Content"), rs.getInt("Type"), rs.getDate("startDate"), rs.getTime("startDate"), rs.getDate("endDate"), rs.getTime("endDate"), rs.getInt("numDateCanEx"), rs.getInt("applyOther"), rs.getString("Img"), rs.getInt("Discontinued"));
                sql = "SELECT * FROM CinApplyEvent WHERE EventCode = ?";
                st = connection.prepareStatement(sql);
                st.setInt(1, code);
                rs = st.executeQuery();
                while(rs.next()) {
                    Cinema c = new Cinema(rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")));
                    list.add(c);
                }
                e.setCin(list);
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
}
