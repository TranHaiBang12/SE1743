/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Shift;

/**
 *
 * @author acer
 */
public class ShiftDAO extends DBContext{
    public Shift getShiftByID(int id) {
        try {
            String sql = "SELECT * FROM Shift WHERE ShiftID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Shift s = new Shift(rs.getInt("ShiftID"), rs.getInt("EmpID"), rs.getTime("startShift"), rs.getTime("endShift"), rs.getDate("startDate"), rs.getDate("endDate"));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
