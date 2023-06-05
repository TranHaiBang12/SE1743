/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FormDAO;
import dal.MovieDAO;
import dal.RoomDAO;
import dal.ScheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author acer
 */
public class PickSeatServlet extends HttpServlet {

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
            out.println("<title>Servlet PickSeatServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PickSeatServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        ScheDAO scd = new ScheDAO();
        System.out.println(scd.getScheduleByID(id));
        if (scd.getScheduleByID(id) == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            FormDAO fmd = new FormDAO();
            MovieDAO mvd = new MovieDAO();
            RoomDAO rmd = new RoomDAO();

            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(scd.getScheduleByID(id).getStart());

            String day = "";
            switch (scd.getScheduleByID(id).getStart().getDay()) {
                case 0:
                    day = "Chủ Nhật";
                    break;
                case 1:
                    day = "Thứ Hai";
                    break;
                case 2:
                    day = "Thứ Ba";
                    break;
                case 3:
                    day = "Thứ Tư";
                    break;
                case 4:
                    day = "Thứ Năm";
                    break;
                case 5:
                    day = "Thứ Sáu";
                    break;
                case 6:
                    day = "Thứ Bảy";
                    break;
            }
            String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
            String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
            request.setAttribute("sche", scd.getScheduleByID(id));
            request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
            request.setAttribute("movName", movName);
            request.setAttribute("formName", formName);
            request.setAttribute("dateFormat", date);
            request.setAttribute("day", day);
            request.getRequestDispatcher("pickSeat.jsp").forward(request, response);
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
