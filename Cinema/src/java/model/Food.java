/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class Food {
    private int id;
    private String productCode;
    private String foodDescript;
    private String foodType;
    private String status;
    private double discount;
    private double price;
    private String img;
    private String typeName;

    public Food(int id, String productCode, String foodDescript, String foodType, String status, double discount, double price, String img) {
        this.id = id;
        this.productCode = productCode;
        this.foodDescript = foodDescript;
        this.foodType = foodType;
        this.status = status;
        this.discount = discount;
        this.price = price;
        this.img = img;
    }
    
    public Food(int id, String productCode, String foodDescript, String foodType, String status, double discount, double price, String img, String typeName) {
        this.id = id;
        this.productCode = productCode;
        this.foodDescript = foodDescript;
        this.foodType = foodType;
        this.status = status;
        this.discount = discount;
        this.price = price;
        this.img = img;
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getFoodDescript() {
        return foodDescript;
    }

    public void setFoodDescript(String foodDescript) {
        this.foodDescript = foodDescript;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    
}
