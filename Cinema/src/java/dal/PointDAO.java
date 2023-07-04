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
import model.AccountPoint;

/**
 *
 * @author acer
 */
public class PointDAO extends DBContext {

    public AccountPoint checkAcc(String username) {
        try {
            String sql = "SELECT * FROM AccountPoint WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                AccountPoint ap = new AccountPoint(rs.getString("UserName"), rs.getInt("Point"));
                return ap;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void updPoint(String username, int point) {
        try {
            String sql = "UPDATE AccountPoint SET Point = Point + ? WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, point);
            st.setString(2, username);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public AccountPoint getAccountPoint(String username) {
        try {
            String sql = "SELECT * FROM AccountPoint WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                AccountPoint ap = new AccountPoint(username, rs.getInt("Point"));
                return ap;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertIntoAPD(String username, int point, String order, Date date, Time time, String status) {
        try {
            String sql = "INSERT INTO AccountPointDetail VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, point);
            st.setString(3, order);
            st.setDate(4, date);
            st.setTime(5, time);
            st.setString(6, status);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertIntoAUP(String username, int point, String order, Date date, Time time) {
        try {
            String sql = "INSERT INTO AccountUsedPoint VALUES(?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, point);
            st.setString(3, order);
            st.setDate(4, date);
            st.setTime(5, time);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertIntoAP(String username, int point) {
        try {
            String sql = "INSERT INTO AccountPoint VALUES(?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, point);

            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<AccountPoint> getAccountPointDetail(String username) {
        List<AccountPoint> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM AccountPointDetail WHERE UserName = ? ORDER BY ExpiredDate, ExpiredTime";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AccountPoint ap = new AccountPoint(username, rs.getInt("Point"), rs.getString("OrderID"), rs.getDate("ExpiredDate"), rs.getTime("ExpiredTime"), rs.getString("Status"));
                list.add(ap);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int getAllAccountUsedPoint(String username) {
        try {
            String sql = "SELECT SUM(Point) AS TotalPoint FROM AccountUsedPoint WHERE UserName = ? GROUP BY UserName";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalPoint");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumAccountUsedPoint(String username) {
        try {
            String sql = "SELECT COUNT(*) AS TotalPoint FROM AccountUsedPoint WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalPoint");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public void updStatusAPD(String username, String orderID, String status) {
        try {
            String sql = "UPDATE AccountPointDetail SET Status = ? WHERE UserName = ? AND OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setString(2, username);
            st.setString(3, orderID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public int getPointByOrderID(String orderID) {
        try {
            String sql = "SELECT * FROM AccountUsedPoint WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Point");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getPointAchieveByOrderID(String orderID) {
        try {
            String sql = "SELECT * FROM AccountPointDetail WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("Point");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
