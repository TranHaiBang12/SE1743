/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.RateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Rate;

/**
 *
 * @author acer
 */
public class SeeAllRate extends HttpServlet {

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
            out.println("<title>Servlet SeeAllRate</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SeeAllRate at " + request.getContextPath() + "</h1>");
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
        RateDAO rd = new RateDAO();

        String page_raw = request.getParameter("page");
        String type_raw = request.getParameter("type");
        int type = 1;
        if (type_raw != null) {
            type = Integer.parseInt(type_raw);
        }

        if (type == 1) {
            List<Rate> list = rd.getAllAllRate();
            int page = 1;
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
            int numPerPage = 5;
            int totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
            int startP = (page - 1) * 5;
            int endP = (page == totalPage) ? (list.size() - 1) : (page * numPerPage - 1);
            if (!rd.getRateByPage(list, startP, endP).isEmpty()) {
                request.setAttribute("type", type);
                request.setAttribute("listPerPage", rd.getRateByPage(list, startP, endP));
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.getRequestDispatcher("seeAllRate.jsp").forward(request, response);
            } else {
                request.setAttribute("ms", "Không có bình luận nào");
                request.getRequestDispatcher("seeAllRate.jsp").forward(request, response);
            }
        } else if (type == 2) {
            List<Rate> list = rd.getAllAllRate("Được duyệt");
            int page = 1;
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
            int numPerPage = 5;
            int totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
            int startP = (page - 1) * 5;
            int endP = (page == totalPage) ? (list.size() - 1) : (page * numPerPage - 1);
            if (!rd.getAllAllRate("Được duyệt").isEmpty()) {
                request.setAttribute("type", type);
                request.setAttribute("listPerPage", rd.getRateByPage(rd.getAllAllRate("Được duyệt"), startP, endP));
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.getRequestDispatcher("seeAllRate.jsp").forward(request, response);
            } else {
                request.setAttribute("ms", "Không có bình luận nào được duyệt");
                request.getRequestDispatcher("seeAllRate.jsp").forward(request, response);
            }
        } else if (type == 3) {
            List<Rate> list = rd.getAllAllRate("Chờ duyệt");
            int page = 1;
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
            int numPerPage = 5;
            int totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
            int startP = (page - 1) * 5;
            int endP = (page == totalPage) ? (list.size() - 1) : (page * numPerPage - 1);
            request.setAttribute("type", type);
            if (!rd.getAllAllRate("Chờ duyệt").isEmpty()) {
                request.setAttribute("listPerPage", rd.getRateByPage(rd.getAllAllRate("Chờ duyệt"), startP, endP));
                request.setAttribute("page", page);
                request.setAttribute("totalPage", totalPage);
                request.getRequestDispatcher("seeAllRate.jsp").forward(request, response);
            } else {
                request.setAttribute("ms", "Không có bình luận nào chưa được duyệt");
                request.getRequestDispatcher("seeAllRate.jsp").forward(request, response);
            }
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
