/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author acer
 */
public class OrderDetailDAO extends DBContext{
    public void insert(String orderID, String productCode, double discount, double price, int quantity) {
        try {
            String sql = "INSERT INTO OrderOnlineDetail VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            st.setString(2, productCode);
            st.setDouble(3, discount);
            st.setDouble(4, price);
            st.setInt(5, quantity);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
            
    }
    
    public List<OrderDetail> getAllProductInOrderByOrderID(String orderID) {
        List<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderOnlineDetail WHERE OrderID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, orderID);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                OrderDetail od = new OrderDetail(orderID, rs.getString("ProductCode"), rs.getDouble("Discount"), rs.getDouble("Price"), rs.getInt("Quantity"));
                list.add(od);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
