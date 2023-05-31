/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import model.Movies;

/**
 *
 * @author acer
 */
public class UpdateServlet extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UpdateServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        request.getRequestDispatcher("update.jsp").forward(request, response);
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
        try {
            int id = Integer.parseInt(id_raw);
            MovieDAO mvd = new MovieDAO();
            Movies m = mvd.getMovieById(id);
            request.setAttribute("data", m);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        } catch (Exception e) {
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
        //processRequest(request, response);
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        String startdate = request.getParameter("startdate");
        String time_raw = request.getParameter("time");
        String lang = request.getParameter("lang");
        String org = request.getParameter("org");
        String status = request.getParameter("status");
        String studio = request.getParameter("studio");
        String img = request.getParameter("img");
        Date std;
        int id = 0;
        double time = 0;
        try {
            id = Integer.parseInt(id_raw);
            if (id <= 0) {
                throw new Exception("Loi ms");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error").forward(request, response);
        }

        try {
            std = Date.valueOf(startdate);
        } catch (Exception e) {
            String ms = "Vui lòng nhập đúng format của ngày khởi chiếu: dd/mm/yyyy";
            MovieDAO mvd = new MovieDAO();
            Movies m = mvd.getMovieById(id);
            request.setAttribute("data", m);
            request.setAttribute("ms", ms);
            processRequest(request, response);
        }
        try {
            time = Double.parseDouble(time_raw);
            if (time <= 0) {
                throw new Exception("Loi ms");
            }
        } catch (Exception e) {
            String ms = "Thời lượng phải lớn hơn 0";
            MovieDAO mvd = new MovieDAO();
            Movies m = mvd.getMovieById(id);
            request.setAttribute("data", m);
            request.setAttribute("ms", ms);
            processRequest(request, response);
        }
        if (status.equals("Đang chiếu") == false && status.equals("Dừng chiếu") == false && status.equals("Sắp chiếu") == false) {
            String ms = "Vui lòng nhập đúng tình trạng của phim (Dừng chiếu, đang chiếu hoặc sắp chiếu)";
            request.setAttribute("ms", ms);
            MovieDAO mvd = new MovieDAO();
            Movies m = mvd.getMovieById(id);
            request.setAttribute("data", m);
            processRequest(request, response);
        }

        String ms = "Update Successfull";
        request.setAttribute("ms", ms);
        MovieDAO mvd = new MovieDAO();
        
        mvd.updateInfo(id, name, startdate, time, lang, org, status, studio, img);
        Movies m = mvd.getMovieById(id);
        request.setAttribute("data", m);
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
