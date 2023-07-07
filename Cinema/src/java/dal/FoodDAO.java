/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.Food;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class FoodDAO extends DBContext {

    public List<Food> getAllFood() {
        List<Food> list = new ArrayList<>();
        int i = 1;
        try {
            String sql = "SELECT Food.*, Product.Price, Product.Discout, FoodType.ftName FROM Food JOIN Product ON Food.ProductCode = Product.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID";
            PreparedStatement st = connection.prepareStatement(sql);
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
            while (rs.next()) {
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

    public int getNumFood() {
        int a = 0, b = 0;
        try {
            String sql = "SELECT SUM(Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID";
            String sql1 = "SELECT SUM(Quantity) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID";
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }

            st = connection.prepareStatement(sql1);

            rs = st.executeQuery();
            if (rs.next()) {
                b = rs.getInt("T");
            }
            return a + b;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;

    }

    public int getNumFoodByDate(Date dS, Date eS) {
        int a = 0, b = 0;
        try {
            String sql = "SELECT SUM(Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID WHERE PaymentDate BETWEEN ? AND ?";
            String sql1 = "SELECT SUM(Quantity) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }

            st = connection.prepareStatement(sql1);
            st.setDate(1, dS);
            st.setDate(2, eS);
            rs = st.executeQuery();
            if (rs.next()) {
                b = rs.getInt("T");
            }
            return a + b;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Food> getAllFoodTypeByDate(Date dS, Date eS) {
        List<Food> listF = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT ftID, ftName FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN Food ON OrderOnlineDetail.ProductCode = Food.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID WHERE PaymentDate BETWEEN ? AND ?\n"
                    + "UNION\n"
                    + "SELECT DISTINCT ftID, ftName FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID JOIN Food ON OrderOfflineDetail.ProductCode = Food.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setDate(3, dS);
            st.setDate(4, eS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Food f = new Food(0, rs.getString("ftID"), rs.getString("ftName"));
                listF.add(f);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listF;
    }

    public int getNumFoodTypeSellByDateAType(Date dS, Date eS, String type) {
        int a = 0, b = 0;
        try {
            String sql = "SELECT SUM(Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN Food ON OrderOnlineDetail.ProductCode = Food.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID WHERE (PaymentDate BETWEEN ? AND ?) AND ftID = ?";

            String sql1 = "SELECT SUM(Quantity) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID JOIN Food ON OrderOfflineDetail.ProductCode = Food.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID WHERE (Date BETWEEN ? AND ?) AND ftID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setString(3, type);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                a = rs.getInt("T");
            }
            st = connection.prepareStatement(sql1);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setString(3, type);
            rs = st.executeQuery();
            while (rs.next()) {
                b = rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a + b;
    }

    public List<Cinema> getAllCinByDate(Date dS, Date eS) {
        List<Cinema> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT Cinema.cinID, cinName FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN Food ON OrderOnlineDetail.ProductCode = Food.ProductCode LEFT JOIN FoodType ON Food.FoodType = FoodType.ftID JOIN TransactionCode ON OrderOnline.OrderID = TransactionCode.OrderID JOIN Cinema ON TransactionCode.cinID = Cinema.cinID WHERE (PaymentDate  BETWEEN ? AND ?) AND TransactionCode.Type = 1\n"
                    + "UNION\n"
                    + "SELECT DISTINCT Cinema.cinID, cinName FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID JOIN Food ON OrderOfflineDetail.ProductCode = Food.ProductCode LEFT JOIN FoodType ON Food.FoodType = FoodType.ftID JOIN Cinema ON OrderOffline.cinID = Cinema.cinID WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setDate(3, dS);
            st.setDate(4, eS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cinema c = new Cinema(rs.getInt("cinID"), rs.getString("cinName"));
                list.add(c);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int getNumFoodSellByCinAndDate(Date dS, Date eS, int cin) {
        int a = 0, b = 0;
        try {
            String sql = "SELECT SUM(Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN Food ON OrderOnlineDetail.ProductCode = Food.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID JOIN TransactionCode ON OrderOnline.OrderID = TransactionCode.OrderID JOIN Cinema ON TransactionCode.cinID = Cinema.cinID WHERE (PaymentDate BETWEEN ? AND ?) AND Cinema.cinID = ? AND TransactionCode.Type = 1";
            String sql1 = "SELECT SUM(Quantity) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID JOIN Food ON OrderOfflineDetail.ProductCode = Food.ProductCode JOIN FoodType ON Food.FoodType = FoodType.ftID JOIN Cinema ON OrderOffline.cinID = Cinema.cinID WHERE (Date BETWEEN ? AND ?) AND Cinema.cinID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cin);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                a = rs.getInt("T");
            }
            st = connection.prepareStatement(sql1);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setInt(3, cin);
            rs = st.executeQuery();
            while (rs.next()) {
                b = rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(a + b);
        return a + b;
    }

    public int getNumFoodByOnlAndOff(Date dS, Date eS, String type) {
        try {
            String sql = "";
            if (type.equals("ONL")) {
                sql = "SELECT SUM(Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID WHERE PaymentDate BETWEEN ? AND ?";
            } else if (type.equals("OFF")) {
                sql = "SELECT SUM(Quantity) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID WHERE Date BETWEEN ? AND ?";
            } else {
                return 0;
            }
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

    public int getNumFoodByDateEXACT(Date dS, Date eS, Date nS) {
        int a = 0, b = 0;
        try {
            String sql = "SELECT SUM(Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID WHERE PaymentDate = ?";
            String sql1 = "SELECT SUM(Quantity) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID WHERE Date = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, nS);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = rs.getInt("T");
            }
            st = connection.prepareStatement(sql1);
            st.setDate(1, nS);
            rs = st.executeQuery();
            if (rs.next()) {
                b = rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a + b;
    }

    public List<TIcketDate> getDateByNumTickDate(Date dS, Date eS) {
        List<TIcketDate> list = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT PaymentDate FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN Food ON OrderOnlineDetail.ProductCode = Food.ProductCode LEFT JOIN FoodType ON Food.FoodType = FoodType.ftID JOIN TransactionCode ON OrderOnline.OrderID = TransactionCode.OrderID JOIN Cinema ON TransactionCode.cinID = Cinema.cinID WHERE (PaymentDate  BETWEEN ? AND ?) AND TransactionCode.Type = 1\n"
                    + "UNION\n"
                    + "SELECT DISTINCT Date FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID JOIN Food ON OrderOfflineDetail.ProductCode = Food.ProductCode LEFT JOIN FoodType ON Food.FoodType = FoodType.ftID JOIN Cinema ON OrderOffline.cinID = Cinema.cinID WHERE Date BETWEEN ? AND ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, dS);
            st.setDate(2, eS);
            st.setDate(3, dS);
            st.setDate(4, eS);

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
    
    public int getIncomeOnl(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM((Price - Price * Discount) * Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN TransactionCode ON OrderOnline.OrderID = TransactionCode.OrderID WHERE TransactionCode.Type = 1 AND (PaymentDate BETWEEN ? AND ?) AND cinID = ?";
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
    
    public int getIncomeOnlByDAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT SUM((Price - Price * Discount) * Quantity) AS T FROM OrderOnlineDetail JOIN OrderOnline ON OrderOnlineDetail.OrderID = OrderOnline.OrderID JOIN TransactionCode ON OrderOnline.OrderID = TransactionCode.OrderID WHERE TransactionCode.Type = 2 AND cinID = ? AND (PaymentDate BETWEEN ? AND ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            st.setDate(2, dS);
            st.setDate(3, eS);
            
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getIncomeOffByDAC(Date dS, Date eS, int cinID) {
        try {
            String sql = "SELECT SUM(Price - Price * Discount) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID WHERE cinID = ? AND (Date BETWEEN ? AND ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cinID);
            st.setDate(2, dS);
            st.setDate(3, eS);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("T");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int getIncomeOff(Date dS, Date eS) {
        try {
            String sql = "SELECT SUM(Price - Price * Discount) AS T FROM OrderOfflineDetail JOIN OrderOffline ON OrderOfflineDetail.OrderID = OrderOffline.OrderID WHERE Date BETWEEN ? AND ?";
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

}
