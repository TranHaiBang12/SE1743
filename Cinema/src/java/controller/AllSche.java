/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.MovieDAO;
import dal.ScheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Schedule;

/**
 *
 * @author acer
 */
public class AllSche extends HttpServlet {

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
            out.println("<title>Servlet AllSche</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllSche at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("searchDate") == null) {
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();

            s = sd.getAllSchedule();
            List<String> scheHasTicket = sd.getAllScheduleHaveTicket();
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setHasTick(false);
                for (int j = 0; j < scheHasTicket.size(); j++) {
                    if (s.get(i).getScheNo().equals(scheHasTicket.get(j))) {
                        s.get(i).setHasTick(true);
                        break;
                    }
                }
            }
            List<String> scheHasSellTicket = sd.getAllScheduleHaveSellTicket();
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setHasSellTick(false);
                for (int j = 0; j < scheHasSellTicket.size(); j++) {
                    if (s.get(i).getScheNo().equals(scheHasSellTicket.get(j))) {
                        s.get(i).setHasSellTick(true);
                        break;
                    }
                }
            }

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
            int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
            if (page > totalPage && !s.isEmpty()) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

            int start = (page - 1) * numPerPage;
            int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
            CinemaDAO cnd = new CinemaDAO();
            MovieDAO mvd = new MovieDAO();
            if (s.isEmpty()) {
                request.setAttribute("ms", "Hiện chưa có bất kỳ lịch chiếu nào của bộ phim này");
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("mov", mvd.getAllAllMovies());
                request.getRequestDispatcher("allSche.jsp").forward(request, response);
            } else {

                request.setAttribute("mov", mvd.getAllAllMovies());
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("allSche.jsp").forward(request, response);
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
        if (request.getParameter("searchDate") != null && !request.getParameter("searchDate").equals("") && (request.getParameter("id") == null || request.getParameter("id").equals(""))) {
            String cin_raw = request.getParameter("cin");
            int cin = 0;
            if (cin_raw != null) {
                try {
                    cin = Integer.parseInt(cin_raw);
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            String date_raw = request.getParameter("searchDate");
            Date date = Date.valueOf(date_raw);
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();

            s = sd.getAllScheduleByDateAC(date, cin);

            List<String> scheHasTicket = sd.getAllScheduleHaveTicket();
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setHasTick(false);
                for (int j = 0; j < scheHasTicket.size(); j++) {
                    if (s.get(i).getScheNo().equals(scheHasTicket.get(j))) {
                        s.get(i).setHasTick(true);
                        break;
                    }
                }
            }

            List<String> scheHasSellTicket = sd.getAllScheduleHaveSellTicket();
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setHasSellTick(false);
                for (int j = 0; j < scheHasSellTicket.size(); j++) {
                    if (s.get(i).getScheNo().equals(scheHasSellTicket.get(j))) {
                        s.get(i).setHasSellTick(true);
                        break;
                    }
                }
            }
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
                    System.out.println("2");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            int numPerPage = 20;
            int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
            if (page > totalPage && !s.isEmpty()) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int start = (page - 1) * numPerPage;
            int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
            CinemaDAO cnd = new CinemaDAO();
            MovieDAO mvd = new MovieDAO();
            if (s.isEmpty()) {
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("ms", "Hiện chưa có bất kỳ lịch chiếu nào của bộ phim này");
                request.setAttribute("mov", mvd.getAllAllMovies());
                request.getRequestDispatcher("allSche.jsp").forward(request, response);
            } else {
                request.setAttribute("cinID", cin);
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("searchDate", date);
                request.setAttribute("mov", mvd.getAllAllMovies());
                request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("allSche.jsp").forward(request, response);
            }
        } else if (request.getParameter("searchDate") != null && (request.getParameter("id") == null || request.getParameter("id").equals(""))) {
            MovieDAO mvd = new MovieDAO();
            CinemaDAO cnd = new CinemaDAO();
            request.setAttribute("cin", cnd.getAllCinema());
            request.setAttribute("mov", mvd.getAllAllMovies());
            request.getRequestDispatcher("allSche.jsp").forward(request, response);
        } else if (request.getParameter("searchDate") != null && !request.getParameter("searchDate").equals("") && (!request.getParameter("id").equals(""))) {
            String date_raw = request.getParameter("searchDate");
            Date date = Date.valueOf(date_raw);
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();

            String id_raw = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(id_raw);
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            
            String cin_raw = request.getParameter("cin");
            int cin = 0;
            if(cin_raw != null) {
                try {
                    cin = Integer.parseInt(cin_raw);
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }

            s = sd.getAllScheduleByDateAMovAC(date, id, cin);
            List<String> scheHasTicket = sd.getAllScheduleHaveTicket();
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setHasTick(false);
                for (int j = 0; j < scheHasTicket.size(); j++) {
                    if (s.get(i).getScheNo().equals(scheHasTicket.get(j))) {
                        s.get(i).setHasTick(true);
                        break;
                    }
                }
            }

            List<String> scheHasSellTicket = sd.getAllScheduleHaveSellTicket();
            for (int i = 0; i < s.size(); i++) {
                s.get(i).setHasSellTick(false);
                for (int j = 0; j < scheHasSellTicket.size(); j++) {
                    if (s.get(i).getScheNo().equals(scheHasSellTicket.get(j))) {
                        s.get(i).setHasSellTick(true);
                        break;
                    }
                }
            }
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
                    System.out.println("2");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            int numPerPage = 20;
            int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
            if (page > totalPage && !s.isEmpty()) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int start = (page - 1) * numPerPage;
            int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
            CinemaDAO cnd = new CinemaDAO();
            if (s.isEmpty()) {
                MovieDAO mvd = new MovieDAO();
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("mov", mvd.getAllAllMovies());
                request.setAttribute("ms", "Hiện chưa có bất kỳ lịch chiếu nào của bộ phim này");
                request.getRequestDispatcher("allSche.jsp").forward(request, response);
            } else {
                MovieDAO mvd = new MovieDAO();
                request.setAttribute("id", id);
                request.setAttribute("cinID", cin);
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("mov", mvd.getAllAllMovies());
                request.setAttribute("searchDate", date);
                request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("allSche.jsp").forward(request, response);
            }

        } else {
            response.sendRedirect("allsche");
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
