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
import java.sql.Time;
import model.Timekeeping;

/**
 *
 * @author acer
 */
public class UpdTkp extends HttpServlet {

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
            out.println("<title>Servlet UpdTkp</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdTkp at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String date_raw = request.getParameter("date");
        if (id_raw == null || date_raw == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            int id = 0;
            Date d = null;
            try {
                d = Date.valueOf(date_raw);
                id = Integer.parseInt(id_raw);
            } catch (Exception e) {
                System.out.println(e);
            }
            TimekeepingDAO tkpd = new TimekeepingDAO();
            Timekeeping t = tkpd.getTimekeepingByDateAID(id, d);
            String dateS = "", monthS = "", yearS = "";
            String t1 = t.getDate().toString();
            int cnt = 0;
            for (int i = 0; i < t1.length(); i++) {
                if (t1.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                    yearS = t1.substring(cnt, i);
                    cnt = i;
                } else if (t1.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                    monthS = t1.substring(cnt + 1, i);
                    cnt = i;
                }
            }
            dateS = t1.substring(cnt + 1);
            t.setDateS(dateS + "-" + monthS + "-" + yearS);
            request.setAttribute("t", t);

            request.setAttribute("date", d);
            request.setAttribute("e", ed.getEmployeeByID(id));
            request.getRequestDispatcher("updTkp.jsp").forward(request, response);
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
        TimekeepingDAO tkpd = new TimekeepingDAO();
        EmployeeDAO ed = new EmployeeDAO();
        String startWork_raw = request.getParameter("startWork");
        String endWork_raw = request.getParameter("endWork");
        String onleave_raw = request.getParameter("onleave");
        String empID_raw = request.getParameter("empID");
        String date_raw = request.getParameter("date");

        int empID = 0;
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
        try {
            empID = Integer.parseInt(empID_raw);
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
        tkpd.updTimekeepingByEmpIDAD(empID, date, startWork, endWork, onleave);
        Timekeeping t = tkpd.getTimekeepingByDateAID(empID, date);
        String dateS = "", monthS = "", yearS = "";
        String t1 = t.getDate().toString();
        int cnt = 0;
        for (int i = 0; i < t1.length(); i++) {
            if (t1.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                yearS = t1.substring(cnt, i);
                cnt = i;
            } else if (t1.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                monthS = t1.substring(cnt + 1, i);
                cnt = i;
            }
        }
        dateS = t1.substring(cnt + 1);
        t.setDateS(dateS + "-" + monthS + "-" + yearS);
        request.setAttribute("t", t);
        request.setAttribute("ms", "Sửa thành công");
        request.setAttribute("date", date);
        request.setAttribute("e", ed.getEmployeeByID(empID));
        request.getRequestDispatcher("updTkp.jsp").forward(request, response);

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
