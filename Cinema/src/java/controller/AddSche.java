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
import java.time.LocalDate;
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
        CinemaDAO cnd = new CinemaDAO();
        if (!s.isEmpty()) {
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

            request.setAttribute("id", id);
            request.setAttribute("s", sd.getScheduleByPage(s, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("cin", cnd.getAllCinema());
        } else {
            MovieDAO mvd = new MovieDAO();
            request.setAttribute("movName", mvd.getMovieById(id).getMovName());
            request.setAttribute("id", id);
            request.setAttribute("cin", cnd.getAllCinema());
        }
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

            } catch (Exception e) {
                System.out.println("ea");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            if (!s.isEmpty()) {
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
                CinemaDAO cnd = new CinemaDAO();
                RoomDAO rd = new RoomDAO();
                FormDAO fmd = new FormDAO();
                MovieDAO mvd = new MovieDAO();
                request.setAttribute("movName", mvd.getMovieById(id).getMovName());
                request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                request.setAttribute("form", fmd.getAllForm());
                request.setAttribute("id", id);
                request.setAttribute("cin", cnd.getAllCinema());
                request.getRequestDispatcher("addSche.jsp").forward(request, response);
            }
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

                } catch (Exception e) {
                    System.out.println("pa");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                if (!s.isEmpty()) {
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
                    CinemaDAO cnd = new CinemaDAO();
                    FormDAO fmd = new FormDAO();
                    RoomDAO rd = new RoomDAO();
                    MovieDAO mvd = new MovieDAO();
                    request.setAttribute("movName", mvd.getMovieById(id).getMovName());
                    System.out.println(request.getParameter("cin"));
                    request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                    request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                    request.setAttribute("form", fmd.getAllForm());
                    request.setAttribute("id", id);

                    request.setAttribute("cin", cnd.getAllCinema());
                    request.getRequestDispatcher("addSche.jsp").forward(request, response);
                }
            } else {
                String id_raw = request.getParameter("id");
                System.out.println(id_raw);
                List<Schedule> s = new ArrayList<>();
                ScheDAO sd = new ScheDAO();
                int id = 0;

                try {
                    id = Integer.parseInt(id_raw);
                    System.out.println(id);
                    s = sd.getAllScheduleByMovID(id);

                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                if (!s.isEmpty()) {
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
                    String scheNo = request.getParameter("scheNo");
                    String startDate_raw = request.getParameter("startDate");
                    String startTime_raw = request.getParameter("startTime");
                    String movID_raw = request.getParameter("movID");
                    String formID_raw = request.getParameter("form");
                    String cinID_raw = request.getParameter("cin");
                    String roomID_raw = request.getParameter("room");

                    Date startDate = Date.valueOf(startDate_raw);
                    System.out.println(startDate);
                    System.out.println(startTime_raw);
                    Time t;
                    t = Time.valueOf(startTime_raw + ":00");
                    System.out.println(t);
                    int movID = 0, formID = 0, cinID = 0, roomID = 0;
                    try {
                        movID = Integer.parseInt(movID_raw);
                        formID = Integer.parseInt(formID_raw);
                        cinID = Integer.parseInt(cinID_raw);
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
                    int p = 0;
                    int min = (int) tgianChieu;
                    System.out.println(min);
                    Time tme = Time.valueOf(tgianChieu_raw);
                    System.out.println(tgianChieu_raw);

                    try {
                        if (sd.getScheduleByIn4(scheNo, movID, startDate, t, roomID, cinID, min) != null) {
                            throw new Exception("Loi");
                        }
                    } catch (Exception e) {
                        System.out.println("5");
                        p = 1;
                        request.setAttribute("ms", "Đã tồn tại một lịch chiếu trong khung giờ này, vui lòng chuyển sang một khung giờ khác");
                        request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                        request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                        request.setAttribute("form", fmd.getAllForm());
                        request.setAttribute("id", id);
                        request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("page", page);
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.getRequestDispatcher("addSche.jsp").forward(request, response);
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
                    if (p == 0) {
                        System.out.println(endDate + " " + endTime);
                        sd.addSchedule(scheNo, movID, formID, cinID, roomID, startDate, t, Date.valueOf(endDate), Time.valueOf(endTime));
                        String ms = "Thêm lịch chiếu thành công";
                        request.setAttribute("ms", ms);
                        request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                        request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                        request.setAttribute("form", fmd.getAllForm());
                        request.setAttribute("id", id);
                        request.setAttribute("s", sd.getScheduleByPage(s, start, end));
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("page", page);
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.getRequestDispatcher("addSche.jsp").forward(request, response);
                    }
                } else {
                    FormDAO fmd = new FormDAO();
                    String scheNo = request.getParameter("scheNo");
                    String startDate_raw = request.getParameter("startDate");
                    String startTime_raw = request.getParameter("startTime");
                    String movID_raw = request.getParameter("movID");
                    String formID_raw = request.getParameter("form");
                    String cinID_raw = request.getParameter("cin");
                    String roomID_raw = request.getParameter("room");

                    Date startDate = Date.valueOf(startDate_raw);
                    System.out.println(startDate);
                    System.out.println(startTime_raw);
                    Time t;
                    t = Time.valueOf(startTime_raw + ":00");
                    System.out.println(t);
                    int movID = 0, formID = 0, cinID = 0, roomID = 0;
                    try {
                        System.out.println(movID_raw + " " + formID_raw + " " + cinID_raw + " " + roomID_raw);
                        movID = Integer.parseInt(movID_raw);
                        formID = Integer.parseInt(formID_raw);
                        cinID = Integer.parseInt(cinID_raw);
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
                    System.out.println(min);
                    Time tme = Time.valueOf(tgianChieu_raw);
                    System.out.println(tgianChieu_raw);
                    int k = 0;
                    try {
                        if (sd.getScheduleByIn4(scheNo, movID, startDate, t, roomID, cinID, min) != null) {
                            throw new Exception("Loi");
                        }
                    } catch (Exception e) {
                        k = 1;
                        CinemaDAO cnd = new CinemaDAO();
                        RoomDAO rd = new RoomDAO();
                        System.out.println("6");
                        request.setAttribute("ms", "Đã tồn tại một lịch chiếu trong khung giờ này, vui lòng chuyển sang một khung giờ khác");
                        request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                        request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                        request.setAttribute("form", fmd.getAllForm());
                        request.setAttribute("id", id);
                        request.setAttribute("movName", mvd.getMovieById(id).getMovName());
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.getRequestDispatcher("addSche.jsp").forward(request, response);
                    }
                    if (k == 0) {
                        String endDate = "";
                        String endTime = "";
                        String en = sd.convertTime(startDate_raw, startTime_raw, min);
                        for (int i = 0; i < en.length(); i++) {
                            if (en.charAt(i) == ' ') {
                                endDate = en.substring(0, i);
                                endTime = en.substring(i + 1);
                            }
                        }
                        sd.addSchedule(scheNo, movID, formID, cinID, roomID, startDate, t, Date.valueOf(endDate), Time.valueOf(endTime));

                        RoomDAO rd = new RoomDAO();
                        CinemaDAO cnd = new CinemaDAO();
                        String ms = "Thêm lịch chiếu thành công";
                        request.setAttribute("ms", ms);
                        request.setAttribute("movName", mvd.getMovieById(id).getMovName());
                        request.setAttribute("cinPick", Integer.parseInt(request.getParameter("cin")));
                        request.setAttribute("room", rd.getAllRoomByCinID(Integer.parseInt(request.getParameter("cin"))));
                        request.setAttribute("form", fmd.getAllForm());
                        request.setAttribute("id", id);

                        request.setAttribute("cin", cnd.getAllCinema());
                        request.getRequestDispatcher("addSche.jsp").forward(request, response);
                    }
                }
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
