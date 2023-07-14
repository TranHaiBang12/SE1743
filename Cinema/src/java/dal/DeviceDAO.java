/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controller.DeviceDist;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import model.DeviceDistribution;
import model.DeviceError;
import model.DeviceImport;

/**
 *
 * @author acer
 */
public class DeviceDAO extends DBContext {
    
    public void dltDeViceImport(String dCode) {
        try {
            String sql = "DELETE FROM DeviceImport WHERE DeviceCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, dCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltDeviceByDCode(String dcode) {
        try {
            String sql = "DELETE FROM DeviceError WHERE DeviceCode = ?";
            String sql1 = "DELETE FROM DeviceDist WHERE DeviceCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, dcode);
            st.executeUpdate();
            st = connection.prepareStatement(sql1);
            st.setString(1, dcode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltDeViceError(String barCode, Timestamp t) {
        try {
            String sql = "DELETE FROM DeviceError WHERE DeviceBarCode = ? AND DateDetected = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, barCode);
            st.setTimestamp(2, t);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getCostBuyDeviceByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM(PriceImport) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getCostBuyDeviceByDateAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT SUM(PriceImport) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE (Date BETWEEN ? AND ?) AND cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cinID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getCostFixedByDateAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT SUM(CostIncured) AS T FROM DeviceError WHERE (DateFixed BETWEEN ? AND ?) AND cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cinID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
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
            while (rs.next()) {
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
            if (rs.next()) {
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
            while (rs.next()) {
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
            if (rs.next()) {
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

    public List<DeviceDistribution> getAllDeviceByCAR(int cinID, int roomID) {
        List<DeviceDistribution> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE cinID = ? AND roomID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            st.setInt(2, roomID);
            ResultSet rs = st.executeQuery();
            CinemaDAO cnd = new CinemaDAO();
            while (rs.next()) {
                DeviceDistribution dd = new DeviceDistribution(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("Date"), rs.getTime("Date"), rs.getString("DeviceType"), getTypeNameByID(rs.getString("DeviceType")), rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                list.add(dd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public DeviceDistribution getAllDeviceByCAR(String barCode) {
        try {
            String sql = "SELECT * FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE DeviceBarCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, barCode);
            ResultSet rs = st.executeQuery();
            CinemaDAO cnd = new CinemaDAO();
            if (rs.next()) {
                DeviceDistribution dd = new DeviceDistribution(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("Date"), rs.getTime("Date"), rs.getString("DeviceType"), getTypeNameByID(rs.getString("DeviceType")), rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                return dd;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void dltDevice(String barcode) {
        try {
            String sql = "DELETE FROM DeviceError WHERE DeviceBarCode = ?";
            String sql1 = "DELETE FROM DeviceDist WHERE DeviceBarCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, barcode);
            st.executeUpdate();
            st = connection.prepareStatement(sql1);
            st.setString(1, barcode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<DeviceError> getAllDeviceError() {
        List<DeviceError> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceError WHERE DateFixed IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            CinemaDAO cnd = new CinemaDAO();
            while (rs.next()) {
                DeviceError de = new DeviceError(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("DateDetected"), rs.getTime("DateDetected"), rs.getDate("DateFixed"), rs.getTime("DateFixed"), rs.getDouble("CostIncured"));
                list.add(de);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void updDeviceDist(String barCode, int cinID, int roomID, String oBarCode, Timestamp t) {
        try {
            String sql = "UPDATE DeviceDist SET cinID = ?, roomID = ?, Date = ?, DeviceBarCode = ? WHERE DeviceBarCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            st.setInt(2, roomID);
            st.setTimestamp(3, t);
            st.setString(4, barCode);
            st.setString(5, oBarCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public DeviceDistribution checkBarCode(String code) {
        try {
            String sql = "SELECT * FROM DeviceDist WHERE DeviceBarCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, code);
            CinemaDAO cnd = new CinemaDAO();
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                DeviceDistribution dd = new DeviceDistribution(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("Date"), rs.getTime("Date"), rs.getString("DeviceType"), getTypeNameByID(rs.getString("DeviceType")), rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                return dd;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void insertNewDeviceError(String deviceCode, int cinID, int roomID, String barCode) {
        try {
            String sql = "INSERT INTO DeviceError (DeviceCode, cinID, roomID, DateDetected, DateFixed, CostIncured, DeviceBarCode) VALUES (?, ?, ?, ?, NULL, NULL, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, deviceCode);
            st.setInt(2, cinID);
            st.setInt(3, roomID);
            st.setTimestamp(4, Timestamp.from(Instant.now()));
            System.out.println(Timestamp.from(Instant.now()));
            st.setString(5, barCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updDeviceErrorNotFixed(String barCode) {
        try {
            String sql = "UPDATE DeviceError SET DateDetected = ? WHERE DeviceBarCode = ? AND DateFixed IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, Timestamp.from(Instant.now()));
            st.setString(2, barCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updDeviceErrorFixed(String barCode, double price) {
        try {
            String sql = "UPDATE DeviceError SET DateFixed = ?, CostIncured = ? WHERE DeviceBarCode = ? AND DateFixed IS NULL";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setTimestamp(1, Timestamp.from(Instant.now()));
            st.setDouble(2, price);
            st.setString(3, barCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<String> getAllDeviceImpCode() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceImport";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                DeviceImport di = new DeviceImport(rs.getString("DeviceCode"), rs.getString("DeviceType"), "", rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                list.add(di.getDeviceCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertDeviceDist(String deviceCode, int cinID, int roomID, Timestamp t, String barCode) {
        try {
            String sql = "INSERT INTO DeviceDist VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, deviceCode);
            st.setInt(2, cinID);
            st.setInt(3, roomID);
            st.setTimestamp(4, t);
            st.setString(5, barCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<DeviceError> getADeviceErr() {
        List<DeviceError> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceError";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            CinemaDAO cnd = new CinemaDAO();
            while (rs.next()) {
                DeviceError de = new DeviceError(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("DateDetected"), rs.getTime("DateDetected"), rs.getDate("DateFixed"), rs.getTime("DateFixed"), rs.getDouble("CostIncured"), getDeviceByCode(rs.getString("DeviceCode")).getImg());
                list.add(de);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<DeviceError> getADeviceErrByDate(Date dS, Date eS) {
        List<DeviceError> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceError WHERE DateDetected BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            CinemaDAO cnd = new CinemaDAO();
            while (rs.next()) {
                DeviceError de = new DeviceError(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("DateDetected"), rs.getTime("DateDetected"), rs.getDate("DateFixed"), rs.getTime("DateFixed"), rs.getDouble("CostIncured"), getDeviceByCode(rs.getString("DeviceCode")).getImg());
                list.add(de);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getNumDeviceDistByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE Date BETWEEN ? AND ?";
            CinemaDAO cnd = new CinemaDAO();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDeviceDistByDateAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE (Date BETWEEN ? AND ?) AND cinID = ?";
            CinemaDAO cnd = new CinemaDAO();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cinID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getSumPriceDeviceDistByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM(PriceImport) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE Date BETWEEN ? AND ?";
            CinemaDAO cnd = new CinemaDAO();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getSumPriceDeviceDistByDateAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT SUM(PriceImport) AS T FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE (Date BETWEEN ? AND ?) AND cinID = ?";
            CinemaDAO cnd = new CinemaDAO();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cinID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumDeviceErrorByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM DeviceError WHERE DateDetected BETWEEN ? AND ?";
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
    
    public int getNumDeviceErrorByDateAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT COUNT(*) AS T FROM DeviceError WHERE (DateDetected BETWEEN ? AND ?) AND cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cinID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getSumPriceDeviceErrorByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM(CostIncured) AS T FROM DeviceError WHERE DateDetected BETWEEN ? AND ?";
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
    
    public int getSumPriceDeviceErrorByDate(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT SUM(CostIncured) AS T FROM DeviceError WHERE (DateFixed BETWEEN ? AND ?) AND cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cinID);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
     public List<DeviceDistribution> getAllDeviceByD(Date dS, Date eS) {
        List<DeviceDistribution> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM DeviceImport JOIN DeviceDist ON DeviceImport.DeviceCode = DeviceDist.DeviceCode WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            CinemaDAO cnd = new CinemaDAO();
            while (rs.next()) {
                DeviceDistribution dd = new DeviceDistribution(rs.getString("DeviceCode"), rs.getString("DeviceBarCode"), rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")), rs.getInt("roomID"), rs.getDate("Date"), rs.getTime("Date"), rs.getString("DeviceType"), getTypeNameByID(rs.getString("DeviceType")), rs.getDouble("PriceImport"), rs.getString("DeviceDescript"), rs.getString("Img"));
                list.add(dd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
