/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Rate;

/**
 *
 * @author acer
 */
public class RateDAO extends DBContext {

    public void insertRate(String username, int movID, String comment, int rate, String status, String displayName) {
        try {
            String sql = "INSERT INTO Rate VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, movID);
            st.setString(3, comment);
            st.setInt(4, rate);
            st.setString(5, status);
            st.setString(6, displayName);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Rate checkAccountRateByMovID(String username, int movID) {
        try {
            String sql = "SELECT * FROM Rate WHERE Username = ? AND movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, movID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Rate r = new Rate(username, movID, rs.getString("Comments"), rs.getInt("Rate"), rs.getString("Status"), rs.getString("DisplayName"));
                return r;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Rate> getAllRate(int movID) {
        List<Rate> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Rate WHERE Status = N'Được duyệt' AND Comments IS NOT NULL AND movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Rate r = new Rate(rs.getString("UserName"), rs.getInt("movID"), rs.getString("Comments"), rs.getInt("Rate"), rs.getString("Status"), rs.getString("DisplayName"));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Rate> getRateByPage(List<Rate> list, int start, int end) {
        List<Rate> list2 = new ArrayList<>();

        try {
            for (int i = start; i <= end; i++) {
                list2.add(list.get(i));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list2;
    }
    
    public int getNoRate(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM Rate WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNoRate5(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM Rate WHERE movID = ? AND Rate = 5";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNoRate4(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM Rate WHERE movID = ? AND Rate = 4";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNoRate3(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM Rate WHERE movID = ? AND Rate = 3";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNoRate2(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM Rate WHERE movID = ? AND Rate = 2";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNoRate1(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS Total FROM Rate WHERE movID = ? AND Rate = 1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getSumRate(int movID) {
        try {
            String sql = "SELECT SUM(Rate) AS Total FROM Rate WHERE movID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("Total");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
}
