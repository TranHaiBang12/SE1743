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
import model.TransactionCode;

/**
 *
 * @author acer
 */
public class TransactionCDAO extends DBContext{
    public void insert(String orderID, String code, int type, Date dateStart, Time timeStart, Date dateEnd, Time timeEnd) {
        try {
            String sql = "INSERT INTO TransactionCode VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.setString(2, code);
            st.setInt(3, type);
            st.setDate(4, dateStart);
            st.setTime(5, timeStart);
            st.setDate(6, dateEnd);
            st.setTime(7, timeEnd);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<TransactionCode> getAllCodeByOrderID(String orderID) {
        List<TransactionCode> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TransactionCode WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                TransactionCode tc = new TransactionCode(orderID, rs.getString("Code"), rs.getInt("Type"), rs.getDate("DateStart"), rs.getTime("TimeStart"), rs.getDate("DateEnd"), rs.getTime("TimeEnd"));
                list.add(tc);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
