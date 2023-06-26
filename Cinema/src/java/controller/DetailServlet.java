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
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import model.Account;
import model.DirectorInMov;
import model.MovieGenre;
import model.Movies;
import model.Rate;
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
        System.out.println("4");
        String id_raw = request.getParameter("id");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        System.out.println(id_raw);
        try {
            int id = Integer.parseInt(id_raw);
            RateDAO rd = new RateDAO();
            int stat;
            if (a != null) {
                if (rd.checkAccountRateByMovID(a.getUserName(), id) != null) {
                    stat = 1;
                } else {
                    stat = 0;
                }

                MovieDAO mvd = new MovieDAO();
                String pattern = "dd-MM-yyyy";
                DiStaGenreMovDAO dsgm = new DiStaGenreMovDAO();
                DirectorInMov dim = dsgm.getAllDirectorByMovID(id);
                String dir = "", star = "", genre = "";
                for (int i = 0; i < dim.getDirectorName().size(); i++) {
                    dir += dim.getDirectorName().get(i);
                    if (i != dim.getDirectorName().size() - 1) {
                        dir += ", ";
                    }
                }
                StarInMov sim = dsgm.getAllStarByMovID(id);
                for (int i = 0; i < sim.getStarName().size(); i++) {
                    star += sim.getStarName().get(i);
                    if (i != sim.getStarName().size() - 1) {
                        star += ", ";
                    }
                }
                MovieGenre mv = dsgm.getAllGenreByMovID(id);
                for (int i = 0; i < mv.getGenreName().size(); i++) {
                    genre += mv.getGenreName().get(i);
                    if (i != mv.getGenreName().size() - 1) {
                        genre += ", ";
                    }
                }
                String page_raw = request.getParameter("page");
                List<Rate> r = rd.getAllRate(id);

                int page = 1;
                if (page_raw != null) {
                    page = Integer.parseInt(page_raw);
                }
                int numPerPage = 5;
                int totalPage = (r.size() % numPerPage == 0) ? (r.size() / numPerPage) : (r.size() / numPerPage + 1);
                int start = (page - 1) * 5;
                int end = (page == totalPage) ? (r.size() - 1) : (page * numPerPage - 1);
                Movies m = mvd.getMovieById(id);
                int noRate = rd.getNoRate(id);
                int noRate5 = rd.getNoRate5(id);
                int noRate4 = rd.getNoRate4(id);
                int noRate3 = rd.getNoRate3(id);
                int noRate2 = rd.getNoRate2(id);
                int noRate1 = rd.getNoRate1(id);
                int sumRate = rd.getSumRate(id);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                double avr = (double) (sumRate) / (double) noRate;
                request.setAttribute("listPerPage", rd.getRateByPage(r, start, end));
                request.setAttribute("page", page);
                request.setAttribute("noRate5", decimalFormat.format((double) noRate5 / (double) noRate));
                request.setAttribute("noRate4", decimalFormat.format((double) noRate4 / (double) noRate));
                request.setAttribute("noRate3", decimalFormat.format((double) noRate3 / (double) noRate));
                request.setAttribute("noRate2", decimalFormat.format((double) noRate2 / (double) noRate));
                request.setAttribute("noRate1", decimalFormat.format((double) noRate1 / (double) noRate));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("noRate", noRate);
                request.setAttribute("avrRate", decimalFormat.format(avr));
                request.setAttribute("id", id);
                request.setAttribute("stat", stat);
                request.setAttribute("data", m);
                request.setAttribute("dir", dir);
                request.setAttribute("star", star);
                request.setAttribute("genre", genre);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            } else {
                MovieDAO mvd = new MovieDAO();
                String pattern = "dd-MM-yyyy";
                DiStaGenreMovDAO dsgm = new DiStaGenreMovDAO();
                DirectorInMov dim = dsgm.getAllDirectorByMovID(id);
                String dir = "", star = "", genre = "";
                for (int i = 0; i < dim.getDirectorName().size(); i++) {
                    dir += dim.getDirectorName().get(i);
                    if (i != dim.getDirectorName().size() - 1) {
                        dir += ", ";
                    }
                }
                StarInMov sim = dsgm.getAllStarByMovID(id);
                for (int i = 0; i < sim.getStarName().size(); i++) {
                    star += sim.getStarName().get(i);
                    if (i != sim.getStarName().size() - 1) {
                        star += ", ";
                    }
                }
                MovieGenre mv = dsgm.getAllGenreByMovID(id);
                for (int i = 0; i < mv.getGenreName().size(); i++) {
                    genre += mv.getGenreName().get(i);
                    if (i != mv.getGenreName().size() - 1) {
                        genre += ", ";
                    }
                }
                String page_raw = request.getParameter("page");
                List<Rate> r = rd.getAllRate(id);

                int page = 1;
                if (page_raw != null) {
                    page = Integer.parseInt(page_raw);
                }
                int numPerPage = 5;
                int totalPage = (r.size() % numPerPage == 0) ? (r.size() / numPerPage) : (r.size() / numPerPage + 1);
                int start = (page - 1) * 5;
                int end = (page == totalPage) ? (r.size() - 1) : (page * numPerPage - 1);
                int noRate = rd.getNoRate(id);
                int noRate5 = rd.getNoRate5(id);
                int noRate4 = rd.getNoRate4(id);
                int noRate3 = rd.getNoRate3(id);
                int noRate2 = rd.getNoRate2(id);
                int noRate1 = rd.getNoRate1(id);
                int sumRate = rd.getSumRate(id);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                double avr = (double) (sumRate) / (double) noRate;
                request.setAttribute("listPerPage", rd.getRateByPage(r, start, end));
                request.setAttribute("page", page);
                request.setAttribute("noRate5", decimalFormat.format((double) noRate5 / (double) noRate));
                request.setAttribute("noRate4", decimalFormat.format((double) noRate4 / (double) noRate));
                request.setAttribute("noRate3", decimalFormat.format((double) noRate3 / (double) noRate));
                request.setAttribute("noRate2", decimalFormat.format((double) noRate2 / (double) noRate));
                request.setAttribute("noRate1", decimalFormat.format((double) noRate1 / (double) noRate));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("noRate", noRate);
                request.setAttribute("avrRate", decimalFormat.format(avr));
                Movies m = mvd.getMovieById(id);
                request.setAttribute("id", id);
                request.setAttribute("data", m);
                request.setAttribute("dir", dir);
                request.setAttribute("star", star);
                request.setAttribute("genre", genre);
                request.getRequestDispatcher("detail.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        String star_raw = request.getParameter("star");
        String name = request.getParameter("name1");
        String cmt = request.getParameter("cmt");
        String anoy = request.getParameter("anoy");
        RateDAO rd = new RateDAO();
        if (anoy != null && anoy.equals("on")) {
            name = "Khách hàng";
            int star = Integer.parseInt(star_raw);
            int movID = Integer.parseInt(request.getParameter("movID"));
            rd.insertRate(a.getUserName(), movID, cmt, star, "Chờ duyệt", name);
        } else {
            int star = Integer.parseInt(star_raw);
            int movID = Integer.parseInt(request.getParameter("movID"));
            rd.insertRate(a.getUserName(), movID, cmt, star, "Chờ duyệt", name);
        }
        request.setAttribute("stat", 1);
        String id_raw = request.getParameter("movID");
        try {
            int id = Integer.parseInt(id_raw);
            MovieDAO mvd = new MovieDAO();
            String pattern = "dd-MM-yyyy";
            DiStaGenreMovDAO dsgm = new DiStaGenreMovDAO();
            DirectorInMov dim = dsgm.getAllDirectorByMovID(id);
            String dir = "", star = "", genre = "";
            for (int i = 0; i < dim.getDirectorName().size(); i++) {
                dir += dim.getDirectorName().get(i);
                if (i != dim.getDirectorName().size() - 1) {
                    dir += ", ";
                }
            }
            StarInMov sim = dsgm.getAllStarByMovID(id);
            for (int i = 0; i < sim.getStarName().size(); i++) {
                star += sim.getStarName().get(i);
                if (i != sim.getStarName().size() - 1) {
                    star += ", ";
                }
            }
            MovieGenre mv = dsgm.getAllGenreByMovID(id);
            for (int i = 0; i < mv.getGenreName().size(); i++) {
                genre += mv.getGenreName().get(i);
                if (i != mv.getGenreName().size() - 1) {
                    genre += ", ";
                }
            }
            Movies m = mvd.getMovieById(id);
            request.setAttribute("ms", "Đánh giá của bạn đang được nhân viên duyệt");
            request.setAttribute("id", id);
            request.setAttribute("data", m);
            request.setAttribute("dir", dir);
            request.setAttribute("star", star);
            request.setAttribute("genre", genre);
            request.getRequestDispatcher("detail.jsp").forward(request, response);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
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
