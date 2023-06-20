/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Rate;

/**
 *
 * @author acer
 */
public class RateDAO extends DBContext{
    public void insertRate(String username, int movID, String comment, int rate, String status, String displayName) {
        try {
            String sql = "INSERT INTO Rate VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, movID);
            st.setString(3, comment);
            st.setInt(4, rate);
            st.setString(5, status);
            st.setString(6, displayName);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Rate checkAccountRateByMovID(String username, int movID) {
        try {
            String sql = "SELECT * FROM Rate WHERE Username = ? AND movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Rate r = new Rate(username, movID, rs.getString("Comments"), rs.getInt("Rate"), rs.getString("Status"), rs.getString("DisplayName"));
                return r;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
