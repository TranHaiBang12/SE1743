/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
}
