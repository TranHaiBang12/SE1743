/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.Account;

/**
 *
 * @author acer
 */
public class AccountDAO extends DBContext{
    public Account check(String u, String p) {
        try {
            String sql = "SELECT * FROM Account WHERE (UserName = ? OR Email = ? OR Phone = ?) AND Password = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u);
            st.setString(2, u);
            st.setString(3, u);
            st.setString(4, p);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"),rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void insert(String user, String gender, Date dob, String phone, String email, String city, int role, String pass) {
        String sql = "INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, gender);
            st.setDate(3, dob);
            st.setString(4, phone);
            st.setString(5, email);
            st.setString(6, city);
            st.setInt(7, role);
            st.setString(8, pass);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Account checkU(String u) {
        try {
            String sql = "SELECT * FROM Account WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"),rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Account checkP(String u) {
        try {
            String sql = "SELECT * FROM Account WHERE Password = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"),rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Account checkE(String u) {
        try {
            String sql = "SELECT * FROM Account WHERE Email = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"),rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Account getAccountByUserName(String username) {
        try {
            String sql = "SELECT * FROM Account WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"), rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
