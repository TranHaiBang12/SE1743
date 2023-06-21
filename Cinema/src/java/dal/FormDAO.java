/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.FormMD;

/**
 *
 * @author acer
 */
public class FormDAO extends DBContext{
    public FormMD getFormById(int id) {
        try {
            String sql = "SELECT formName FROM Schedule JOIN Form ON Schedule.formID = Form.formID WHERE Schedule.formID = ?"; 
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                FormMD f = new FormMD(id, rs.getString("formName"));
                return f;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<FormMD> getAllForm() {
        List<FormMD> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Form";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                FormMD f = new FormMD(rs.getInt("formID"), rs.getString("formName"));
                list.add(f);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
}
