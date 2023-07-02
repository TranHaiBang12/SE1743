/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.TIcketDate;
import model.Ticket;

/**
 *
 * @author acer
 */
public class TicketDAO extends DBContext {

    public List<Ticket> getTicketBySchedule(String sche) {
        List<Ticket> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TickTypeInSche WHERE scheNo = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sche);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(rs.getString("ProductCode"), rs.getString("Type"), rs.getString("scheNo"), rs.getInt("NumberLeft"), rs.getString("Status"));
                list.add(t);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Ticket getTicketBySchedule(String sche, String type) {
        try {
            String sql = "SELECT * FROM TickTypeInSche WHERE scheNo = ? AND Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sche);
            st.setString(2, type);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(rs.getString("ProductCode"), rs.getString("Type"), rs.getString("scheNo"), rs.getInt("NumberLeft"), rs.getString("Status"));
                return t;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Ticket> getTicketPByScheduleRCS(String sche) {
        List<Ticket> list = new ArrayList<>();
        int i = 1;
        try {
            String sql = "SELECT Schedule.scheNo, TickTypeInSche.ProductCode, TickTypeInSche.NumberLeft, TickTypeInSche.Status, TickTypeInSche.Discontinued, Product.Price, Product.Discout, Seat.Row , Seat.Col, Seat.Type FROM Schedule JOIN TickTypeInSche ON Schedule.scheNo = TickTypeInSche.scheNo JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode JOIN TicketType ON TicketType.ttID = TickTypeInSche.Type JOIN Seat ON Schedule.roomID = Seat.roomID AND Schedule.cinID = Seat.cinID AND TicketType.SeatType = Seat.Type WHERE Schedule.scheNo = ? ORDER BY Row, Col";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sche);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(i, rs.getString("ProductCode"), rs.getInt("Type"), rs.getString("scheNo"), rs.getInt("NumberLeft"), rs.getString("Status"), rs.getInt("Row"), rs.getString("Col"), rs.getDouble("Price"), rs.getDouble("Discout"), rs.getInt("Discontinued"));
                System.out.println(t.getCol());
                list.add(t);
                i++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Ticket getTicketPByProductCodeRC(String productCode, int row, String col) {
        int i = 1;
        MovieDAO mvd = new MovieDAO();
        try {
            String sql = "SELECT Schedule.scheNo, Schedule.cinID, Schedule.roomID, Schedule.movID, TickTypeInSche.ProductCode, TickTypeInSche.NumberLeft, TickTypeInSche.Status, TickTypeInSche.Discontinued, Product.Price, Product.Discout, Seat.Row , Seat.Col, Seat.Type FROM Schedule JOIN TickTypeInSche ON Schedule.scheNo = TickTypeInSche.scheNo JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode JOIN TicketType ON TicketType.ttID = TickTypeInSche.Type JOIN Seat ON Schedule.roomID = Seat.roomID AND Schedule.cinID = Seat.cinID AND TicketType.SeatType = Seat.Type WHERE TickTypeInSche.ProductCode = ? AND Row = ? AND Col = ? ORDER BY Row";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productCode);
            st.setInt(2, row);
            st.setString(3, col);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(i, rs.getInt("movID"), mvd.getMovieById(rs.getInt("movID")), rs.getString("ProductCode"), rs.getInt("Type"), rs.getString("scheNo"), rs.getInt("NumberLeft"), rs.getString("Status"), rs.getInt("Row"), rs.getString("Col"), rs.getDouble("Price"), rs.getDouble("Discout"), rs.getInt("Discontinued"), rs.getInt("cinID"));
                t.setRoomID(rs.getInt("roomID"));
                return t;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Ticket> getAllTicketBoughtBySchedule(String scheNo) {
        List<Ticket> list = new ArrayList<>();
        try {
            String sql = "SELECT TicketOnlDetail.ProductCode, TicketOnlDetail.SeatNumber, TicketOnlDetail.SeatType FROM TickTypeInSche RIGHT JOIN TicketOnlDetail ON TickTypeInSche.ProductCode = TicketOnlDetail.ProductCode WHERE scheNo = ?\n"
                    + "UNION\n"
                    + "SELECT TicketOffDetail.ProductCode, TicketOffDetail.SeatNumber, TicketOffDetail.SeatType FROM TickTypeInSche RIGHT JOIN TicketOffDetail ON TickTypeInSche.ProductCode = TicketOffDetail.ProductCode WHERE scheNo = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, scheNo);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Ticket(0, rs.getString("ProductCode"), 0, scheNo, 0, sql, rs.getInt("SeatNumber"), rs.getString("SeatType"), 0, 0, 0));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public int insertProductTicket(double discount, double price) {
        int id = 0;
        try {
            String sql = "INSERT INTO Product VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, "TK");
            st.setDouble(2, discount);
            st.setDouble(3, price);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public void insertTicketNM(String productCode, String scheNo, int numberLeft) {
        try {
            String sql = "INSERT INTO TickTypeInSche VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productCode);
            st.setString(2, "NM");
            st.setString(3, scheNo);
            st.setInt(4, numberLeft);
            st.setString(5, "ĐANG BÁN");
            st.setInt(6, 0);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertTicketVP(String productCode, String scheNo, int numberLeft) {
        try {
            String sql = "INSERT INTO TickTypeInSche VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productCode);
            st.setString(2, "VP");
            st.setString(3, scheNo);
            st.setInt(4, numberLeft);
            st.setString(5, "ĐANG BÁN");
            st.setInt(6, 0);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertTicketVT(String productCode, String scheNo, int numberLeft) {
        try {
            String sql = "INSERT INTO TickTypeInSche VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productCode);
            st.setString(2, "VT");
            st.setString(3, scheNo);
            st.setInt(4, numberLeft);
            st.setString(5, "ĐANG BÁN");
            st.setInt(6, 0);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double getTicketNMPriceBySche(String scheNo) {
        try {
            String sql = "SELECT TickTypeInSche.*, Product.Price, Product.Discout FROM TickTypeInSche JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode WHERE scheNo = ? AND TickTypeInSche.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, "NM");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble("Price");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getTicketVPPriceBySche(String scheNo) {
        try {
            String sql = "SELECT TickTypeInSche.*, Product.Price, Product.Discout FROM TickTypeInSche JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode WHERE scheNo = ? AND TickTypeInSche.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, "VP");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble("Price");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getTicketVTPriceBySche(String scheNo) {
        try {
            String sql = "SELECT TickTypeInSche.*, Product.Price, Product.Discout FROM TickTypeInSche JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode WHERE scheNo = ? AND TickTypeInSche.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, "VT");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble("Price");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getTicketNMDiscountBySche(String scheNo) {
        try {
            String sql = "SELECT TickTypeInSche.*, Product.Price, Product.Discout FROM TickTypeInSche JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode WHERE scheNo = ? AND TickTypeInSche.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, "NM");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble("Discout");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getTicketVPDiscountBySche(String scheNo) {
        try {
            String sql = "SELECT TickTypeInSche.*, Product.Price, Product.Discout FROM TickTypeInSche JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode WHERE scheNo = ? AND TickTypeInSche.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, "VP");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble("Discout");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public double getTicketVTDiscountBySche(String scheNo) {
        try {
            String sql = "SELECT TickTypeInSche.*, Product.Price, Product.Discout FROM TickTypeInSche JOIN Product ON TickTypeInSche.ProductCode = Product.ProductCode WHERE scheNo = ? AND TickTypeInSche.Type = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, scheNo);
            st.setString(2, "VT");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble("Discout");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void updTicketPriceBySche(String productCode, double price, double discout) {
        try {
            String sql = "UPDATE Product SET Price = ?, Discout = ? WHERE ProductCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, price);
            st.setDouble(2, discout);
            st.setString(3, productCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Ticket> getAllTicketBought() {
        List<Ticket> ticket = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TicketOnlDetail\n"
                    + "UNION\n"
                    + "SELECT * FROM TicketOFFDetail";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(rs.getString("ProductCode"), rs.getInt("SeatNumber"), rs.getString("SeatType"));
                ticket.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return ticket;
    }

    public void deleteTicketByID(String productCode) {
        try {
            String sql = "DELETE FROM Product WHERE ProductCode = ?";
            String sql1 = "DELETE FROM TickTypeInSche WHERE ProductCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st.setString(1, productCode);
            st1.setString(1, productCode);
            st1.executeUpdate();
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getNumTicketSellByTime(Date dS, Date eS, int movID) {
        try {
            String sql = "SELECT COUNT(*) AS [T] FROM\n"
                    + "(SELECT TicketOnlDetail.*, TickTypeInSche.scheNo, Schedule.movID FROM TicketOnlDetail JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE (OrderOnline.PaymentDate BETWEEN ? AND ?) AND Schedule.movID = ?\n"
                    + "UNION\n"
                    + "SELECT TicketOffDetail.*, TickTypeInSche.scheNo, Schedule.movID FROM TicketOffDetail JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE (OrderOffline.Date BETWEEN ? AND ?) AND Schedule.movID = ?\n"
                    + ") AS [T]";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, movID);
            st.setDate(4, dS);
            st.setDate(5, eS);
            st.setInt(6, movID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<String> getAllTickTypeByTime(Date dS, Date eS, int movID) {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Type FROM TickTypeInSche JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo WHERE Schedule.movID = ? AND (startDate BETWEEN ? AND ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setDate(2, dS);
            st.setDate(3, eS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Type"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getNumTickTypeSellByTime(Date dS, Date eS, int movID, String type) {
        try {
            String sql = "SELECT COUNT(*) AS [T] FROM\n"
                    + "(SELECT TicketOnlDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOnline.PaymentDate FROM TicketOnlDetail JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE (OrderOnline.PaymentDate BETWEEN ? AND ?) AND movID = ? AND TickTypeInSche.Type = ?\n"
                    + "UNION\n"
                    + "SELECT TicketOffDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOffline.Date FROM TicketOffDetail JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE (OrderOffline.Date BETWEEN ? AND ?) AND movID = ? AND TickTypeInSche.Type = ?\n"
                    + ") AS [T]";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, movID);
            st.setString(4, type);
            st.setDate(5, dS);
            st.setDate(6, eS);
            st.setInt(7, movID);
            st.setString(8, type);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumTickFormByTime(Date dS, Date eS, int movID, int formID) {
        try {
            String sql = "SELECT COUNT(*) AS [T] FROM\n"
                    + "(SELECT TicketOnlDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOnline.PaymentDate FROM TicketOnlDetail JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE (OrderOnline.PaymentDate BETWEEN ? AND ?) AND movID = ? AND Schedule.formID = ?\n"
                    + "UNION\n"
                    + "SELECT TicketOffDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOffline.Date FROM TicketOffDetail JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE (OrderOffline.Date BETWEEN ? AND ?) AND movID = ? AND Schedule.formID = ?\n"
                    + ") AS [T]";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, movID);
            st.setInt(4, formID);
            st.setDate(5, dS);
            st.setDate(6, eS);
            st.setInt(7, movID);
            st.setInt(8, formID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumTickByDate(Date dS, Date eS, int movID, Date nS) {
        try {
            String sql = "SELECT COUNT(*) AS [T] FROM\n"
                    + "(SELECT TicketOnlDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOnline.PaymentDate FROM TicketOnlDetail JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE (OrderOnline.PaymentDate BETWEEN ? AND ?) AND movID = ? AND PaymentDate = ?\n"
                    + "UNION\n"
                    + "SELECT TicketOffDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOffline.Date FROM TicketOffDetail JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE (OrderOffline.Date BETWEEN ? AND ?) AND movID = ? AND Date = ?\n"
                    + ") AS [T]";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, movID);
            st.setDate(4, nS);
            st.setDate(5, dS);
            st.setDate(6, eS);
            st.setInt(7, movID);
            st.setDate(8, nS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<TIcketDate> getAllTicketBoughtDateByTime(Date dS, Date eS, int movID) {
        List<TIcketDate> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT PaymentDate FROM TickTypeInSche JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN TicketOnlDetail ON TickTypeInSche.ProductCode = TicketOnlDetail.ProductCode JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE Schedule.movID = ?  AND (PaymentDate BETWEEN ? AND ?)\n"
                    + "UNION\n"
                    + "SELECT DISTINCT Date FROM TickTypeInSche JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN TicketOffDetail ON TickTypeInSche.ProductCode = TicketOffDetail.ProductCode JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE Schedule.movID = ?  AND (Date BETWEEN ? AND ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setDate(2, dS);
            st.setDate(3, eS);
            st.setInt(4, movID);
            st.setDate(5, dS);
            st.setDate(6, eS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String t = rs.getDate("PaymentDate").toString();
                String year = "", month = "", date = "";
                int cnt = 0;
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        year = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        month = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                date = t.substring(cnt + 1);
                TIcketDate td = new TIcketDate(date + "-" + month + "-" + year);
                list.add(td);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getNumTickByMovID(int movID) {
        try {
            String sql = "SELECT COUNT(*) AS [T] FROM\n"
                    + "(SELECT TicketOnlDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOnline.PaymentDate FROM TicketOnlDetail JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE movID = ? \n"
                    + "UNION\n"
                    + "SELECT TicketOffDetail.*, TickTypeInSche.scheNo, Schedule.movID, OrderOffline.Date FROM TicketOffDetail JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE  movID = ? \n"
                    + ") AS [T]";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movID);
            st.setInt(2, movID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public int getNumTickSellByDate(Date dS, Date eS) {
        try {
            String sql = "SELECT\n"
                    + "(SELECT COUNT(*) FROM TicketOnlDetail JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID WHERE PaymentDate BETWEEN ? AND ?) \n"
                    + "+\n"
                    + "(SELECT COUNT(*) FROM TicketOffDetail JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID WHERE Date BETWEEN ? AND ?) AS T";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setDate(3, dS);
            st.setDate(4, eS);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getNumTickSellOfAllTime() {
        try {
            String sql = "SELECT\n"
                    + "(SELECT COUNT(*) FROM TicketOnlDetail JOIN OrderOnline ON TicketOnlDetail.OrderID = OrderOnline.OrderID) \n"
                    + "+\n"
                    + "(SELECT COUNT(*) FROM TicketOffDetail JOIN OrderOffline ON TicketOffDetail.OrderID = OrderOffline.OrderID) AS T";
            PreparedStatement st = connection.prepareStatement(sql);
 
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
