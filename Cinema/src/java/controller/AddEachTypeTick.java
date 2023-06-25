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
import dal.SeatDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.CartItemTicket;
import model.RoomSeat;
import model.Ticket;

/**
 *
 * @author acer
 */
public class AddEachTypeTick extends HttpServlet {

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
            out.println("<title>Servlet AddEachTypeTick</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEachTypeTick at " + request.getContextPath() + "</h1>");
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
        System.out.println("10");
        String id = request.getParameter("id");
        String type = request.getParameter("type");

        ScheDAO scd = new ScheDAO();
        System.out.println(scd.getScheduleByID(id));
        if (scd.getScheduleByID(id) == null) {
            System.out.println("4");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            FormDAO fmd = new FormDAO();
            MovieDAO mvd = new MovieDAO();
            RoomDAO rmd = new RoomDAO();
            CinemaDAO cnd = new CinemaDAO();
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(scd.getScheduleByID(id).getStart());

            String day = "";
            switch (scd.getScheduleByID(id).getStart().getDay()) {
                case 0:
                    day = "Chủ Nhật";
                    break;
                case 1:
                    day = "Thứ Hai";
                    break;
                case 2:
                    day = "Thứ Ba";
                    break;
                case 3:
                    day = "Thứ Tư";
                    break;
                case 4:
                    day = "Thứ Năm";
                    break;
                case 5:
                    day = "Thứ Sáu";
                    break;
                case 6:
                    day = "Thứ Bảy";
                    break;
            }
            SeatDAO sed = new SeatDAO();
            //sed.insertAllSeatInRoom(id);
            TicketDAO tkd = new TicketDAO();
            Cookie[] arr = request.getCookies();
            HttpSession session = request.getSession();
            List<CartItemTicket> listT = new ArrayList<>();

            List<RoomSeat> rs = sed.selectSeatByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID());

            List<Ticket> tkBought = tkd.getAllTicketBoughtBySchedule(scd.getScheduleByID(id).getScheNo());

            String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
            String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
            request.setAttribute("id", id);
            request.setAttribute("sche", scd.getScheduleByID(id));
            request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
            request.setAttribute("tk", rs);
            request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
            request.setAttribute("movName", movName);
            request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
            request.setAttribute("formName", formName);
            request.setAttribute("dateFormat", date);
            request.setAttribute("day", day);
            request.setAttribute("type", type);
            request.getRequestDispatcher("addEachTypeTick.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        TicketDAO tkd = new TicketDAO();
        ScheDAO sd = new ScheDAO();
        RoomDAO rd = new RoomDAO();
        
        if (type.equals("NM")) {
            String nm_price_raw = request.getParameter("nm");
            String nm_dc_raw = request.getParameter("nm_dc");
            nm_dc_raw = decimalFormat.format(Double.parseDouble(nm_dc_raw));
            int code = tkd.insertProductTicket(Double.parseDouble(nm_dc_raw), Double.parseDouble(nm_price_raw));
            String productCode = "TK" + code;
            tkd.insertTicketNM(productCode, id, rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()).getNoColSeats() * rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()).getNoRowSeats());
        } else if (type.equals("VP")) {
            String vp_price_raw = request.getParameter("vp");
            String vp_dc_raw = request.getParameter("vp_dc");
            vp_dc_raw = decimalFormat.format(Double.parseDouble(vp_dc_raw));
            int code = tkd.insertProductTicket(Double.parseDouble(vp_dc_raw), Double.parseDouble(vp_price_raw));
            String productCode = "TK" + code;
            tkd.insertTicketNM(productCode, id, rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()).getNoColSeats() * rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()).getNoRowSeats());
        } else if (type.equals("VT")) {
            String vt_price_raw = request.getParameter("vt");
            String vt_dc_raw = request.getParameter("vt_dc");
            vt_dc_raw = decimalFormat.format(Double.parseDouble(vt_dc_raw));
            int code = tkd.insertProductTicket(Double.parseDouble(vt_dc_raw), Double.parseDouble(vt_price_raw));
            String productCode = "TK" + code;
            tkd.insertTicketNM(productCode, id, rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()).getNoColSeats() * rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()).getNoRowSeats());
        }
        ScheDAO scd = new ScheDAO();
        System.out.println(scd.getScheduleByID(id));
        if (scd.getScheduleByID(id) == null) {
            System.out.println("4");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            FormDAO fmd = new FormDAO();
            MovieDAO mvd = new MovieDAO();
            RoomDAO rmd = new RoomDAO();
            CinemaDAO cnd = new CinemaDAO();
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(scd.getScheduleByID(id).getStart());

            String day = "";
            switch (scd.getScheduleByID(id).getStart().getDay()) {
                case 0:
                    day = "Chủ Nhật";
                    break;
                case 1:
                    day = "Thứ Hai";
                    break;
                case 2:
                    day = "Thứ Ba";
                    break;
                case 3:
                    day = "Thứ Tư";
                    break;
                case 4:
                    day = "Thứ Năm";
                    break;
                case 5:
                    day = "Thứ Sáu";
                    break;
                case 6:
                    day = "Thứ Bảy";
                    break;
            }
            SeatDAO sed = new SeatDAO();
            //sed.insertAllSeatInRoom(id);

            List<CartItemTicket> listT = new ArrayList<>();

            List<RoomSeat> rs = sed.selectSeatByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID());

            List<Ticket> tkBought = tkd.getAllTicketBoughtBySchedule(scd.getScheduleByID(id).getScheNo());

            String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
            String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
            request.setAttribute("ms", "Thêm thành công!");
            request.setAttribute("id", id);
            request.setAttribute("sche", scd.getScheduleByID(id));
            request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
            request.setAttribute("tk", rs);
            request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
            request.setAttribute("movName", movName);
            request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
            request.setAttribute("formName", formName);
            request.setAttribute("dateFormat", date);
            request.setAttribute("day", day);
            request.setAttribute("type", type);
            request.getRequestDispatcher("addEachTypeTick.jsp").forward(request, response);
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
