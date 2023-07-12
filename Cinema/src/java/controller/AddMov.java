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
import java.util.ArrayList;
import java.util.List;
import model.MovieGenre;

/**
 *
 * @author acer
 */
public class AddMov extends HttpServlet {

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
            out.println("<title>Servlet AddMov</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddMov at " + request.getContextPath() + "</h1>");
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
        List<String> stt = new ArrayList<>();
        MovieDAO mvd = new MovieDAO();

        stt.add("Đang chiếu");
        stt.add("Chưa chiếu");
        stt.add("Dừng chiếu");
        List<MovieGenre> list = mvd.getAllGenre();
        request.setAttribute("list", list);
        request.setAttribute("stt", stt);
        request.getRequestDispatcher("addMov.jsp").forward(request, response);
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
        String id_raw = request.getParameter("id");
        String[] dir = request.getParameterValues("dir");
        String[] star = request.getParameterValues("star");
        String[] genre_raw = request.getParameterValues("genre");
        String name = request.getParameter("name");
        String startDate_raw = request.getParameter("startDate");
        String time_raw = request.getParameter("time");
        String lang = request.getParameter("lang");
        String org = request.getParameter("org");
        String stt = request.getParameter("stt");
        String studio = request.getParameter("studio");
        String img = request.getParameter("img");
        String note = request.getParameter("note");
        String gN_raw = request.getParameter("gN");
        String endDate_raw = request.getParameter("endDate");
        int gN = Integer.parseInt(gN_raw);
        Double time = Double.parseDouble(time_raw);
        int id = Integer.parseInt(id_raw);
        int genre[] = new int[gN + 1];
        MovieGenre genreName[] = new MovieGenre[gN + 1];

        MovieDAO mvd = new MovieDAO();
        if (mvd.checkID(id) == null) {
            String ms = "Add thành công";
            mvd.insertMovie(id, name, Date.valueOf(startDate_raw), time, lang, org, 0, note, stt, studio, img, Date.valueOf(endDate_raw));
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
            request.setAttribute("ms", ms);
            request.setAttribute("msT", ms);
            request.setAttribute("genre", genreName);
            request.setAttribute("dir", dir);
            request.setAttribute("star", star);
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("startDate", Date.valueOf(startDate_raw));
            request.setAttribute("time", time);
            request.setAttribute("lang", lang);
            request.setAttribute("org", org);
            request.setAttribute("stt", stt);
            request.setAttribute("studio", studio);
            request.setAttribute("img", img);
            request.setAttribute("note", note);
            request.getRequestDispatcher("addMov.jsp").forward(request, response);
        } else {
            String ms = "ID đã tồn tại";
            List<MovieGenre> list = mvd.getAllGenre();
            request.setAttribute("list", list);
            request.setAttribute("ms", ms);
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("startDate", Date.valueOf(startDate_raw));
            request.setAttribute("time", time);
            request.setAttribute("lang", lang);
            request.setAttribute("org", org);
            request.setAttribute("stt", stt);
            request.setAttribute("studio", studio);
            request.setAttribute("img", img);
            request.setAttribute("note", note);
            request.getRequestDispatcher("addMov.jsp").forward(request, response);
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
