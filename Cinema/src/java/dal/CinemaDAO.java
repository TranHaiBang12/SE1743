/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acer
 */
public class CinemaDAO extends DBContext{
    public List<String> getCinemaLocation() {
        List<String> list = new ArrayList<>();
        try {
            String sql1 = "SELECT DISTINCT City FROM Cinema";
            PreparedStatement st = connection.prepareStatement(sql1);  
            ResultSet rs = st.executeQuery();
            
            while(rs.next()) {
                list.add(rs.getString("City"));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
}
