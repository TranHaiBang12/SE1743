/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FoodDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Food;

/**
 *
 * @author acer
 */
public class UpdF extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdF</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdF at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        FoodDAO fd = new FoodDAO();
        String id = request.getParameter("id");
        Food f = fd.getFoodById(id);
        request.setAttribute("f", f);
        request.setAttribute("id", id);
        List<String> discontinued = new ArrayList<>();
        discontinued.add("Tiếp tục kinh doanh sản phẩm này");
        discontinued.add("Không kinh doanh sản phẩm này nữa");
        List<String> status = new ArrayList<>();
        status.add("ĐANG BÁN");
        status.add("HẾT HÀNG");
        request.setAttribute("status", status);
        request.setAttribute("type", fd.getAllFoodType());
        request.setAttribute("discontinued", discontinued);
        request.getRequestDispatcher("updFood.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String img = request.getParameter("img");
        String fd = request.getParameter("fd");
        String tl = request.getParameter("tl");
        String dc_raw = request.getParameter("dc");
        String price_raw = request.getParameter("price");
        String stt = request.getParameter("stt");
        String kd = request.getParameter("kd");
        String id = request.getParameter("id");
        
        Double dc = Double.parseDouble(dc_raw);
        Double price = Double.parseDouble(price_raw);
        FoodDAO fdo = new FoodDAO();
        int dis = 0;
        if(kd.equals("Tiếp tục kinh doanh sản phẩm này")) {
            dis = 0;
        }
        else if(kd.equals("Không kinh doanh sản phẩm này nữa")) {
            dis = 1;
        }
        Food f = fdo.getFoodById(id);
        request.setAttribute("f", f);
        request.setAttribute("id", id);
        String dct = "";
        if(f.getDiscontinued() == 0) {
            dct = "Tiếp tục kinh doanh sản phẩm này";
            
        }
        else if(f.getDiscontinued() == 1) {
            dct = "Không kinh doanh sản phẩm này nữa";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String t = decimalFormat.format(dc);
        
        request.setAttribute("dc", dct);
        fdo.updateFoodByID(img, fd, tl, Double.parseDouble(t), price * 1000, stt, dis, id);
        request.setAttribute("ms", "Update thành công");
        request.getRequestDispatcher("updFood.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
