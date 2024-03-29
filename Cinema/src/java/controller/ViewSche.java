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
public class ViewSche extends HttpServlet {

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
            out.println("<title>Servlet ViewSche</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewSche at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("searchDate") == null) {
            String id_raw = request.getParameter("id");
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();
            int id = 0;
           
            try {
                id = Integer.parseInt(id_raw);
                
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
             s = sd.getAllScheduleByMovID(id);
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
            for (int i = 0; i < s.size(); i++) {
                System.out.println(s.get(i).isHasSellTick());
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
            System.out.println(s.size());
            CinemaDAO cnd = new CinemaDAO();
            MovieDAO mvd = new MovieDAO();
            if (s.isEmpty()) {
                request.setAttribute("id", id);
                request.setAttribute("mname", mvd.getMovieById(id).getMovName());
                request.setAttribute("ms", "Hiện chưa có bất kỳ lịch chiếu nào của bộ phim này");
                request.getRequestDispatcher("viewSche.jsp").forward(request, response);
            } else {
                request.setAttribute("id", id);
                request.setAttribute("mname", mvd.getMovieById(id).getMovName());
                request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("viewSche.jsp").forward(request, response);
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
        if (request.getParameter("searchDate") != null && !request.getParameter("searchDate").equals("")) {
            String date_raw = request.getParameter("searchDate");
            Date date = Date.valueOf(date_raw);
            String id_raw = request.getParameter("id");
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();
            int id = 0;

            try {
                id = Integer.parseInt(id_raw);
                s = sd.getAllScheduleByDateAMov(date, id);
                if (s.isEmpty()) {

                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                System.out.println("3");
                        
                request.setAttribute("id", id);
                request.setAttribute("ms", "Không có bất kỳ lịch chiếu nào của bộ phim này trong ngày bạn lựa chọn");
                request.getRequestDispatcher("viewSche.jsp").forward(request, response);
            }
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
            if (page > totalPage) {

                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int start = (page - 1) * numPerPage;
            int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
            CinemaDAO cnd = new CinemaDAO();
            request.setAttribute("searchDate", date);
            request.setAttribute("id", id);
            request.setAttribute("s", sd.getScheduleByPage(s, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("cin", cnd.getAllCinema());
            request.getRequestDispatcher("viewSche.jsp").forward(request, response);
        } else {
            String id_raw = request.getParameter("id");
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();
            int id = 0;

            try {
                id = Integer.parseInt(id_raw);
                s = sd.getAllScheduleByMovID(id);
                if (s.isEmpty()) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
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
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            int numPerPage = 20;
            int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
            if (page > totalPage) {

                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int start = (page - 1) * numPerPage;
            int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
            CinemaDAO cnd = new CinemaDAO();
            request.setAttribute("id", id);
            request.setAttribute("s", sd.getScheduleByPage(s, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("cin", cnd.getAllCinema());
            request.getRequestDispatcher("viewSche.jsp").forward(request, response);
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
