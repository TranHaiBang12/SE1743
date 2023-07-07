/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import model.OrderOnl;
import model.OrderOnlByDate;
import model.OrderOnlByDate;
import model.OrderOff;
import model.OrderOffByDate;
import model.Ticket;

/**
 *
 * @author acer
 */
public class OrderDAO extends DBContext {

    public int insert(String userName, String firstName, String lastName, String phone, String email, String country, String street, String district, String city, String paymentType, Date paymentDate, Time paymentTime) {
        int id = 0;
        try {
            String sql = "INSERT INTO OrderOnline VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, userName);
            st.setString(2, phone);
            st.setString(3, email);
            st.setString(4, street);
            st.setString(5, district);
            st.setString(6, city);
            st.setString(7, paymentType);
            st.setDate(8, paymentDate);
            st.setString(9, firstName);
            st.setString(10, lastName);
            st.setString(11, country);
            st.setTime(12, paymentTime);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            System.out.println(rs.getString("OrderID"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public List<OrderOnl> getAllOrderOnlByDate(Date dS, Date eS) {
        List<OrderOnl> list = new ArrayList<>();
        try {
            String sql = "";
            sql = "SELECT * FROM OrderOnline WHERE PaymentDate BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                list.add(o);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<OrderOff> getAllOrderOffByDate(Date dS, Date eS) {
        List<OrderOff> list = new ArrayList<>();
        try {
            String sql = "";
            sql = "SELECT * FROM OrderOffline WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"));

                list.add(o);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<OrderOnl> getAllOrderOnlByUserName(String userName) {
        List<OrderOnl> list = new ArrayList<>();
        try {
            String sql = "";
            if (userName != null) {
                sql = "SELECT * FROM OrderOnline WHERE UserName = ?";
            } else {
                sql = "SELECT * FROM OrderOnline";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (userName != null) {
                st.setString(1, userName);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                list.add(o);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public OrderOnlByDate getAllOrderFoodOnlByIDAD(Date d) {
        List<OrderOnl> listO = new ArrayList<>();
        try {
            String sql = "";
            if (d != null) {
                sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                        + "                        (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                        (SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID  WHERE PaymentDate = ?";
            } else {
                sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                        + "                        (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                        (SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (d != null) {
                st.setDate(1, d);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"), rs.getDouble("TotalAmount"));

                listO.add(o);
            }
            String date = "", month = "", year = "";
            int k = 0;
            for (int i = 0; i < d.toString().length(); i++) {
                if (d.toString().charAt(i) == '-') {
                    if (k == 0) {
                        year = d.toString().substring(0, i);
                        k = i;
                    }
                    if (!year.equals("") && k < i) {
                        month = d.toString().substring(i - 2, i);
                        k = i;
                    }
                }
                if (i == d.toString().length() - 1) {
                    date = d.toString().substring(i - 1);
                    break;
                }

            }
            OrderOnlByDate obd = new OrderOnlByDate(date + "-" + month + "-" + year, listO);
            return obd;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public OrderOffByDate getAllOrderFoodOffByIDAD(Date d) {
        List<OrderOff> listO = new ArrayList<>();
        try {
            String sql = "";
            if (d != null) {
                sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n"
                        + "                        (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                        (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID  WHERE Date = ?";

            } else {
                sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n"
                        + "                        (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                        (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (d != null) {
                st.setDate(1, d);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"));

                listO.add(o);
            }
            String date = "", month = "", year = "";
            int k = 0;
            for (int i = 0; i < d.toString().length(); i++) {
                if (d.toString().charAt(i) == '-') {
                    if (k == 0) {
                        year = d.toString().substring(0, i);
                        k = i;
                    }
                    if (!year.equals("") && k < i) {
                        month = d.toString().substring(i - 2, i);
                        k = i;
                    }
                }
                if (i == d.toString().length() - 1) {
                    date = d.toString().substring(i - 1);
                    break;
                }

            }
            OrderOffByDate obd = new OrderOffByDate(date + "-" + month + "-" + year, listO);
            return obd;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public OrderOnlByDate getAllOrderTicketOnlByIDAD(int movID, Date d) {
        List<OrderOnl> listO = new ArrayList<>();
        try {
            String sql = "";
            if (movID != 0) {
                sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                        + "(SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "(SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID\n"
                        + "UNION\n"
                        + "SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID WHERE P.OrderID IN(SELECT OrderOnline.OrderID FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo WHERE movID = ? AND PaymentDate = ?)";
            } else {
                sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                        + "(SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "(SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID\n"
                        + "UNION\n"
                        + "SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID WHERE P.OrderID IN(SELECT OrderOnline.OrderID FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID JOIN TickTypeInSche ON TicketOnlDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo WHERE PaymentDate = ?)";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (movID != 0) {
                st.setInt(1, movID);
                st.setDate(2, d);
            } else {
                st.setDate(1, d);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"), rs.getDouble("TotalAmount"));

                listO.add(o);
            }
            String date = "", month = "", year = "";
            int k = 0;
            for (int i = 0; i < d.toString().length(); i++) {
                if (d.toString().charAt(i) == '-') {
                    if (k == 0) {
                        year = d.toString().substring(0, i);
                        k = i;
                    }
                    if (!year.equals("") && k < i) {
                        month = d.toString().substring(i - 2, i);
                        k = i;
                    }
                }
                if (i == d.toString().length() - 1) {
                    date = d.toString().substring(i - 1);
                    break;
                }

            }
            OrderOnlByDate obd = new OrderOnlByDate(date + "-" + month + "-" + year, listO);
            return obd;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<OrderOff> getAllOrderOffByUserName(String userName) {
        List<OrderOff> list = new ArrayList<>();
        try {
            String sql = "";
            if (userName != null) {
                sql = "SELECT * FROM OrderOffline WHERE Account = ?";
            } else {
                sql = "SELECT * FROM OrderOffline";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            CinemaDAO cnd = new CinemaDAO();
            if (userName != null) {
                st.setString(1, userName);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"));

                list.add(o);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public OrderOffByDate getAllOrderTicketOffByIDAD(int movID, Date d) {
        List<OrderOff> listO = new ArrayList<>();
        try {
            String sql = "";
            if (movID != 0) {
                sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n"
                        + "                     (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                     (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID\n"
                        + "                   UNION\n"
                        + "                     SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID WHERE P.OrderID IN(SELECT OrderOffline.OrderID FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo WHERE movID = ? AND Date = ?)";
            } else {
                sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n"
                        + "                     (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                     (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID\n"
                        + "                   UNION\n"
                        + "                     SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID WHERE P.OrderID IN(SELECT OrderOffline.OrderID FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID JOIN TickTypeInSche ON TicketOffDetail.ProductCode = TickTypeInSche.ProductCode JOIN Schedule ON TickTypeInSche.scheNo = Schedule.scheNo WHERE Date = ?)";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (movID != 0) {
                st.setInt(1, movID);
                st.setDate(2, d);
            } else {
                st.setDate(1, d);
            }
            CinemaDAO cnd = new CinemaDAO();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"), rs.getDouble("TotalAmount"), cnd.getCinemaNameByID(rs.getInt("cinID")));

                listO.add(o);
            }
            String date = "", month = "", year = "";
            int k = 0;
            for (int i = 0; i < d.toString().length(); i++) {
                if (d.toString().charAt(i) == '-') {
                    if (k == 0) {
                        year = d.toString().substring(0, i);
                        k = i;
                    }
                    if (!year.equals("") && k < i) {
                        month = d.toString().substring(i - 2, i);
                        k = i;
                    }
                }
                if (i == d.toString().length() - 1) {
                    date = d.toString().substring(i - 1);
                    break;
                }

            }
            OrderOffByDate obd = new OrderOffByDate(date + "-" + month + "-" + year, listO);
            return obd;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public OrderOnl getOrderOnlByID(String orderID) {
        try {
            String sql = "SELECT * FROM OrderOnline WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                return o;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public OrderOff getOrderOffByID(String orderID) {
        try {
            String sql = "SELECT * FROM OrderOffline WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"));
                return o;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public List<OrderOnl> getAllOrderFoodByID(String orderID) {
        List<OrderOnl> list = new ArrayList<>();
        try {
            String sql = "SELECT OrderOnline.* FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID WHERE OrderOnline.OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<OrderOnl> getAllIn4OrderByID(String orderID) {
        List<OrderOnl> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderOnline WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<OrderOff> getAllIn4OrderOffByID(String orderID) {
        List<OrderOff> list = new ArrayList<>();
        EmployeeDAO emd = new EmployeeDAO();
        try {
            String sql = "SELECT * FROM OrderOffline WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            CinemaDAO cnd = new CinemaDAO();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"), cnd.getCinemaNameByID(rs.getInt("cinID")), emd.getEmployeeByID(rs.getInt("EmpID")).getLastName() + " " + emd.getEmployeeByID(rs.getInt("EmpID")).getFirstName());
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<OrderOnl> getAllOrderTicketByID(String orderID) {
        List<OrderOnl> list = new ArrayList<>();
        try {
            String sql = "SELECT OrderOnline.* FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID WHERE OrderOnline.OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"));
                list.add(o);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public OrderOnlByDate getAllOrderOnlByUserNameAPDate(String userName, Date paymentDate) {
        List<OrderOnl> listO = new ArrayList<>();
        try {
            String sql = "";
            if (userName != null) {
                sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                        + "(SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "(SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID\n"
                        + "UNION\n"
                        + "SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID WHERE UserName = ? AND PaymentDate = ?";
            } else {
                sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                        + "(SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "(SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID\n"
                        + "UNION\n"
                        + "SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID WHERE PaymentDate = ?";
            }

            PreparedStatement st = connection.prepareStatement(sql);
            if (userName != null) {
                st.setString(1, userName);
                st.setDate(2, paymentDate);
            } else {
                st.setDate(1, paymentDate);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOnl o = new OrderOnl(rs.getString("OrderID"), rs.getString("UserName"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("Phone"), rs.getString("Email"), rs.getString("Country"), rs.getString("Street"), rs.getString("District"), rs.getString("City"), rs.getString("PaymentType"), rs.getDate("PaymentDate"), rs.getTime("PaymentTime"), rs.getDouble("TotalAmount"));

                listO.add(o);
            }
            String date = "", month = "", year = "";
            int k = 0;
            for (int i = 0; i < paymentDate.toString().length(); i++) {
                if (paymentDate.toString().charAt(i) == '-') {
                    if (k == 0) {
                        year = paymentDate.toString().substring(0, i);
                        k = i;
                    }
                    if (!year.equals("") && k < i) {
                        month = paymentDate.toString().substring(i - 2, i);
                        k = i;
                    }
                }
                if (i == paymentDate.toString().length() - 1) {
                    date = paymentDate.toString().substring(i - 1);
                    break;
                }

            }
            OrderOnlByDate obd = new OrderOnlByDate(date + "-" + month + "-" + year, listO);
            return obd;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public OrderOffByDate getAllOrderOffByUserNameAPDate(String userName, Date paymentDate) {
        List<OrderOff> listO = new ArrayList<>();
        try {
            String sql = "";
            if (userName != null) {
                sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n"
                        + "                     (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                     (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID\n"
                        + "                   UNION\n"
                        + "                     SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID WHERE Account = ? AND Date = ?";
            } else {
                sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n"
                        + "                     (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                        + "                     (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID\n"
                        + "                   UNION\n"
                        + "                     SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID WHERE Date = ?";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (userName != null) {
                st.setString(1, userName);
                st.setDate(2, paymentDate);
            } else {
                st.setDate(1, paymentDate);
            }
            CinemaDAO cnd = new CinemaDAO();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                OrderOff o = new OrderOff(rs.getString("OrderID"), rs.getString("Account"), rs.getString("CusName"), rs.getString("CusPhone"), rs.getInt("EmpID"), rs.getInt("cinID"), rs.getString("PaymentType"), rs.getDate("Date"), rs.getTime("Time"), rs.getDouble("TotalAmount"), cnd.getCinemaNameByID(rs.getInt("cinID")));

                listO.add(o);
            }
            String date = "", month = "", year = "";
            int k = 0;
            for (int i = 0; i < paymentDate.toString().length(); i++) {
                if (paymentDate.toString().charAt(i) == '-') {
                    if (k == 0) {
                        year = paymentDate.toString().substring(0, i);
                        k = i;
                    }
                    if (!year.equals("") && k < i) {
                        month = paymentDate.toString().substring(i - 2, i);
                        k = i;
                    }
                }
                if (i == paymentDate.toString().length() - 1) {
                    date = paymentDate.toString().substring(i - 1);
                    break;
                }

            }
            OrderOffByDate obd = new OrderOffByDate(date + "-" + month + "-" + year, listO);
            return obd;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public int getNumberOfOrderByUserName(String username) {
        int cnt = 0;
        try {
            String sql = "SELECT COUNT(OrderID) AS TotalOrder FROM \n"
                    + "(SELECT OrderID, UserName FROM OrderOnline\n"
                    + "UNION \n"
                    + "SELECT OrderID, Account FROM OrderOffline) AS [T]\n"
                    + "WHERE T.UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalOrder");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cnt;
    }
    
    public void upd() {
        List<Ticket> t = new ArrayList<>();
        try {
            String sql = "SELECT * FROM TicketOffDetail";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                
                t.add(new Ticket(rs.getString("ProductCode"), rs.getInt("SeatNumber"), rs.getString("SeatType"), rs.getDouble("Price")));
            }
            String sql1 = "UPDATE TicketOffDetail SET Type = ? WHERE ProductCode = ? AND SeatNumber = ? AND SeatType = ?";
            
            for (int i = 0; i < t.size(); i++) {
                if(t.get(i).getPrice() == 65000) {
                    st = connection.prepareStatement(sql1);
                    st.setInt(1, 1);
                    st.setString(2, t.get(i).getProductCode());
                    st.setInt(3, t.get(i).getRow());
                    st.setString(4, t.get(i).getCol());
                    st.executeUpdate();
                    
                }
                else if(t.get(i).getPrice() == 80000) {
                    st = connection.prepareStatement(sql1);
                    st.setInt(1, 2);
                    st.setString(2, t.get(i).getProductCode());
                    st.setInt(3, t.get(i).getRow());
                    st.setString(4, t.get(i).getCol());
                    st.executeUpdate();
                    
                }
                else if(t.get(i).getPrice() == 160000) {
                    st = connection.prepareStatement(sql1);
                    st.setInt(1, 3);
                    st.setString(2, t.get(i).getProductCode());
                    st.setInt(3, t.get(i).getRow());
                    st.setString(4, t.get(i).getCol());
                    st.executeUpdate();
                    
                }
            }
        } catch (Exception e) {
        }
    }

}
