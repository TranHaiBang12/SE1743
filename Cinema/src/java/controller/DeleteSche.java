/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
public class DeleteSche extends HttpServlet {

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
            out.println("<title>Servlet DeleteSche</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteSche at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");

        ScheDAO sd = new ScheDAO();
        sd.deleteSchedule(id);
        TicketDAO tkd = new TicketDAO();
        Ticket tNM = tkd.getTicketBySchedule(id, "NM");
        Ticket tVP = tkd.getTicketBySchedule(id, "VP");
        Ticket tVT = tkd.getTicketBySchedule(id, "VT");
        if (tNM != null) {
            tkd.deleteTicketByID(tNM.getProductCode());
        }
        if (tVP != null) {
            tkd.deleteTicketByID(tVP.getProductCode());
        }
        if (tVT != null) {
            tkd.deleteTicketByID(tVT.getProductCode());
        }
        //response.sendRedirect("home");
        int movID = Integer.parseInt(request.getParameter("movid"));
//        request.setAttribute("id", Integer.parseInt(request.getParameter("movid")));
//        request.getRequestDispatcher("viewsche").forward(request, response);
        response.sendRedirect("viewsche?id=" + movID);
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
