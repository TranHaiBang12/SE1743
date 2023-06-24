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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.CartItemTicket;
import model.Room;
import model.RoomSeat;
import model.Ticket;

/**
 *
 * @author acer
 */
public class AddTick extends HttpServlet {

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
            out.println("<title>Servlet AddTick</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddTick at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        ScheDAO scd = new ScheDAO();
        System.out.println(scd.getScheduleByID(id));
        if (scd.getScheduleByID(id) == null) {
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
            request.getRequestDispatcher("addTick.jsp").forward(request, response);
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
        String nm = request.getParameter("nm");
        String vp = request.getParameter("vp");
        String vt = request.getParameter("vt");
        
        String id = request.getParameter("id");

        String nm_dc_raw = request.getParameter("nm_dc");
        String vp_dc_raw = request.getParameter("vp_dc");
        String vt_dc_raw = request.getParameter("vt_dc");
        System.out.println(id);
        System.out.println(nm + " " + vp + " " + vt + " " + nm_dc_raw + " " + vp_dc_raw + " " + vt_dc_raw);
        TicketDAO tkd = new TicketDAO();
        
        double nm_price = 0, vp_price = 0, vt_price = 0;
        double nm_dc = 0, vp_dc = 0, vt_dc = 0;
        
        try {
            nm_price = Double.parseDouble(nm) * 1000;
            vp_price = Double.parseDouble(vp) * 1000;
            vt_price = Double.parseDouble(vt) * 1000;
            
            nm_dc = Double.parseDouble(nm_dc_raw);
            vp_dc = Double.parseDouble(vp_dc_raw);
            vt_dc = Double.parseDouble(vt_dc_raw);
        } catch (Exception e) {
           
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        int codeNM = tkd.insertProductTicket(nm_dc, nm_price);
        System.out.println(codeNM);
        ScheDAO sd = new ScheDAO();
        RoomDAO rd = new RoomDAO();
     
        Room r = rd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID());
        String cNM = "TK" + codeNM;
        tkd.insertTicketNM(cNM, id, r.getNoColSeats() * r.getNoRowSeats());
        
        int codeVP = tkd.insertProductTicket(vp_dc, vp_price);
        String cVP = "TK" + codeVP;
        tkd.insertTicketVP(cVP, id, r.getNoColSeats() * r.getNoRowSeats());
        
        int codeVT = tkd.insertProductTicket(vt_dc, vt_price);
        String cVT = "TK" + codeVT;
        tkd.insertTicketVT(cVT, id, r.getNoColSeats() * r.getNoRowSeats());
        
        FormDAO fmd = new FormDAO();
            MovieDAO mvd = new MovieDAO();
            RoomDAO rmd = new RoomDAO();
            CinemaDAO cnd = new CinemaDAO();
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(sd.getScheduleByID(id).getStart());

            String day = "";
            switch (sd.getScheduleByID(id).getStart().getDay()) {
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

            List<RoomSeat> rs = sed.selectSeatByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID());

            List<Ticket> tkBought = tkd.getAllTicketBoughtBySchedule(sd.getScheduleByID(id).getScheNo());

            String movName = mvd.getMovieById(sd.getScheduleByID(id).getMovID()).getMovName();
            String formName = fmd.getFormById(sd.getScheduleByID(id).getFormID()).getFormName();
            request.setAttribute("ms", "Thêm thành công!");
            request.setAttribute("id", id);
            request.setAttribute("sche", sd.getScheduleByID(id));
            request.setAttribute("cin", cnd.getCinemaByID(sd.getScheduleByID(id).getCinID()));
            request.setAttribute("tk", rs);
            request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(sd.getScheduleByID(id).getRoomID(), sd.getScheduleByID(id).getCinID()));
            request.setAttribute("movName", movName);
            request.setAttribute("mov", mvd.getMovieById(sd.getScheduleByID(id).getMovID()));
            request.setAttribute("formName", formName);
            request.setAttribute("dateFormat", date);
            request.setAttribute("day", day);
            request.getRequestDispatcher("addTick.jsp").forward(request, response);
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
