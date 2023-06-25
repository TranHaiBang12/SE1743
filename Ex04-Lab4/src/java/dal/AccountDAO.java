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
    public Account checkA(String u, String p) {
        try {
            String sql = "SELECT * FROM UserTBL WHERE Username = ? AND Password = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u);
            st.setString(2, p);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Account a = new Account(rs.getString("Username"), rs.getString("Password"), rs.getString("DisplayName"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
