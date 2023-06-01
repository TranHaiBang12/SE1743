/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author acer
 */
public class CinemaDAO extends DBContext{
    public String [] getCinemaLocation() {
        String a[];
        try {
            int i = 0;
            
            String sql = "SELECT COUNT(DISTINCT City) AS [cnt] FROM Cinema";
            String sql1 = "SELECT DISTINCT City FROM Cinema";
            PreparedStatement st = connection.prepareStatement(sql);  
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                a = new String[rs.getInt("cnt")];
            }
            else {
                a = new String[100];
            }
            st = connection.prepareStatement(sql1);  
            rs = st.executeQuery();
            while(rs.next()) {
                a[i] = rs.getString("City");
                i++;
            }
            return a;
        } catch (Exception e) {
        }
        return null;
    }
}
