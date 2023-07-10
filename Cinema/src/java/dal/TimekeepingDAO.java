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
import model.Employee;
import model.Shift;
import model.Timekeeping;

/**
 *
 * @author acer
 */
public class TimekeepingDAO extends DBContext{
    public Timekeeping getTimekeepingByDateAID(int empID, Date d) {
        EmployeeDAO ed = new EmployeeDAO();
        ShiftDAO sd = new ShiftDAO();
        try {
            String sql = "SELECT * FROM Timekeeping WHERE EmpID = ? AND Date = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setDate(2, d);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Timekeeping t = new Timekeeping(rs.getInt("EmpID"), rs.getTime("startWorkTime"), rs.getTime("endWorkTime"), rs.getInt("ShiftID"), rs.getInt("onLeave"), rs.getDate("Date"), ed.getEmployeeByID(rs.getInt("EmpID")), sd.getShiftByID(rs.getInt("ShiftID")));
                return t;
            }
        } catch (Exception e) {
            System.out.println(e);           
        }
        return null;
    }
    
    public void updTimekeepingByEmpIDAD(int empID, Date d, Time startWork, Time endWork, int onleave) {
        try {
            String sql = "UPDATE Timekeeping SET startWorkTime = ?, endWorkTime = ?, onleave = ? WHERE EmpID = ? AND Date = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTime(1, startWork);
            st.setTime(2, endWork);
            st.setInt(3, onleave);
            st.setInt(4, empID);
            st.setDate(5, d);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Timekeeping> getTimekeepingByEmpAndYear(int empID, int year, int month) {
        List<Timekeeping> list = new ArrayList<>();
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT * FROM Timekeeping WHERE EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Timekeeping t = new Timekeeping(rs.getInt("EmpID"), rs.getTime("startWorkTime"), rs.getTime("endWorkTime"), rs.getInt("ShiftID"), rs.getInt("onLeave"), rs.getDate("Date"), ed.getEmployeeByID(rs.getInt("EmpID")), sd.getShiftByID(rs.getInt("ShiftID")));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public int geNumDateWorkByTime(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime IS NOT NULL AND endWorkTime IS NOT NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public double geNumHourWorkByTime(int empID, int year, int month) {
        double a = 0;
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT SUM(DATEDIFF(minute, startWorkTime, endWorkTime)) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime IS NOT NULL AND endWorkTime IS NOT NULL ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                
                a = (double)rs.getInt("T") / (double)60;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
    
    public int getNumDateOffWorkByTime(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime IS NULL AND endWorkTime IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateOffWorkByTimeHasPermission(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime IS NULL AND endWorkTime IS NULL AND onLeave = 1";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateComeRightTime(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime = startShift";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateComeLate(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime > startShift";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateComeSoon(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND startWorkTime < startShift";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateReturnRightTime(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND endWorkTime = endShift";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateOT(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND endWorkTime > endShift";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDateReturnSoon(int empID, int year, int month) {
        try {
            EmployeeDAO ed = new EmployeeDAO();
            ShiftDAO sd = new ShiftDAO();
            String sql = "SELECT COUNT(*) AS T FROM Timekeeping JOIN Shift ON Timekeeping.ShiftID = Shift.ShiftID WHERE Timekeeping.EmpID = ? AND YEAR(Date) = ? AND MONTH(Date) = ? AND endWorkTime < endShift";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, empID);
            st.setInt(2, year);
            st.setInt(3, month);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
}
