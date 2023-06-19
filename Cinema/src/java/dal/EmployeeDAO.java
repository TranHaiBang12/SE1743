/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Employee;

/**
 *
 * @author acer
 */
public class EmployeeDAO extends DBContext {

    public Employee getEmployeeByID(int ID) {
        try {
            String sql = "SELECT * FROM Employee WHERE EmpID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee e = new Employee(rs.getInt("EmpID"), rs.getString("LastName"), rs.getString("FirstName"), rs.getString("Gender"), rs.getDate("Dob").toString(), rs.getString("Address"), rs.getString("CCCD"), rs.getString("Phone"), rs.getString("Email"), rs.getDate("HiredDate").toString(), rs.getString("Position"), rs.getInt("cinID"), rs.getInt("ManagerID"), rs.getString("Img"), rs.getDouble("Salary"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
