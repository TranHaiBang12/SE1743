/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
