/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Ticket;

/**
 *
 * @author acer
 */
public class allTicket extends HttpServlet {

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
            out.println("<title>Servlet allTicket</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet allTicket at " + request.getContextPath() + "</h1>");
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
        TicketDAO tkd = new TicketDAO();
        List<Ticket> list = tkd.getAllTicket();

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
                System.out.println("n2");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }

        int numPerPage = 20;
        int totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
        if (page > totalPage && !list.isEmpty()) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        if(request.getParameter("ms") != null) {
            request.setAttribute("ms", request.getParameter("ms"));
        }

        int start = (page - 1) * numPerPage;
        int end = (page * numPerPage > list.size()) ? (list.size() - 1) : (page * numPerPage - 1);
        request.setAttribute("list", tkd.getTicketByPage(list, start, end));
        request.setAttribute("page", page);
        request.setAttribute("start", start);
        request.setAttribute("totalPage", totalPage);
        request.getRequestDispatcher("allTicket.jsp").forward(request, response);
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
