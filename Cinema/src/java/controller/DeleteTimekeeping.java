/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.EmployeeDAO;
import dal.TimekeepingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Timekeeping;

/**
 *
 * @author acer
 */
public class DeleteTimekeeping extends HttpServlet {
   
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
            out.println("<title>Servlet DeleteTimekeeping</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteTimekeeping at " + request.getContextPath () + "</h1>");
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
        TimekeepingDAO tkpd = new TimekeepingDAO();
        EmployeeDAO ed = new EmployeeDAO();
        String id_raw = request.getParameter("id");
        String date_raw = request.getParameter("date");
        String month_raw = request.getParameter("month");
        String year_raw = request.getParameter("year");
        
        if(id_raw == null || date_raw == null || month_raw == null || year_raw == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        else {
            int id = 0;
            Date d = null;
            int month = 0;
            int year = 0;
            
            try {
                id = Integer.parseInt(id_raw);
                d = Date.valueOf(date_raw);
                month = Integer.parseInt(month_raw);
                year = Integer.parseInt(year_raw);
                if(ed.getEmployeeByID(id) == null || month > 12 || month < 1) {
                    throw new Exception("L");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            
            tkpd.dltTimekeeping(id, d);
            response.sendRedirect("emptp?id=" + id + "&month=" + month + "&year=" + year);
        }
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
