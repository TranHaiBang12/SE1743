/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.FAP.util;

import java.sql.Date;

/**
 *
 * @author acer
 */
public class FormatDate {
    public String formatDateSQL(Date d) {
        String t = d.toString();
        String a = "";
        String date = "", month = "", year = "";
        int k = 0, count = 0;
        for (int i = 0; i < t.length(); i++) {
            if(t.charAt(i) == '-' && count == 0) {
                 year = t.substring(k, i);
                 count++;
                 k = i;
            }
            else if(t.charAt(i) == '-' && count == 1) {
                 month = t.substring(k + 1, i);
                 count++;
                 k = i;
            }
            else if(i == t.length() - 1) {
                date = t.substring(k + 1, i + 1);
            }
        }
        return date + "-" + month+ "-" + year;
    }
    
    public Date formatStringToDateSQL(String d) {
        String a = "";
        int k = 0;
        for (int i = 0; i < d.length(); i++) {
            if(d.charAt(i) == '-') {
                 a = a.concat(d.substring(k, i));
                 k = i;
            }
            else if(i == d.length() - 1) {
                a = a.concat(d.substring(k, i));
            }
        }
        return Date.valueOf(a);
    }
}
