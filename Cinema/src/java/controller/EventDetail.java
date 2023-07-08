/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EventDAO;
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
public class EventDetail extends HttpServlet {

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
            out.println("<title>Servlet EventDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EventDetail at " + request.getContextPath() + "</h1>");
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
        EventDAO evd = new EventDAO();
        String id_raw = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            if (evd.getEventByCode(id) == null) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        model.Event e = evd.getEventByCode(id);

        String dateS = "", monthS = "", yearS = "";
        String dateE = "", monthE = "", yearE = "";
        String t = e.getStartDate().toString();
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
        cnt = 0;
        t = e.getEndDate().toString();
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
        
        e.setStartS(dateS + "-" + monthS + "-" + yearS);
        e.setEndS(dateE + "-" + monthE + "-" + yearE);
        request.setAttribute("e", e);
        request.getRequestDispatcher("eventDetail.jsp").forward(request, response);
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
