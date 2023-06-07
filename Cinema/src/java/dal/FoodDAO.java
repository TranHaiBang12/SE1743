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
public class FoodDAO extends DBContext{
    public List<Food> getAllFood() {
        List<Food> list = new ArrayList<>();
        try {
            String sql = "SELECT Food.*, Product.Price, Product.Discout FROM Food JOIN Product ON Food.ProductCode = Product.ProductCode";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Food f = new Food(rs.getString("ProductCode"), rs.getString("FoodDescription"), rs.getString("FoodType"), rs.getInt("NumberLeft"), rs.getString("Status"), rs.getDouble("Discout"), rs.getDouble("Price"), rs.getString("Img"));
                System.out.println(f);
                list.add(f);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
