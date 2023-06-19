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

    public List<OrderOnl> getAllOrderOnlByUserName(String userName) {
        List<OrderOnl> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderOnline WHERE UserName = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
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
    
    public List<OrderOff> getAllOrderOffByUserName(String userName) {
        List<OrderOff> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderOffline WHERE Account = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            CinemaDAO cnd = new CinemaDAO();
            st.setString(1, userName);
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
            String sql = "SELECT OrderOnline.*, P.TotalAmount FROM OrderOnline JOIN\n"
                    + "(SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n"
                    + "(SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOnline JOIN OrderOnlineDetail ON OrderOnline.OrderID = OrderOnlineDetail.OrderID GROUP BY OrderOnline.OrderID\n"
                    + "UNION\n"
                    + "SELECT OrderOnline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOnline JOIN TicketOnlDetail ON OrderOnline.OrderID = TicketOnlDetail.OrderID GROUP BY OrderOnline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOnline.OrderID = P.OrderID WHERE UserName = ? AND PaymentDate = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setDate(2, paymentDate);
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
            String sql = "SELECT OrderOffline.*, P.TotalAmount FROM OrderOffline JOIN\n" +
"                     (SELECT OrderID, Sum(TotalAmount) AS TotalAmount FROM\n" +
"                     (SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount) * Quantity) AS TotalAmount FROM OrderOffline JOIN OrderOfflineDetail ON OrderOffline.OrderID = OrderOfflineDetail.OrderID GROUP BY OrderOffline.OrderID\n" +
"                   UNION\n" +
"                     SELECT OrderOffline.OrderID, SUM(Price * (1 - Discount)) FROM OrderOffline JOIN TicketOffDetail ON OrderOffline.OrderID = TicketOffDetail.OrderID GROUP BY OrderOffline.OrderID) AS [T]  GROUP BY OrderID) AS [P] ON OrderOffline.OrderID = P.OrderID WHERE Account = ? AND Date = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setDate(2, paymentDate);
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
            
        } catch (Exception e) {
        }
        return cnt;
    }
}
