/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.RoomSeat;

/**
 *
 * @author acer
 */
public class SeatDAO extends DBContext {

    public void insert(int row, String col, int roomID, int cinID, int type) {
        try {
            String sql = "INSERT INTO Seat VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, row);
            st.setString(2, col);
            st.setInt(3, roomID);
            st.setInt(4, cinID);
            st.setInt(5, type);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<RoomSeat> selectSeatByRoomIDAndCinID(int roomID, int cinID) {
        List<RoomSeat> list = new ArrayList<>();
        try {
            int i = 1;
            String sql = "SELECT * FROM Seat Room WHERE roomID = ? AND cinID = ? ORDER BY Row";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, roomID);
            st.setInt(2, cinID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RoomSeat rS = new RoomSeat(i, rs.getInt("Row"), rs.getString("Col"), rs.getInt("roomID"), rs.getInt("cinID"), rs.getInt("Type"));
                list.add(rS);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertAllSeatInRoom(String id) {
        RoomDAO rmd = new RoomDAO();
        ScheDAO scd = new ScheDAO();
        List<String> b = new ArrayList<>();
        SeatDAO sed = new SeatDAO();
        System.out.println(rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getCinID());
        int a[] = new int[rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats()];
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats(); i++) {
            a[i] = i + 1;
        }
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoColSeats(); i++) {
            b.add(c.substring(i, i + 1));

        }

        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoColSeats(); i++) {
            for (int j = 0; j < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats(); j++) {
                if (j >= 3 && j <= 5) {
                    if (i >= 1 && i <= 6) {
                        sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 2);
                    } else {
                        sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 1);
                    }
                } else if (j >= 6) {
                    sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 3);

                } else {
                    sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 1);
                }
            }
        }
    }
}
