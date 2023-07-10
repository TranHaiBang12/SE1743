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
import java.sql.Date;
import java.sql.Time;
import model.Shift;

/**
 *
 * @author acer
 */
public class UpdShift extends HttpServlet {

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
            out.println("<title>Servlet UpdShift</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdShift at " + request.getContextPath() + "</h1>");
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
     * @throws IOExceintption if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("id") == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            ShiftDAO sd = new ShiftDAO();
            request.setAttribute("k", sd.getShiftByID(id));
            request.getRequestDispatcher("updShift.jsp").forward(request, response);
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
        EmployeeDAO ed = new EmployeeDAO();
        ShiftDAO sd = new ShiftDAO();
        String empID_raw = request.getParameter("empID");
        String id_raw = request.getParameter("id");
        String startWork_raw = request.getParameter("startWork");
        String endWork_raw = request.getParameter("endWork");
        String startDate_raw = request.getParameter("startDate");
        String endDate_raw = request.getParameter("endDate");

        int empID = 0;
        int id = 0;
        Time startWork = null;
        Time endWork = null;
        Date startDate = null;
        Date endDate = null;
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

        try {
            empID = Integer.parseInt(empID_raw);
            id = Integer.parseInt(id_raw);
            if (cntS == 2) {
                startWork = Time.valueOf(startWork_raw);
            } else if (cntS == 1) {
                startWork = Time.valueOf(startWork_raw + ":00");
            }
            if (cntE == 2) {
                endWork = Time.valueOf(endWork_raw);
            }
            else if(cntE == 1) {
                endWork = Time.valueOf(endWork_raw + ":00");
            }
            startDate = Date.valueOf(startDate_raw);
            endDate = Date.valueOf(endDate_raw);
            if (ed.getEmployeeByID(empID) == null) {
                throw new Exception("L");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp");
        }


        //check trung shift id
        if (sd.checkShiftUpdByDate(startDate, endDate, id, empID) == true) {
            System.out.println(id + " " + startDate + " " + endDate);
            request.setAttribute("ms", "Khoảng thời gian này đã tồn tại 1 ca làm");
            request.setAttribute("e", ed.getEmployeeByID(empID));
            request.setAttribute("k", sd.getShiftByID(id));
            request.getRequestDispatcher("updShift.jsp").forward(request, response);
        } else {
            sd.updShift(id, empID, startWork, endWork, startDate, endDate);
            request.setAttribute("ms", "Sửa thành công");
            request.setAttribute("e", ed.getEmployeeByID(empID));
            Shift k = sd.getShiftByID(id);
            String dateS = "", monthS = "", yearS = "";
            String dateE = "", monthE = "", yearE = "";
            String t = k.getStartDate().toString();
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
            k.setStartDateS(dateS + "-" + monthS + "-" + yearS);
            t = k.getEndDate().toString();
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
            k.setEndDateS(dateE + "-" + monthE + "-" + yearE);

            request.setAttribute("k", k);

            request.getRequestDispatcher("updShift.jsp").forward(request, response);

        }
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
