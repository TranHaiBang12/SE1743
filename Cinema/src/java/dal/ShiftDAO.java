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
import model.Shift;

/**
 *
 * @author acer
 */
public class ShiftDAO extends DBContext{
    public Shift getShiftByID(int id) {
        try {
            String sql = "SELECT * FROM Shift WHERE ShiftID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Shift s = new Shift(rs.getInt("ShiftID"), rs.getInt("EmpID"), rs.getTime("startShift"), rs.getTime("endShift"), rs.getDate("startDate"), rs.getDate("endDate"));
                return s;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Shift> getAllShiftByEmpAMAY(int empID, int month, int year) {
        List<Shift> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Shift WHERE EmpID = ? AND (YEAR(startDate) = ? AND MONTH(startDate) = ?) OR (YEAR(endDate) = ? AND MONTH(endDate) = ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            st.setInt(4, year);
            st.setInt(5, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Shift s = new Shift(rs.getInt("ShiftID"), rs.getInt("EmpID"), rs.getTime("startShift"), rs.getTime("endShift"), rs.getDate("startDate"), rs.getDate("endDate"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public boolean checkShiftID(int code) {
        try {
            String sql = "SELECT * FROM Shift WHERE ShiftID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean checkShiftByDate(Date start, Date end) {
        try {
            String sql = "SELECT * FROM Shift WHERE (startDate BETWEEN ? AND ?) OR (endDate BETWEEN ? AND ?) OR (startDate < ? AND endDate > ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, start);
            st.setDate(2, end);
            st.setDate(3, start);
            st.setDate(4, end);
            st.setDate(5, start);
            st.setDate(6, end);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean checkShiftUpdByDate(Date start, Date end, int shiftID) {
        try {
            String sql = "SELECT * FROM Shift WHERE ShiftID != ? AND ((startDate BETWEEN ? AND ?) OR (endDate BETWEEN ? AND ?) OR (startDate < ? AND endDate > ?))";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shiftID);
            st.setDate(2, start);
            st.setDate(3, end);
            st.setDate(4, start);
            st.setDate(5, end);
            st.setDate(6, start);
            st.setDate(7, end);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    public void updShift(int shiftID, int empID, Time startShift, Time endShift, Date start, Date end) {
        try {
            String sql = "UPDATE Shift SET startShift = ?, endShift = ?, startDate = ?, endDate = ? WHERE ShiftID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTime(1, startShift);
            st.setTime(2, endShift);
            st.setDate(3, start);
            st.setDate(4, end);
            st.setInt(5, shiftID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertShift(int shiftID, int empID, Time startShift, Time endShift, Date start, Date end) {
        try {
            String sql = "INSERT INTO Shift VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, shiftID);
            st.setInt(2, empID);
            st.setTime(3, startShift);
            st.setTime(4, endShift);
            st.setDate(5, start);
            st.setDate(6, end);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
