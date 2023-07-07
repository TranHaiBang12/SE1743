/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Room;

/**
 *
 * @author acer
 */
public class RoomDAO extends DBContext{
    public Room getRoomByRoomIDAndCinID(int roomID, int cinID) {
        try {
            String sql = "SELECT * FROM Room WHERE roomID = ? AND cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roomID);
            st.setInt(2, cinID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Room r = new Room(rs.getInt("roomID"), rs.getInt("cinID"), rs.getInt("roomType"), rs.getInt("Floor"), rs.getInt("NoRowSeats"), rs.getInt("NoColumnSeats"), rs.getString("Status"));
                return r;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Room> getAllRoomByCinID(int cinID) {
        List<Room> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Room WHERE cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Room r = new Room(rs.getInt("roomID"), rs.getInt("cinID"), rs.getInt("roomType"), rs.getInt("Floor"), rs.getInt("NoRowSeats"), rs.getInt("NoColumnSeats"), rs.getString("Status"));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Room getRoomByTCode(String code) {
        try {
            String sql = "SELECT Room.* FROM TickTypeInSche JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN Room ON Schedule.roomID = Room.roomID AND Schedule.cinID = Room.cinID WHERE ProductCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Room r = new Room(rs.getInt("roomID"), rs.getInt("cinID"), rs.getInt("roomType"), rs.getInt("Floor"), rs.getInt("NoRowSeats"), rs.getInt("NoColumnSeats"), rs.getString("Status"));
                return r;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
}
