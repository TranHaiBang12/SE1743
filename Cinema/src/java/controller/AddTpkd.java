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
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author acer
 */
public class AddTpkd extends HttpServlet {
   
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
            out.println("<title>Servlet AddTpkd</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddTpkd at " + request.getContextPath () + "</h1>");
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
        String id_raw = request.getParameter("id");
        if(id_raw == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        else {
            EmployeeDAO ed = new EmployeeDAO();
            int id = 0;
            try {
                id = Integer.parseInt(id_raw);
                if(ed.getEmployeeByID(id) == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int a[] = sd.getAllShiftID(id);
            request.setAttribute("a", a);
            request.setAttribute("e", ed.getEmployeeByID(id));
            request.getRequestDispatcher("addTpkd.jsp").forward(request, response);
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
        EmployeeDAO ed = new EmployeeDAO();
        ShiftDAO sd = new ShiftDAO();
        TimekeepingDAO tpkd = new TimekeepingDAO();
        String empID_raw = request.getParameter("empID");
        String shiftID_raw = request.getParameter("id");
        String startWork_raw = request.getParameter("startWork");
        String endWork_raw = request.getParameter("endWork");
        String onleave_raw = request.getParameter("onleave");
        String date_raw = request.getParameter("date");
        System.out.println(empID_raw);
        System.out.println("pk" + empID_raw + " " + shiftID_raw + " " + startWork_raw + " " + endWork_raw + " " + onleave_raw + " " + date_raw);
        int empID = 0;
        int shiftID = 0;
        int onleave = 0;
        Date date = null;
        Time startWork = null;
        Time endWork = null;
        int cntS = 0;
        int cntE = 0;
        for (int i = 0; i < startWork_raw.length(); i++) {
            if (startWork_raw.charAt(i) == ':') {
                cntS++;
            }
        }
        for (int i = 0; i < endWork_raw.length(); i++) {
            if (endWork_raw.charAt(i) == ':') {
                cntE++;
            }
        }
        System.out.println(cntS + " " + cntE);
        try {
            empID = Integer.parseInt(empID_raw);
            shiftID = Integer.parseInt(shiftID_raw);
            onleave = Integer.parseInt(onleave_raw);
            date = Date.valueOf(date_raw);
            if (cntS == 1) {
                startWork = Time.valueOf(startWork_raw + ":00");
            }
            else if (cntS == 2) {
                startWork = Time.valueOf(startWork_raw);
            }
            if (cntE == 1) {
                endWork = Time.valueOf(endWork_raw + ":00");
            }
            else if (cntE == 2) {
                endWork = Time.valueOf(endWork_raw);
            }
            if (ed.getEmployeeByID(empID) == null) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        System.out.println("p" + empID + " " + shiftID + " " + startWork + " " + endWork + " " + onleave + " " + date);
        if(tpkd.checkTimekeepingByEmpAD(empID, date) == true) {
            int a[] = sd.getAllShiftID(empID);
            request.setAttribute("ms", "Đã có dự liệu chấm công của nhân viên này trong khoảng thời gian này");
            request.setAttribute("a", a);
            request.setAttribute("e", ed.getEmployeeByID(empID));
            request.getRequestDispatcher("addTpkd.jsp").forward(request, response);
        }
        else {
            tpkd.insert(empID, shiftID, startWork, endWork, onleave, date);
            request.setAttribute("ms", "Thêm thành công");
            int a[] = sd.getAllShiftID(empID);
            request.setAttribute("a", a);
            request.setAttribute("e", ed.getEmployeeByID(empID));
            request.getRequestDispatcher("addTpkd.jsp").forward(request, response);
        }
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
