/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderTicketDetailDAO;
import dal.TransactionCDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;
import model.OrderDetail;
import model.OrderTicketDetail;
import model.TransactionCode;

/**
 *
 * @author acer
 */
public class In4ODServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet In4ODServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet In4ODServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        String orderID = request.getParameter("id");
        OrderDAO ord = new OrderDAO();
        List<Order> listO = ord.getAllIn4OrderByID(orderID);
        OrderDetailDAO odd = new OrderDetailDAO();
        List<OrderDetail> listOD = odd.getAllProductInOrderByOrderID(orderID);
        OrderTicketDetailDAO otd = new OrderTicketDetailDAO();
        List<OrderTicketDetail> listOTD = otd.getTkByOrderID(orderID);
        
        for (int i = 0; i < listOTD.size(); i++) {
            if(listOTD.get(i).getType().equals("NM")) {
                listOTD.get(i).setType("Thường");
            }
            else if(listOTD.get(i).getType().equals("VP")) {
                listOTD.get(i).setType("VIP");
            }
            else if(listOTD.get(i).getType().equals("VT")) {
                listOTD.get(i).setType("Đôi");
            }
        }
        TransactionCDAO tcd = new TransactionCDAO();
        List<TransactionCode> listTC = tcd.getAllCodeByOrderID(orderID);
        
        request.setAttribute("listO", listO);
        request.setAttribute("listOD", listOD);
        request.setAttribute("listOTD", listOTD);
        request.setAttribute("listTC", listTC);
        request.getRequestDispatcher("in4OD.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
