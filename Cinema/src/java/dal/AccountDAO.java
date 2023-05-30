/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
                Account a = new Account(rs.getString("UserName"), rs.getString("LastName"), rs.getString("FirstName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("CCCD"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;               
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
