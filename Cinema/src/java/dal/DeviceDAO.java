/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
