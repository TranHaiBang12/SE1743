/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiStaGenreMovDAO;
import dal.MovieDAO;
import dal.RateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import model.DirectorInMov;
import model.MovieGenre;
import model.Movies;
import model.Rate;
import model.StarInMov;

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
            List<MovieGenre> list = mvd.getAllGenre();
            request.setAttribute("list", list);
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
        System.out.println("1");
                
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
            request.getRequestDispatcher("update.jsp").forward(request, response);
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
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }
        
        if (status.equals("Đang chiếu") == false && status.equals("Dừng chiếu") == false && status.equals("Sắp chiếu") == false) {
            String ms = "Vui lòng nhập đúng tình trạng của phim (Dừng chiếu, đang chiếu hoặc sắp chiếu)";
            request.setAttribute("ms", ms);
            MovieDAO mvd = new MovieDAO();
            Movies m = mvd.getMovieById(id);
            request.setAttribute("data", m);
            request.getRequestDispatcher("update.jsp").forward(request, response);
        }

        String ms = "Update Successfull";
        request.setAttribute("ms", ms);
        MovieDAO mvd = new MovieDAO();
        System.out.println("3");
        String[] dir = request.getParameterValues("dir");
        String[] star = request.getParameterValues("star");
        String[] genre_raw = request.getParameterValues("genre");

        String gN_raw = request.getParameter("gN");
        String endDate_raw = request.getParameter("endDate");
        int gN = Integer.parseInt(gN_raw);

        int genre[] = new int[gN + 1];
        MovieGenre genreName[] = new MovieGenre[gN + 1];
        DiStaGenreMovDAO dsgm = new DiStaGenreMovDAO();
        dsgm.dltAllDir(id);
        dsgm.dltAllGenre(id);
        dsgm.dltAllStar(id);
            for (int i = 0; i <= gN; i++) {
                genre[i] = Integer.parseInt(genre_raw[i]);
                mvd.insertGenre(id, genre[i]);
                genreName[i] = mvd.getGenreByID(genre[i]);
            }
            for (int i = 0; i < star.length; i++) {
                mvd.insertStar(id, star[i]);
            }
            for (int i = 0; i < dir.length; i++) {
                mvd.insertDirector(id, dir[i]);
            }

            mvd.updateInfo(id, name, startdate, time, lang, org, status, studio, img);
            Movies m = mvd.getMovieById(id);
            request.setAttribute("data", m);
            
            DirectorInMov dim = dsgm.getAllDirectorByMovID(id);
                String dir1 = "", star1 = "",genre1 = "";
                for (int i = 0; i < dim.getDirectorName().size(); i++) {
                    dir1 += dim.getDirectorName().get(i);
                    if (i != dim.getDirectorName().size() - 1) {
                        dir1 += ", ";
                    }
                }
                System.out.println("5");
                StarInMov sim = dsgm.getAllStarByMovID(id);
                for (int i = 0; i < sim.getStarName().size(); i++) {
                    star1 += sim.getStarName().get(i);
                    if (i != sim.getStarName().size() - 1) {
                        star1 += ", ";
                    }
                }
                MovieGenre mv = dsgm.getAllGenreByMovID(id);
                for (int i = 0; i < mv.getGenreName().size(); i++) {
                    genre1 += mv.getGenreName().get(i);
                    if (i != mv.getGenreName().size() - 1) {
                        genre1 += ", ";
                    }
                }
        
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
          
                
                request.setAttribute("id", id);
                request.setAttribute("data", m);
                request.setAttribute("dir", dir1);
                request.setAttribute("star", star1);
                request.setAttribute("genre", genre1);
                request.setAttribute("stt", 0);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
        
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
