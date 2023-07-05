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
import model.DeviceImport;

/**
 *
 * @author acer
 */
public class DeviceDAO extends DBContext{
    public int getCostBuyDeviceByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM(PriceImport) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getCostFixedByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM(CostIncured) AS T FROM DeviceError WHERE DateFixed BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public List<DeviceImport> getAllDevice() {
        List<DeviceImport> list = new ArrayList<>();
        try {
            String sql = "SELECT DeviceImport.*, DeviceType.dtName FROM DeviceImport JOIN DeviceType ON DeviceImport.DeviceType = DeviceType.dtID";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                DeviceImport di = new DeviceImport(rs.getString("DeviceCode"), rs.getString("DeviceType"), rs.getString("dtName"), rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                list.add(di);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public DeviceImport getDeviceByCode(String code) {
        try {
            String sql = "SELECT * FROM DeviceImport WHERE DeviceCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                DeviceImport di = new DeviceImport(rs.getString("DeviceCode"), rs.getString("DeviceType"), "", rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                return di;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<DeviceImport> getAllDeviceType() {
        List<DeviceImport> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceType";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                DeviceImport di = new DeviceImport(rs.getString("dtID"), rs.getString("dtName"));
                list.add(di);
            }
        } catch (Exception e) {
            System.out.println(e);                  
        }
        return list;
    }
    
    public void upd(String code, String type, double price, String descript, String img, String ocode) {
        try {
            String sql = "UPDATE DeviceImport SET DeviceCode = ?, DeviceType = ?, PriceImport = ?, DeviceDescript = ?, Img = ? WHERE DeviceCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            st.setString(2, type);
            st.setDouble(3, price);
            st.setString(4, descript);
            st.setString(5, img);
            st.setString(6, ocode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public String getTypeNameByID(String id) {
        try {
            String sql = "SELECT * FROM DeviceType WHERE dtID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getString("dtName");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
   public void insertDevice(String code, String type, double price, String descript, String img) {
       try {
           String sql = "INSERT INTO DeviceImport VALUES (?, ?, ?, ?, ?)";
           PreparedStatement st = connection.prepareStatement(sql);
           st.setString(1, code);
           st.setString(2, type);
           st.setDouble(3, price);
           st.setString(4, descript);
           st.setString(5, img);
           st.executeUpdate();
       } catch (Exception e) {
           System.out.println(e);
       }
   }
}
