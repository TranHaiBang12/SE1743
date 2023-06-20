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
                System.out.println(f);
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
        System.out.println(start + " " + end + "dadawsssss");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("d?ea");
            System.out.println(list.get(i).getFoodDescript());
        }
        try {
            for (int i = start; i <= end; i++) {
                System.out.println(list.get(i) + "dawa");
                list2.add(list.get(i));
            }
            
        } catch (Exception e) {
            System.out.println(e + "da");
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
                Food f = new Food(0, rs.getString("ProductCode"), rs.getString("FoodDescription"), rs.getString("FoodType"), rs.getString("Status"), rs.getDouble("Discout"), rs.getDouble("Price"), rs.getString("Img"));
                return f;
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
                System.out.println(f);
                list.add(f);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
}
