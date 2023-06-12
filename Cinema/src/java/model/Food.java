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
    private int numberLeft;
    private String status;
    private double discount;
    private double price;
    private String img;

    public Food(int id, String productCode, String foodDescript, String foodType, int numberLeft, String status, double discount, double price, String img) {
        this.id = id;
        this.productCode = productCode;
        this.foodDescript = foodDescript;
        this.foodType = foodType;
        this.numberLeft = numberLeft;
        this.status = status;
        this.discount = discount;
        this.price = price;
        this.img = img;
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

    public int getNumberLeft() {
        return numberLeft;
    }

    public void setNumberLeft(int numberLeft) {
        this.numberLeft = numberLeft;
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