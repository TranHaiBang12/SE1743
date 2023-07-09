/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.Event;

/**
 *
 * @author acer
 */
public class EventDAO extends DBContext {

    public List<Event> getAllEventContinued() {
        List<Event> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Event WHERE Discontinued = 0";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), rs.getString("Name"), rs.getString("Content"), rs.getInt("Type"), rs.getDate("startDate"), rs.getTime("startDate"), rs.getDate("endDate"), rs.getTime("endDate"), rs.getInt("numDateCanEx"), rs.getInt("applyOther"), rs.getString("Img"), rs.getInt("Discontinued"));
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Event getAllProductInEvent(int code) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EventGift WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("ProductCode"));
                System.out.println(rs.getString("ProductCode"));

            }

        } catch (Exception e) {
            System.out.println("n");
            System.out.println(e);
        }
        if (!list.isEmpty()) {
            Event e = new Event(code, list);
            return e;
        } else {
            return null;
        }
    }

    public void insertEventOrderOnline(String orderID, int code) {
        try {
            String sql = "INSERT INTO EventOrderOnline VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.setInt(2, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Event> getAllEventType() {
        List<Event> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EventType";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt("idType"), rs.getString("TypeName")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void updEventMov(int code, int mov) {
        try {
            String sql = "UPDATE EventApplyMovie SET movID = ? WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mov);
            st.setInt(2, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltCinApplyEvent(int code) {
        try {
            String sql = "DELETE FROM CinApplyEvent WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertCinApplyEvent(int code, int cinID) {
        try {
            String sql = "INSERT INTO CinApplyEvent VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            st.setInt(2, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertEventOrder(int code, double price, String type) {
        try {
            String sql = "INSERT INTO EventApplyOrder VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.setDouble(2, price);
            st.setString(3, type);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertEventMovie(int code, int movID) {
        try {
            String sql = "INSERT INTO EventApplyMovie VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.setInt(2, movID);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltEventMovie(int code) {
        try {
            String sql = "DELETE FROM EventApplyMovie WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, code);
            st.executeQuery();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltEventOrder(int code) {
        try {
            String sql = "DELETE FROM EventApplyOrder WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, code);
            st.executeQuery();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updEventOrder(int code, double price, String type) {
        try {
            String sql = "UPDATE EventApplyOrder SET Price = ?, Type = ? WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, price);
            st.setString(2, type);
            st.setInt(3, code);
            st.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Event getEventOrder(int code) {
        try {
            String sql = "SELECT * FROM EventApplyOrder WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Event e = new Event(code, rs.getDouble("Price"), rs.getString("Type"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Event getEventMov(int code) {
        try {
            String sql = "SELECT * FROM EventApplyMovie WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Event e = new Event(code, rs.getInt("movID"));
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Event> getAllOrderEventOff(String orderID) {
        List<Event> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EventOrderOffline WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), orderID);
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Event> getAllOrderEvent(String orderID) {
        List<Event> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM EventOrderOnline WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), orderID);
                list.add(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertEventOrderOffline(String orderID, int code) {
        try {
            String sql = "INSERT INTO EventOrderOffline VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.setInt(2, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Event getEventDiscountO(int code) {
        try {
            String sql = "SELECT EventDiscount.*, EventApplyOrder.Type FROM EventDiscount JOIN EventApplyOrder ON EventApplyOrder.EventCode = EventDiscount.EventCode WHERE EventDiscount.EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), rs.getDouble("Discount"), rs.getString("Type"));
                return e;
            }
        } catch (Exception e) {
            System.out.println("p");
            System.out.println(e);
        }
        return null;
    }

    public Event getEventDiscountM(int code) {
        try {
            String sql = "SELECT EventDiscount.* FROM EventDiscount JOIN EventApplyMovie ON EventApplyMovie.EventCode = EventDiscount.EventCode WHERE EventDiscount.EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), rs.getDouble("Discount"));
                return e;
            }
        } catch (Exception e) {
            System.out.println("c");
            System.out.println(e);
        }
        return null;
    }

    public int[] checkEventOrder(double price, int code, String type) {
        int b[] = new int[0];
        int i = 0;
        int a = 0;
        try {
            String sql = "SELECT * FROM EventApplyOrder WHERE Price <= ? AND EventApplyOrder.EventCode = ? AND EventApplyOrder.Type = ?";
            String sql1 = "SELECT COUNT(*) AS T FROM EventApplyOrder WHERE Price <= ? AND EventApplyOrder.EventCode = ? AND EventApplyOrder.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setDouble(1, price);
            st.setInt(2, code);
            st.setString(3, type);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }
            st = connection.prepareStatement(sql);
            st.setDouble(1, price);
            st.setInt(2, code);
            st.setString(3, type);
            rs = st.executeQuery();

            b = new int[a];
            while (rs.next()) {
                b[i] = rs.getInt("EventCode");
                if (getEventByCode(code).getApplyO() == 0) {
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("k");
            System.out.println(e);
        }
        return b;
    }

    public int[] checkEventMov(int movID, int code) {
        int b[] = new int[0];
        int i = 0;
        int a = 0;
        try {
            String sql = "SELECT * FROM EventApplyMovie WHERE movID = ? AND EventApplyMovie.EventCode = ?";
            String sql1 = "SELECT COUNT(*) AS T FROM EventApplyMovie WHERE movID = ? AND EventApplyMovie.EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, movID);
            st.setInt(2, code);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }
            st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setInt(2, code);
            rs = st.executeQuery();

            b = new int[a];
            while (rs.next()) {
                b[i] = rs.getInt("EventCode");
                if (getEventByCode(code).getApplyO() == 0) {
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            System.out.println("3");
            System.out.println(e);
        }
        return b;
    }

    public int[] getEventCodeByCin(int cinID) {
        int a = 0;
        int i = 0;
        int b[] = new int[0];
        try {
            String sql1 = "SELECT COUNT(*) AS T FROM CinApplyEvent JOIN Event ON CinApplyEvent.EventCode = Event.EventCode WHERE cinID = ? AND Discontinued = 0";
            String sql = "SELECT CinApplyEvent.EventCode FROM CinApplyEvent JOIN Event ON CinApplyEvent.EventCode = Event.EventCode WHERE cinID = ? AND Discontinued = 0";
            PreparedStatement st = connection.prepareStatement(sql1);
            st.setInt(1, cinID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }
            b = new int[a];
            st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            rs = st.executeQuery();

            while (rs.next()) {
                b[i] = rs.getInt("EventCode");
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return b;
    }
    
    public void insertEvent(int code, String name, String content, int type, Timestamp start, Timestamp end, int appO, String img, int discontinued, int date) {
        try {
            String sql = "INSERT INTO Event VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.setString(2, name);
            st.setString(3, content);
            st.setInt(4, type);
            st.setTimestamp(5, start);
            st.setTimestamp(6, end);
            st.setInt(7, date);
            st.setInt(8, appO);
            st.setString(9, img);
            st.setInt(10, discontinued);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updEventByCode(int code, String content, int type, Timestamp start, Timestamp end, int appO, String img, int discontinued, int date) {
        try {
            System.out.println("code" + code);
            String sql = "";
            sql = "UPDATE Event Set Content = ?, Type = ?, startDate = ?, endDate = ?, numDateCanEx = ?, applyOther = ?, Img = ?, Discontinued = ? WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, content);
            st.setInt(2, type);
            st.setTimestamp(3, start);
            st.setTimestamp(4, end);
            st.setInt(5, date);
            st.setInt(6, appO);
            st.setString(7, img);
            st.setInt(8, discontinued);
            st.setInt(9, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updEventDiscount(double discount, int code) {
        try {
            String sql = "UPDATE EventDiscount SET Discount = ? WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, discount);
            st.setInt(2, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertEventDiscount(double discount, int code) {
        try {
            String sql = "INSERT INTO EventDiscount VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.setDouble(2, discount);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltEventDiscount(int code) {
        try {
            String sql = "DELETE FROM EventDiscount WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void dltEventGift(int code) {
        try {
            String sql = "DELETE FROM EventGift WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updEventGift(int code, String prCode) {
        try {
            String sql = "UPDATE EventGift SET ProductCode = ? WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, prCode);
            st.setInt(2, code);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void insertEventGift(int code, String prCode) {
        try {
            String sql = "INSERT INTO EventGift VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            st.setString(2, prCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Event getEventByCode(int code) {
        try {
            String sql = "SELECT * FROM Event WHERE EventCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, code);
            CinemaDAO cnd = new CinemaDAO();
            List<Cinema> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Event e = new Event(rs.getInt("EventCode"), rs.getString("Name"), rs.getString("Content"), rs.getInt("Type"), rs.getDate("startDate"), rs.getTime("startDate"), rs.getDate("endDate"), rs.getTime("endDate"), rs.getInt("numDateCanEx"), rs.getInt("applyOther"), rs.getString("Img"), rs.getInt("Discontinued"));
                sql = "SELECT * FROM CinApplyEvent WHERE EventCode = ?";
                st = connection.prepareStatement(sql);
                st.setInt(1, code);
                rs = st.executeQuery();
                while (rs.next()) {
                    Cinema c = new Cinema(rs.getInt("cinID"), cnd.getCinemaNameByID(rs.getInt("cinID")));
                    list.add(c);
                }
                e.setCin(list);
                return e;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
