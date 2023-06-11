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
import model.Ticket;

/**
 *
 * @author acer
 */
public class TicketDAO extends DBContext {

    public List<Ticket> getTicketBySchedule(String sche) {
        List<Ticket> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TickTypeInSche WHERE scheNo = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sche);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(rs.getString("ProductCode"), rs.getString("Type"), rs.getString("scheNo"), rs.getInt("NumberLeft"), rs.getString("Status"));
                list.add(t);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
     public List<Ticket> getTicketPByScheduleRCS (String sche) {
        List<Ticket> list = new ArrayList<>();
        int i = 1;
        try {
            String sql = "SELECT Schedule.scheNo, TickTypeInSche.ProductCode, TickTypeInSche.NumberLeft, TickTypeInSche.Status, TickTypeInSche.Discontinued, Product.Price, Product.Discout, Seat.Row , Seat.Col, Seat.Type FROM Schedule JOIN TickTypeInSche ON Schedule.scheNo = TickTypeInSche.scheNo JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode JOIN TicketType ON TicketType.ttID = TickTypeInSche.Type JOIN Seat ON Schedule.roomID = Seat.roomID AND Schedule.cinID = Seat.cinID AND TicketType.SeatType = Seat.Type WHERE Schedule.scheNo = ? ORDER BY Row";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sche);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(i, rs.getString("ProductCode"), rs.getInt("Type"), rs.getString("scheNo"), rs.getInt("NumberLeft"), rs.getString("Status"), rs.getInt("Row"), rs.getString("Col"), rs.getDouble("Price"), rs.getDouble("Discout"), rs.getInt("Discontinued"));
                System.out.println(t.getCol());
                list.add(t);
                i++;
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    
    
}
