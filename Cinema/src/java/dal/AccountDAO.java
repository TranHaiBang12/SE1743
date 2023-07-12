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
import model.Account;

/**
 *
 * @author acer
 */
public class AccountDAO extends DBContext {

    public Account check(String u, String p) {
        try {
            String sql = "SELECT * FROM Acc WHERE (UserName = ?) AND Password = ? AND Active = 1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u);
            st.setString(2, p);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Password"), rs.getInt("Role"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insert(String user, String gender, Date dob, String phone, String email, String city, int role, String pass) {
        String sql = "INSERT INTO Account VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String sql1 = "INSERT INTO Acc VALUES (?, ?, ?, 1)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, user);
            st1.setInt(2, role);
            st1.setString(3, pass);
            st1.executeUpdate();
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
            if (rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"), rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
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
            if (rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"), rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
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
            if (rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"), rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Account getAccountByUserName(String username) {
        try {
            String sql = "SELECT Account.* FROM Acc JOIN Account ON Acc.UserName = Account.UserName WHERE Acc.UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"), rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updAccountNoCngPass(String username, String gen, Date dob, String phone, String email, String city) {
        try {
            String sql = "UPDATE Account SET Gender = ?, Dob = ?, Phone = ?, Email = ?, City = ? WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, gen);
            st.setDate(2, dob);
            st.setString(3, phone);
            st.setString(4, email);
            st.setString(5, city);
            st.setString(6, username);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updAccountCngPass(String username, String gen, Date dob, String phone, String email, String pass, String city) {
        try {
            String sql = "UPDATE Account SET Gender = ?, Dob = ?, Phone = ?, Email = ?, City = ?, Password = ? WHERE UserName = ?";
            String sql1 = "UPDATE Acc SET Password = ?  WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, pass);
            st1.setString(2, username);
            st1.executeUpdate();
            st.setString(1, gen);
            st.setDate(2, dob);
            st.setString(3, phone);
            st.setString(4, email);
            st.setString(5, city);
            st.setString(6, pass);
            st.setString(7, username);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String checkDuplicatePhone(String phone, String username) {
        try {
            String sql = "SELECT Phone FROM Account WHERE Phone = ? AND UserName != ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, phone);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("Phone");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updRole(String u, int role) {
        try {
            String sql = "UPDATE Acc SET Role = ? WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, role);
            st.setString(2, u);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println("e");
        }
    }

    public int getNumBuy(String user) {
        int a = 0, b = 0;
        try {
            String sql = "SELECT COUNT(*) AS T FROM OrderOnline WHERE UserName = ?";
            String sql1 = "SELECT COUNT(*) AS T FROM OrderOffline WHERE OrderOffline.Account = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }

            st = connection.prepareStatement(sql1);
            st.setString(1, user);
            rs = st.executeQuery();
            if (rs.next()) {
                b = rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a + b;
    }
    
    public int getNumBuyOnl(String user) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM OrderOnline WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumBuyOff(String user) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM OrderOffline WHERE OrderOffline.Account = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<Account> getAllAcount() {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT Account.*, Acc.Active FROM Account JOIN Acc ON Account.UserName = Acc.UserName";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account(rs.getString("UserName"), rs.getString("Gender"), rs.getDate("Dob"), rs.getString("Phone"), rs.getString("Email"), rs.getString("City"), rs.getInt("Role"), rs.getString("Password"), rs.getInt("Active"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void setActive(String username, int active) {
        try {
            String sql = "UPDATE Acc SET Active = ? WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, active);
            st.setString(2, username);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<String> getCusCity() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT City FROM Account";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                list.add(rs.getString("City"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getNumCusCity(String city) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM Account WHERE City = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, city);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
