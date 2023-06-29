/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author acer
 */
public class MovieRate {
    private int noD;
    private int sum;
    private double avr;
    
    private int no5;
    private int no4;
    private int no3;
    private int no2;
    private int no1;
    
    private String pc5;
    private String pc4;
    private String pc3;
    private String pc2;
    private String pc1;

    public MovieRate(int noD, int sum, double avr, int no5, int no4, int no3, int no2, int no1, String pc5, String pc4, String pc3, String pc2, String pc1) {
        this.noD = noD;
        this.sum = sum;
        this.avr = avr;
        this.no5 = no5;
        this.no4 = no4;
        this.no3 = no3;
        this.no2 = no2;
        this.no1 = no1;
        this.pc5 = pc5;
        this.pc4 = pc4;
        this.pc3 = pc3;
        this.pc2 = pc2;
        this.pc1 = pc1;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public double getAvr() {
        return avr;
    }

    public void setAvr(double avr) {
        this.avr = avr;
    }
    
    

    public int getNoD() {
        return noD;
    }

    public void setNoD(int noD) {
        this.noD = noD;
    }

    public int getNo5() {
        return no5;
    }

    public void setNo5(int no5) {
        this.no5 = no5;
    }

    public int getNo4() {
        return no4;
    }

    public void setNo4(int no4) {
        this.no4 = no4;
    }

    public int getNo3() {
        return no3;
    }

    public void setNo3(int no3) {
        this.no3 = no3;
    }

    public int getNo2() {
        return no2;
    }

    public void setNo2(int no2) {
        this.no2 = no2;
    }

    public int getNo1() {
        return no1;
    }

    public void setNo1(int no1) {
        this.no1 = no1;
    }

    public String getPc5() {
        return pc5;
    }

    public void setPc5(String pc5) {
        this.pc5 = pc5;
    }

    public String getPc4() {
        return pc4;
    }

    public void setPc4(String pc4) {
        this.pc4 = pc4;
    }

    public String getPc3() {
        return pc3;
    }

    public void setPc3(String pc3) {
        this.pc3 = pc3;
    }

    public String getPc2() {
        return pc2;
    }

    public void setPc2(String pc2) {
        this.pc2 = pc2;
    }

    public String getPc1() {
        return pc1;
    }

    public void setPc1(String pc1) {
        this.pc1 = pc1;
    }
    
    
}
