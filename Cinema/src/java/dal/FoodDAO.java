/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Food;

/**
 *
 * @author acer
 */
public class FoodDAO extends DBContext {

    public List<Food> getAllFood() {
        List<Food> list = new ArrayList<>();
        int i = 1;
        try {
            String sql = "SELECT Food.*, Product.Price, Product.Discout FROM Food JOIN Product ON Food.ProductCode = Product.ProductCode";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Food f = new Food(i, rs.getString("ProductCode"), rs.getString("FoodDescription"), rs.getString("FoodType"), rs.getString("Status"), rs.getDouble("Discout"), rs.getDouble("Price"), rs.getString("Img"));
                list.add(f);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Food> getFoodByPage(List<Food> list, int start, int end) {
        List<Food> list2 = new ArrayList<>();
    
        try {
            for (int i = start; i <= end; i++) {
                list2.add(list.get(i));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list2;
    }

    public Food getFoodById(String productCode) {
        try {
            String sql = "SELECT Food.*, Discout, Price FROM Food JOIN Product ON Food.ProductCode = Product.ProductCode WHERE Food.ProductCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, productCode);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Food f = new Food(0, rs.getString("ProductCode"), rs.getString("FoodDescription"), rs.getString("FoodType"), rs.getString("Status"), rs.getDouble("Discout"), rs.getDouble("Price"), rs.getString("Img"), getFoodTypeNameByID(rs.getString("FoodType")), rs.getInt("Discontinued"));
                return f;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public String getFoodTypeNameByID(String id) {
        try {
            String sql = "SELECT * FROM FoodType WHERE ftID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString("ftName");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Food> getFoodByType(String type, String key) {
        List<Food> list = new ArrayList<>();
        int i = 1;
        try {
            String sql;
            if (type.equals("")) {
                sql = "SELECT Food.*, Product.Price, Product.Discout, FoodType.ftName FROM Food JOIN Product ON Food.ProductCode = Product.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID WHERE FoodDescription LIKE '%" + key + "%'";
            } else {
                sql = "SELECT Food.*, Product.Price, Product.Discout, FoodType.ftName FROM Food JOIN Product ON Food.ProductCode = Product.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID WHERE Food.FoodType = ? AND FoodDescription LIKE '%" + key + "%'";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            if (!type.equals("")) {
                st.setString(1, type);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Food f = new Food(i, rs.getString("ProductCode"), rs.getString("FoodDescription"), rs.getString("FoodType"), rs.getString("Status"), rs.getDouble("Discout"), rs.getDouble("Price"), rs.getString("Img"), rs.getString("ftName"));
                list.add(f);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<String> getAllFoodOff() {
        List<String> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Food WHERE Status = N'HẾT HÀNG'";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("ProductCode"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateFoodByID(String img, String foodDescript, String type, double discount, double price, String status, int discontinued, String productCode) {

        try {
            String sql = "UPDATE Food SET FoodDescription = ?, FoodType = ?, Status = ?, Discontinued = ?, Img = ? WHERE ProductCode = ?";
            String sql1 = "UPDATE Product SET Discout = ?, Price = ? WHERE ProductCode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, foodDescript);
            st.setString(2, type);
            st.setString(3, status);
            st.setInt(4, discontinued);
            st.setString(5, img);
            st.setString(6, productCode);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setDouble(1, discount);
            st1.setDouble(2, price);
            st1.setString(3, productCode);
            st.executeUpdate();
            st1.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<Food> getAllFoodType() {
        List<Food> list = new ArrayList<>();
        try {
            int i = 0;
            String sql = "SELECT * FROM FoodType";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Food f = new Food(i, rs.getString("ftID"), rs.getString("ftName"));
                list.add(f);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void deleteFoodByID(String productCode) {
        try {
            String sql = "DELETE FROM Product WHERE ProductCode = ?";
            String sql1 = "DELETE FROM Food WHERE ProductCode = ?";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st = connection.prepareStatement(sql);
            st1.setString(1, productCode);
            st1.executeUpdate();
            st.setString(1, productCode);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
