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
import model.Employee;
import model.Shift;
import model.Timekeeping;

/**
 *
 * @author acer
 */
public class TimekeepingDAO extends DBContext{
    public List<Timekeeping> getTimekeepingByEmpAndYear(int empID, int year, int month) {
        List<Timekeeping> list = new ArrayList<>();
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT * FROM Timekeeping WHERE EmpID = ? AND YEAR(Date) = ?AND MONTH(Date) = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Timekeeping t = new Timekeeping(rs.getInt("EmpID"), rs.getTime("startWorkTime"), rs.getTime("endWorkTime"), rs.getInt("ShiftID"), rs.getInt("onLeave"), rs.getDate("Date"), ed.getEmployeeByID(rs.getInt("EmpID")), sd.getShiftByID(rs.getInt("ShiftID")));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
