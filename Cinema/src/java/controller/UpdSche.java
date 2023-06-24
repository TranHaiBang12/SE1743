/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
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
import java.sql.Time;
import model.Schedule;

/**
 *
 * @author acer
 */
public class UpdSche extends HttpServlet {

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
            out.println("<title>Servlet UpdSche</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdSche at " + request.getContextPath() + "</h1>");
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
        ScheDAO sd = new ScheDAO();
        MovieDAO mvd = new MovieDAO();
        String id = request.getParameter("id");
        Schedule s = sd.getScheduleByID(id);
        CinemaDAO cnd = new CinemaDAO();
        FormDAO fd = new FormDAO();
        RoomDAO rd = new RoomDAO();
        if (s == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            request.setAttribute("id", id);
            request.setAttribute("movie", mvd.getMovieById(s.getMovID()));
            request.setAttribute("allmov", mvd.getAllMovies());
            request.setAttribute("cin", cnd.getAllCinema());
            request.setAttribute("form", fd.getAllForm());
            request.setAttribute("room", rd.getAllRoomByCinID(s.getCinID()));
            request.setAttribute("cinP", cnd.getCinemaByID(s.getCinID()));
            request.setAttribute("roomP", rd.getRoomByRoomIDAndCinID(s.getRoomID(), s.getCinID()));
            request.setAttribute("formP", fd.getFormById(s.getFormID()));
            request.setAttribute("s", s);
            request.getRequestDispatcher("updSche.jsp").forward(request, response);
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
        if (request.getParameter("check").equals("0")) {
            CinemaDAO cnd = new CinemaDAO();
            FormDAO fmd = new FormDAO();
            String id = request.getParameter("id");
            String startDate_raw = request.getParameter("startDate");
            String startTime_raw = request.getParameter("startTime");
            String movID_raw = request.getParameter("mov");
            String formID_raw = request.getParameter("form");
            String cinID_raw = request.getParameter("cin");
            int cinID = Integer.parseInt(cinID_raw);
            String roomID_raw = request.getParameter("room");

            Date startDate = Date.valueOf(startDate_raw);
            System.out.println(startDate);
            System.out.println(startTime_raw);
            Time t;
            t = Time.valueOf(startTime_raw + ":00");
            System.out.println(movID_raw);
            int movID = 0, formID = 0, roomID = 0;
            try {
                movID = Integer.parseInt(movID_raw);
                formID = Integer.parseInt(formID_raw);
                roomID = Integer.parseInt(roomID_raw);
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            MovieDAO mvd = new MovieDAO();
            double tgianChieu = mvd.getMovieById(movID).getTime();
            String tgianChieu_raw = "";
            int h = (int) (tgianChieu / 60);
            int m = (int) (tgianChieu % 60);
            int ss = (int) ((tgianChieu - Math.floor(tgianChieu)) * 60);
            if (h < 10) {
                tgianChieu_raw += "0";
                tgianChieu_raw += h;
            } else {
                tgianChieu_raw += h;
            }
            if (m < 10) {
                tgianChieu_raw += ":";
                tgianChieu_raw += "0";
                tgianChieu_raw += m;
            } else {
                tgianChieu_raw += ":";
                tgianChieu_raw += m;
            }
            if (ss < 10) {
                tgianChieu_raw += ":";
                tgianChieu_raw += "0";
                tgianChieu_raw += ss;
            } else {
                tgianChieu_raw += ":";
                tgianChieu_raw += ss;
            }

            int min = (int) tgianChieu;
            Time tme = Time.valueOf(tgianChieu_raw);
            ScheDAO sd = new ScheDAO();
            Schedule s = sd.getScheduleByID(id);
            RoomDAO rd = new RoomDAO();
            try {
                System.out.println(id + " " + movID + " " + startDate + " " + t + " " + roomID + " " + cinID + " " + min);
                if (sd.getScheduleUPDByIn4(id, movID, startDate, t, roomID, cinID, min) != null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.setAttribute("movie", mvd.getMovieById(movID));
                request.setAttribute("allmov", mvd.getAllMovies());
                request.setAttribute("ms", "Đã tồn tại một lịch chiếu trong khung giờ này, vui lòng chuyển sang một khung giờ khác");
                request.setAttribute("cinP", cnd.getCinemaByID(cinID));
                request.setAttribute("room", rd.getAllRoomByCinID(cinID));
                request.setAttribute("roomP", rd.getRoomByRoomIDAndCinID(roomID, cinID));
                request.setAttribute("form", fmd.getAllForm());
                request.setAttribute("formP", fmd.getFormById(formID));
                request.setAttribute("id", id);
                request.setAttribute("s", s);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("updSche.jsp").forward(request, response);
            }
            String endDate = "";
            String endTime = "";
            String en = sd.convertTime(startDate_raw, startTime_raw, min);
            for (int i = 0; i < en.length(); i++) {
                if (en.charAt(i) == ' ') {
                    endDate = en.substring(0, i);
                    endTime = en.substring(i + 1);
                }
            }
            sd.updSchedule(id, movID, formID, cinID, roomID, startDate, t, Date.valueOf(endDate), Time.valueOf(endTime));
            request.setAttribute("ms", "Update thành công");
            request.setAttribute("movie", mvd.getMovieById(movID));
            request.setAttribute("allmov", mvd.getAllMovies());
            request.setAttribute("cinP", cnd.getCinemaByID(cinID));
            request.setAttribute("room", rd.getAllRoomByCinID(cinID));
            request.setAttribute("roomP", rd.getRoomByRoomIDAndCinID(roomID, cinID));
            request.setAttribute("form", fmd.getAllForm());
            request.setAttribute("formP", fmd.getFormById(formID));
            request.setAttribute("id", id);
            request.setAttribute("s", s);
            request.setAttribute("cin", cnd.getAllCinema());
            request.getRequestDispatcher("updSche.jsp").forward(request, response);
        } else if (request.getParameter("check").equals("1")) {
            String cinID_raw = request.getParameter("cin");
            int cinID = Integer.parseInt(cinID_raw);
            ScheDAO sd = new ScheDAO();
            MovieDAO mvd = new MovieDAO();
            String id = request.getParameter("id");
            Schedule s = sd.getScheduleByID(id);
            CinemaDAO cnd = new CinemaDAO();
            FormDAO fd = new FormDAO();
            RoomDAO rd = new RoomDAO();
            if (s == null) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {
                System.out.println(id);
                request.setAttribute("id", id);
                request.setAttribute("movie", mvd.getMovieById(s.getMovID()));
                request.setAttribute("allmov", mvd.getAllMovies());
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("form", fd.getAllForm());
                request.setAttribute("room", rd.getAllRoomByCinID(cinID));
                request.setAttribute("cinP", cnd.getCinemaByID(cinID));
                request.setAttribute("roomP", rd.getRoomByRoomIDAndCinID(s.getRoomID(), s.getCinID()));
                request.setAttribute("formP", fd.getFormById(s.getFormID()));
                request.setAttribute("s", s);
                request.getRequestDispatcher("updSche.jsp").forward(request, response);
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
