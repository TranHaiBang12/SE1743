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
import model.Order;

/**
 *
 * @author acer
 */
public class OrderDAO extends DBContext{
    public void insert(String userName, String firstName, String lastName, String phone, String email, String country, String street, String district, String city, String paymentType, Date paymentDate, Time paymentTime) {
        try {
            String sql = "INSERT INTO OrderOnline VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, phone);
            st.setString(3, email);
            st.setString(4, street);
            st.setString(5, district);
            st.setString(6, city);
            st.setString(7, paymentType);
            st.setDate(8, paymentDate);
            st.setString(9, firstName);
            st.setString(10, lastName);
            st.setString(11, country);
            st.setTime(12, paymentTime);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Order> getAllOrderByUserName(String userName) {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderOnline WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
               
                Order o = new Order(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                list.add(o);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
