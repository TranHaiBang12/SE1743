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
public class CinemaDAO extends DBContext {

    public List<String> getCinemaLocation() {
        List<String> list = new ArrayList<>();
        try {
            String sql1 = "SELECT DISTINCT City FROM Cinema";
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("City"));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public String getCinTypeByName(String name) {
        try {
            String sql = "SELECT ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE cinName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getString("ctypeName");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
