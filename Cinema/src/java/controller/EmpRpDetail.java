/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployeeDAO;
import dal.ShiftDAO;
import dal.TimekeepingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author acer
 */
public class EmpRpDetail extends HttpServlet {

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
            out.println("<title>Servlet EmpRpDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmpRpDetail at " + request.getContextPath() + "</h1>");
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
        EmployeeDAO ed = new EmployeeDAO();
        ShiftDAO sd = new ShiftDAO();
        TimekeepingDAO tkpd = new TimekeepingDAO();

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
                if(ed.getEmployeeByID(id) == null || month > 12 || month < 1) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            
            int numDateWork = tkpd.geNumDateWorkByTime(id, year, month);
            double numHourWork = tkpd.geNumHourWorkByTime(id, year, month);
            
            int numDateOff = tkpd.getNumDateOffWorkByTime(id, year, month);
            int numDateOffHasP = tkpd.getNumDateOffWorkByTimeHasPermission(id, year, month);
            
            int numDateComeSoon = tkpd.getNumDateComeSoon(id, year, month);
            int numDateComeR = tkpd.getNumDateComeRightTime(id, year, month);
            int numDateComeL = tkpd.getNumDateComeLate(id, year, month);
            
            int numDateRSoon = tkpd.getNumDateReturnSoon(id, year, month);
            int numDateRR = tkpd.getNumDateReturnRightTime(id, year, month);
            int numDateOT = tkpd.getNumDateOT(id, year, month);
            
            request.setAttribute("numDateWork", numDateWork);
            request.setAttribute("numHourWork", numHourWork);
            
            request.setAttribute("numDateOff", numDateOff);
            request.setAttribute("numDateOffHasP", numDateOffHasP);
            
            request.setAttribute("numDateCS", numDateComeSoon);
            request.setAttribute("numDateCR", numDateComeR);
            request.setAttribute("numDateCL", numDateComeL);
            
            request.setAttribute("numDateRS", numDateRSoon);
            request.setAttribute("numDateRR", numDateRR);
            request.setAttribute("numDateOT", numDateOT);
            
            request.setAttribute("e", ed.getEmployeeByID(id));
            request.setAttribute("month", month);
            request.setAttribute("year", year);
            request.getRequestDispatcher("empRpD.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
