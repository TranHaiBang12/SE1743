/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiStaGenreMovDAO;
import dal.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import model.DirectorInMov;
import model.MovieGenre;
import model.Movies;
import model.StarInMov;

/**
 *
 * @author acer
 */
public class DetailServlet extends HttpServlet {

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
            out.println("<title>Servlet DetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DetailServlet at " + request.getContextPath() + "</h1>");
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
        try {
            int id = Integer.parseInt(id_raw);
            MovieDAO mvd = new MovieDAO();
            String pattern = "dd-MM-yyyy";
            DiStaGenreMovDAO dsgm = new DiStaGenreMovDAO();
            DirectorInMov dim = dsgm.getAllDirectorByMovID(id);
            String dir = "", star = "", genre = "";
            for (int i = 0; i < dim.getDirectorName().size(); i++) {
                dir += dim.getDirectorName().get(i);
                if(i != dim.getDirectorName().size() - 1) {
                    dir += ", ";
                }
            }
            StarInMov sim = dsgm.getAllStarByMovID(id);
            for (int i = 0; i < sim.getStarName().size(); i++) {
                star += sim.getStarName().get(i);
                if(i != sim.getStarName().size() - 1) {
                    star += ", ";
                }
            }
            MovieGenre mv = dsgm.getAllGenreByMovID(id);
            for (int i = 0; i < mv.getGenreName().size(); i++) {
                genre += mv.getGenreName().get(i);
                if(i != mv.getGenreName().size() - 1) {
                    genre += ", ";
                }
            }
            Movies m = mvd.getMovieById(id);
            request.setAttribute("id", id);
            request.setAttribute("data", m);
            request.setAttribute("dir", dir);
            request.setAttribute("star", star);
            request.setAttribute("genre", genre);
            request.getRequestDispatcher("detail.jsp").forward(request, response);
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
