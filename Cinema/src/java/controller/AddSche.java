/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.FormDAO;
import dal.ScheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Schedule;

/**
 *
 * @author acer
 */
public class AddSche extends HttpServlet {

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
            out.println("<title>Servlet AddSche</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSche at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        List<Schedule> s = new ArrayList<>();
        ScheDAO sd = new ScheDAO();
        int id = 0;

        try {
            id = Integer.parseInt(id_raw);
            s = sd.getAllScheduleByMovID(id);
            if (s.isEmpty()) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        String page_raw = request.getParameter("page");
        int page = 0;
        if (page_raw == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(page_raw);
                if (page <= 0) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        int numPerPage = 20;
        int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
        if (page > totalPage) {

            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        int start = (page - 1) * numPerPage;
        int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
        FormDAO fmd = new FormDAO();
        CinemaDAO cnd = new CinemaDAO();
        request.setAttribute("form", fmd.getAllForm());
        request.setAttribute("cin", cnd.getAllCinema());
        request.setAttribute("id", id);
        request.setAttribute("s", sd.getScheduleByPage(s, start, end));
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("page", page);
        request.getRequestDispatcher("addSche.jsp").forward(request, response);
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
        String scheNo = request.getParameter("scheNo");
        String startDate_raw = request.getParameter("startDate");
        String startTime_raw = request.getParameter("startTime");
        String movID_raw = request.getParameter("movID");
        String formID_raw = request.getParameter("form");
        String cinID_raw = request.getParameter("cin");
        
        System.out.println(scheNo + " " + startDate_raw + " " + startTime_raw + " " + movID_raw + " " + formID_raw + " " + cinID_raw);
                
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
