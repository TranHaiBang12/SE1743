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
import model.OrderDetail;
import model.OrderTicketDetail;

/**
 *
 * @author acer
 */
public class OrderTicketDetailDAO extends DBContext{
    public void insert(String orderID, String productCode, String seatType, int seatNumber, double discount, double price) {
        try {
            String sql = "INSERT INTO TicketOnlDetail VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.setString(2, productCode);
            st.setString(3, seatType);
            st.setInt(4, seatNumber);
            st.setDouble(5, discount);
            st.setDouble(6, price);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("3");
            System.out.println(e);
        }
            
    }
    
    public List<OrderTicketDetail> getAllProductInOrderByOrderID(String orderID) {
        List<OrderTicketDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TicketOnlDetail WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OrderTicketDetail otd = new OrderTicketDetail(orderID, rs.getString("ProductCode"), rs.getString("SeatType"), rs.getInt("SeatNumber"), rs.getDouble("Discount"), rs.getDouble("Price"));
                list.add(otd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<OrderTicketDetail> getTkByOrderID(String orderID) {
        List<OrderTicketDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT TicketOnlDetail.*, TickTypeInSche.Type, TickTypeInSche.scheNo, Cinema.cinName, Schedule.roomID, Movies.movName, Form.formName, Schedule.startDate, Schedule.startTim FROM TicketOnlDetail JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN Cinema ON Schedule.cinID = Cinema.cinID JOIN Form ON Schedule.formID = Form.formID JOIN Movies ON Schedule.movID = Movies.movID WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OrderTicketDetail otd = new OrderTicketDetail(orderID, rs.getString("ProductCode"), rs.getString("SeatType"), rs.getInt("SeatNumber"), rs.getDouble("Discount"), rs.getDouble("Price"), rs.getString("Type"), rs.getString("scheNo"), rs.getString("cinName"), rs.getInt("roomID"), rs.getString("movName"), rs.getString("formName"), rs.getDate("startDate"), rs.getString("startTim"));
                list.add(otd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<OrderTicketDetail> getTkOffByOrderID(String orderID) {
        List<OrderTicketDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT TicketOffDetail.*, TickTypeInSche.Type, TickTypeInSche.scheNo, Cinema.cinName, Schedule.roomID, Movies.movName, Form.formName, Schedule.startDate, Schedule.startTim FROM TicketOffDetail JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN Cinema ON Schedule.cinID = Cinema.cinID JOIN Form ON Schedule.formID = Form.formID JOIN Movies ON Schedule.movID = Movies.movID WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OrderTicketDetail otd = new OrderTicketDetail(orderID, rs.getString("ProductCode"), rs.getString("SeatType"), rs.getInt("SeatNumber"), rs.getDouble("Discount"), rs.getDouble("Price"), rs.getString("Type"), rs.getString("scheNo"), rs.getString("cinName"), rs.getInt("roomID"), rs.getString("movName"), rs.getString("formName"), rs.getDate("startDate"), rs.getString("startTim"));
                list.add(otd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
