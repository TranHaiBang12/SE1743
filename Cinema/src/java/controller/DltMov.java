/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiStaGenreMovDAO;
import dal.MovieDAO;
import dal.ScheDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ticket;

/**
 *
 * @author acer
 */
public class DltMov extends HttpServlet {

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
            out.println("<title>Servlet DltMov</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DltMov at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        MovieDAO mvd = new MovieDAO();
        DiStaGenreMovDAO dsg = new DiStaGenreMovDAO();
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            if (mvd.getMovieById(id) == null) {
                throw new Exception("L");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        ScheDAO sd = new ScheDAO();

        TicketDAO tkd = new TicketDAO();
        if (tkd.getAllMovSellTicketByMovID(id).isEmpty()) {
            tkd.dltAllTicket(id);
            //response.sendRedirect("home");
            sd.dltAllScheOfMov(id);
            dsg.dltAllDir(id);
            dsg.dltAllGenre(id);
            dsg.dltAllStar(id);
            mvd.dltMovByID(id);
//        request.setAttribute("id", Integer.parseInt(request.getParameter("movid")));
//        request.getRequestDispatcher("viewsche").forward(request, response);
            response.sendRedirect("home");
        }
        else {
            response.sendRedirect("home?ms=" + "Bộ phim đã bán vé, không thể xóa");
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
