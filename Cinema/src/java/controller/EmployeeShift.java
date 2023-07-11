/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.EmployeeDAO;
import dal.ShiftDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Shift;

/**
 *
 * @author acer
 */
public class EmployeeShift extends HttpServlet {
   
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
            out.println("<title>Servlet EmployeeShift</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeShift at " + request.getContextPath () + "</h1>");
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
        ShiftDAO sd = new ShiftDAO();
        EmployeeDAO ed = new EmployeeDAO();
        if (request.getParameter("id") == null || request.getParameter("month") == null || request.getParameter("year") == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            String id_raw = request.getParameter("id");
            String month_raw = request.getParameter("month");
            String year_raw = request.getParameter("year");

            int id = 0;
            int month = 0;
            int year = 0;
            try {
                id = Integer.parseInt(id_raw);
                month = Integer.parseInt(month_raw);
                year = Integer.parseInt(year_raw);
                if (ed.getEmployeeByID(id) == null || month > 12 || month < 1) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            
            List<Shift> list = sd.getAllShiftByEmpAMAY(id, month, year);
            for (int j = 0; j < list.size(); j++) {
                String dateS = "", monthS = "", yearS = "";
                String dateE = "", monthE = "", yearE = "";
                String t = list.get(j).getStartDate().toString();
                int cnt = 0;
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        yearS = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        monthS = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                dateS = t.substring(cnt + 1);
                list.get(j).setStartDateS(dateS + "-" + monthS + "-" + yearS);
                t = list.get(j).getEndDate().toString();
                cnt = 0;
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        yearE = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        monthE = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                dateE = t.substring(cnt + 1);
                list.get(j).setEndDateS(dateE + "-" + monthE + "-" + yearE);
            }
        
            
            request.setAttribute("e", ed.getEmployeeByID(id));
            request.setAttribute("month", month);
            request.setAttribute("year", year);
            if (!list.isEmpty()) {
                request.setAttribute("list", list);
            }
            else {
                request.setAttribute("ms", "Không có dữ liệu về ca trực của thời gian này");
            }
            
            request.getRequestDispatcher("empShift.jsp").forward(request, response);
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
