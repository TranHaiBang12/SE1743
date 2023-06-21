/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.FormDAO;
import dal.RoomDAO;
import dal.ScheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Schedule;

/**
 *
 * @author acer
 */
public class AddSche extends HttpServlet {

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
            out.println("<title>Servlet AddSche</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddSche at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("addSche.jsp").forward(request, response);

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
        if (request.getParameter("room") == null) {
            String id_raw = request.getParameter("id");
            System.out.println(id_raw);
            List<Schedule> s = new ArrayList<>();
            ScheDAO sd = new ScheDAO();
            int id = 0;

            try {
                id = Integer.parseInt(id_raw);
                System.out.println(id);
                s = sd.getAllScheduleByMovID(id);
                if (s.isEmpty()) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                System.out.println("ea");
                request.getRequestDispatcher("error.jsp").forward(request, response);
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
                    System.out.println("ra");

                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            int numPerPage = 20;
            int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
            if (page > totalPage) {
                System.out.println(page + " " + totalPage);
                System.out.println("ia");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int start = (page - 1) * numPerPage;
            RoomDAO rd = new RoomDAO();

            int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
            CinemaDAO cnd = new CinemaDAO();
            FormDAO fmd = new FormDAO();
            System.out.println(request.getParameter("cin"));
            request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
            request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
            request.setAttribute("form", fmd.getAllForm());
            request.setAttribute("id", id);
            request.setAttribute("s", sd.getScheduleByPage(s, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("cin", cnd.getAllCinema());
            request.getRequestDispatcher("addSche.jsp").forward(request, response);
        } else {
            if (request.getParameter("check").equals("1")) {
                String id_raw = request.getParameter("id");
                System.out.println(id_raw);
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
                    System.out.println("pa");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
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
                        System.out.println("ha");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                }
                int numPerPage = 20;
                int totalPage = (s.size() % numPerPage == 0) ? (s.size() / numPerPage) : (s.size() / numPerPage + 1);
                if (page > totalPage) {
                    System.out.println("ma");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                int start = (page - 1) * numPerPage;
                RoomDAO rd = new RoomDAO();

                int end = (page * numPerPage > s.size()) ? (s.size() - 1) : (page * numPerPage - 1);
                CinemaDAO cnd = new CinemaDAO();
                FormDAO fmd = new FormDAO();
                System.out.println(request.getParameter("cin"));
                request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                request.setAttribute("form", fmd.getAllForm());
                request.setAttribute("id", id);
                request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("addSche.jsp").forward(request, response);
            } else {
                String scheNo = request.getParameter("scheNo");
                String startDate_raw = request.getParameter("startDate");
                String startTime_raw = request.getParameter("startTime");
                String movID_raw = request.getParameter("movID");
                String formID_raw = request.getParameter("form");
                String cinID_raw = request.getParameter("cin");
                String roomID_raw = request.getParameter("room");

                Date startDate = Date.valueOf(startDate_raw);
                System.out.println(startDate);
                Time t;
                t = Time.valueOf(startTime_raw);
                System.out.println(t);
                int movID = 0, formID = 0, cinID = 0;
                try {
                    movID = Integer.parseInt(movID_raw);
                    formID = Integer.parseInt(formID_raw);
                    cinID = Integer.parseInt(cinID_raw);
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                ScheDAO sd = new ScheDAO();
                try {
                    if (sd.getScheduleByIn4(scheNo, movID, startDate, t, formID, cinID) != null) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

                //sd.addSchedule(scheNo, movID, formID, cinID, formID, startDate, startTime, startDate, startTime);
//                String ms = "Thêm lịch chiếu thành công";
//                request.setAttribute("ms", ms);
//                request.getRequestDispatcher("addSche.jsp").forward(request, response);
            }
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
