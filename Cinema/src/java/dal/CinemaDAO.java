/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.LocationCinMD;

/**
 *
 * @author acer
 */
public class CinemaDAO extends DBContext {

    public List<String> getCinemaLocation() {
        List<String> list = new ArrayList<>();
        try {
            String sql1 = "SELECT DISTINCT City FROM Cinema";
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("City"));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public String getCinemaNameByID(int cinID) {
        try {
            String sql1 = "SELECT cinName FROM Cinema WHERE cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, cinID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                return rs.getString("cinName");
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<LocationCinMD> getAllCinemaNameAndLoc() {
        List<LocationCinMD> list = new ArrayList<>();
        try {
            String sql1 = "SELECT cinID, cinName, City FROM Cinema";
            PreparedStatement st = connection.prepareStatement(sql1);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String t = rs.getString("cinName") + ", " + rs.getString("City");
                list.add(new LocationCinMD(rs.getInt("cinID"), t));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public String getCinTypeByName(String name) {
        try {
            String sql = "SELECT ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE cinName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("ctypeName");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getCinIDByName(String name) {
        try {
            String sql = "SELECT cinID FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE cinName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("cinID");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public void updCinema(int code, String name, int type, String city, String street, String address, int noroom, String fax, String hotline) {
        try {
            String sql = "UPDATE Cinema SET cinName = ?, cinType = ?, City = ?, Street = ?, Address = ?, NoRoom = ?, Fax = ?, Hotline = ? WHERE cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, type);
            st.setString(3, city);
            st.setString(4, street);
            st.setString(5, address);
            st.setInt(6, noroom);
            st.setString(7, fax);
            st.setString(8, hotline);
            st.setInt(9, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Cinema> getAllCinemaType() {
        List<Cinema> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT CinemaType.ctypeID, ctypeName FROM CinemaType";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                list.add(new Cinema(rs.getInt("ctypeID"), rs.getString("ctypeName")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Cinema> getAllCinema() {
        List<Cinema> list = new ArrayList<>();
        try {
            int i = 0;
            String sql = "SELECT Cinema.*, CinemaType.ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cinema c = new Cinema(i, rs.getInt("cinID"), rs.getString("cinName"), rs.getInt("cinType"), rs.getString("City"), rs.getString("Street"), rs.getString("Address"), rs.getInt("NoRoom"), rs.getString("Fax"), rs.getString("Hotline"), rs.getString("ManagerPhone"), rs.getString("Status"), rs.getString("ctypeName"));
                list.add(c);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Cinema> getAllCinemaByTypeALoc(int type, String loc) {
        List<Cinema> list = new ArrayList<>();
        try {
            int i = 0;
            String sql = "";
            if (type == 1) {
                sql = "SELECT Cinema.*, CinemaType.ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE (Cinema.cinType = 2 OR Cinema.cinType = 3 OR Cinema.cinType = 5) AND City = ?";
            } else if (type == 2) {
                sql = "SELECT Cinema.*, CinemaType.ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE Cinema.cinType = 1 AND City = ?";
            } else if (type == 3) {
                sql = "SELECT Cinema.*, CinemaType.ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE Cinema.cinType = 4 AND City = ?";
            } else if (type == 0) {
                sql = "SELECT Cinema.*, CinemaType.ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE City = ?";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, loc);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cinema c = new Cinema(i, rs.getInt("cinID"), rs.getString("cinName"), rs.getInt("cinType"), rs.getString("City"), rs.getString("Street"), rs.getString("Address"), rs.getInt("NoRoom"), rs.getString("Fax"), rs.getString("Hotline"), rs.getString("ManagerPhone"), rs.getString("Status"), rs.getString("ctypeName"));
                list.add(c);
                i++;
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<String> getAllCinemaLocByType(int type) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "";
            if (type == 1) {
                sql = "SELECT DISTINCT City FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE Cinema.cinType = 2 OR Cinema.cinType = 3 OR Cinema.cinType = 5";
            } else if (type == 2) {
                sql = "SELECT DISTINCT City FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE Cinema.cinType = 1";
            }
            else if (type == 3) {
                sql = "SELECT DISTINCT City FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE Cinema.cinType = 4";
            }
            else if(type == 0) {
                sql = "SELECT DISTINCT City FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("City"));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Cinema getCinemaByID(int id) {
        try {
            String sql = "SELECT Cinema.*, CinemaType.ctypeName FROM Cinema JOIN CinemaType ON Cinema.cinType = CinemaType.ctypeID WHERE cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cinema c = new Cinema(0, rs.getInt("cinID"), rs.getString("cinName"), rs.getInt("cinType"), rs.getString("City"), rs.getString("Street"), rs.getString("Address"), rs.getInt("NoRoom"), rs.getString("Fax"), rs.getString("Hotline"), rs.getString("ManagerPhone"), rs.getString("Status"), rs.getString("ctypeName"));
                return c;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void updCinemaStt(int id, String stt) {
        try {
            String sql = "UPDATE Cinema SET Status = ? WHERE cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, stt);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
