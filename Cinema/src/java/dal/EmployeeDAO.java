/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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

    public Employee getAccEmpByUserName(String username) {
        try {
            String sql = "SELECT Employee.*, Acc.Role, Acc.Password FROM Employee JOIN Acc ON Employee.Account = Acc.UserName WHERE Employee.Account = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee e = new Employee(rs.getInt("EmpID"), rs.getString("LastName"), rs.getString("FirstName"), rs.getString("Gender"), rs.getDate("Dob").toString(), rs.getString("Address"), rs.getString("CCCD"), rs.getString("Phone"), rs.getString("Email"), rs.getDate("HiredDate").toString(), rs.getString("Position"), rs.getInt("cinID"), rs.getInt("ManagerID"), rs.getString("Img"), rs.getDouble("Salary"), rs.getString("Account"), rs.getInt("Role"), rs.getString("Password"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updateIn4NoCngP(String lastName, String firstName, String gender, Date dob, String address, String cccd, String phone, String email, String username) {

        try {
            String sql = "UPDATE Employee SET LastName = ?, FirstName = ?, Gender = ?, Dob = ?, Address = ?, CCCD = ?, Phone = ?, Email = ? WHERE Account = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, lastName);
            st.setString(2, firstName);
            st.setString(3, gender);
            st.setDate(4, dob);
            st.setString(5, address);
            st.setString(6, cccd);
            st.setString(7, phone);
            st.setString(8, email);
            st.setString(9, username);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateIn4CngP(String lastName, String firstName, String gender, Date dob, String address, String cccd, String phone, String email, String username, String pass) {

        try {
            String sql = "UPDATE Employee SET LastName = ?, FirstName = ?, Gender = ?, Dob = ?, Address = ?, CCCD = ?, Phone = ?, Email = ? WHERE Account = ?";
            String sql1 = "UPDATE Acc SET Password = ? WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, lastName);
            st.setString(2, firstName);
            st.setString(3, gender);
            st.setDate(4, dob);
            st.setString(5, address);
            st.setString(6, cccd);
            st.setString(7, phone);
            st.setString(8, email);
            st.setString(9, username);
            st.executeUpdate();
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, pass);
            st1.setString(2, username);
            st1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public String checkDuplicatePhone(String phone, String username) {
        try {
            String sql = "SELECT Phone FROM Acc WHERE Phone = ? AND UserName != ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getString("Phone");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Employee> getAllEmployee() {
        List<Employee> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee JOIN Acc ON Employee.Account = Acc.UserName";
            PreparedStatement st = connection.prepareStatement(sql);
            CinemaDAO cnd = new CinemaDAO();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Employee e = new Employee(rs.getInt("EmpID"), rs.getString("LastName"), rs.getString("FirstName"), rs.getString("Gender"), rs.getDate("Dob").toString(), rs.getString("Address"), rs.getString("CCCD"), rs.getString("Phone"), rs.getString("Email"), rs.getDate("HiredDate").toString(), rs.getString("Position"), rs.getInt("cinID"), rs.getInt("ManagerID"), rs.getString("Img"), rs.getDouble("Salary"), rs.getString("Account"), rs.getInt("Role"), rs.getString("Password"), cnd.getCinemaByID(rs.getInt("cinID")).getCinName());
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Employee empD(int id) {
        try {
            String sql = "SELECT Employee.*, Acc.Role, Acc.Password FROM Employee JOIN Acc ON Employee.Account = Acc.UserName WHERE Employee.EmpID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Employee e = new Employee(rs.getInt("EmpID"), rs.getString("LastName"), rs.getString("FirstName"), rs.getString("Gender"), rs.getDate("Dob").toString(), rs.getString("Address"), rs.getString("CCCD"), rs.getString("Phone"), rs.getString("Email"), rs.getDate("HiredDate").toString(), rs.getString("Position"), rs.getInt("cinID"), rs.getInt("ManagerID"), rs.getString("Img"), rs.getDouble("Salary"), rs.getString("Account"), rs.getInt("Role"), rs.getString("Password"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
