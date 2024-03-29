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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.RoomSeat;
import model.Ticket;

/**
 *
 * @author acer
 */
public class Upd1Tick extends HttpServlet {

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
            out.println("<title>Servlet Upd1Tick</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Upd1Tick at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("sd");
        ScheDAO scd = new ScheDAO();
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
        List<String> b = new ArrayList<>();

        int a[] = new int[rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats()];
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats(); i++) {
            a[i] = i + 1;
        }
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoColSeats(); i++) {
            b.add(c.substring(i, i + 1));

        }
        SeatDAO sed = new SeatDAO();
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoColSeats(); i++) {
            for (int j = 0; j < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats(); j++) {
                if (j >= 3 && j <= 5) {
                    if (i >= 1 && i <= 6) {
                        sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 2);
                    } else {
                        sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 1);
                    }
                } else if (j >= 6) {
                    sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 3);

                } else {
                    sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 1);
                }
            }
        }
        TicketDAO tkd = new TicketDAO();

        List<RoomSeat> rs = sed.selectSeatByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID());

        List<Ticket> tkBought = tkd.getAllTicketBoughtBySchedule(scd.getScheduleByID(id).getScheNo());
        List<Ticket> tk = tkd.getTicketPByScheduleRCS(scd.getScheduleByID(id).getScheNo());

        String idTick = request.getParameter("id");
        request.setAttribute("tk", tk);

        String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
        String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
        request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
        request.setAttribute("sche", scd.getScheduleByID(id));
        request.setAttribute("id", id);
        request.setAttribute("idTick", idTick);
        request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
        request.setAttribute("movName", movName);
        request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
        request.setAttribute("formName", formName);
        request.setAttribute("dateFormat", date);
        request.setAttribute("day", day);
        request.getRequestDispatcher("upd1Tick.jsp").forward(request, response);
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
        ScheDAO scd = new ScheDAO();
        FormDAO fmd = new FormDAO();
        MovieDAO mvd = new MovieDAO();
        RoomDAO rmd = new RoomDAO();
        CinemaDAO cnd = new CinemaDAO();
        TicketDAO tkd = new TicketDAO();
        String id = request.getParameter("id");
        String idTick = request.getParameter("idTick");
        String price_raw = request.getParameter("nm");
        String discount_raw = request.getParameter("nm_dc");
        double price = 0;
        double discount = 0;
        try {
            price = Double.parseDouble(price_raw);
            discount = Double.parseDouble(discount_raw);
        } catch (Exception e) {
            System.out.println(e);
        }
        tkd.updTicketPriceBySche(idTick, price * 1000, discount);

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
        List<String> b = new ArrayList<>();

        int a[] = new int[rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats()];
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats(); i++) {
            a[i] = i + 1;
        }
        String c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoColSeats(); i++) {
            b.add(c.substring(i, i + 1));

        }
        SeatDAO sed = new SeatDAO();
        for (int i = 0; i < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoColSeats(); i++) {
            for (int j = 0; j < rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()).getNoRowSeats(); j++) {
                if (j >= 3 && j <= 5) {
                    if (i >= 1 && i <= 6) {
                        sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 2);
                    } else {
                        sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 1);
                    }
                } else if (j >= 6) {
                    sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 3);

                } else {
                    sed.insert(j + 1, c.substring(i, i + 1), scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID(), 1);
                }
            }
        }

        List<RoomSeat> rs = sed.selectSeatByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID());

        List<Ticket> tkBought = tkd.getAllTicketBoughtBySchedule(scd.getScheduleByID(id).getScheNo());
        List<Ticket> tk = tkd.getTicketPByScheduleRCS(scd.getScheduleByID(id).getScheNo());

        request.setAttribute("tk", tk);

        String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
        String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
        request.setAttribute("ms", "Sửa thành công");
        request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
        request.setAttribute("sche", scd.getScheduleByID(id));
        request.setAttribute("id", id);
        request.setAttribute("idTick", idTick);
        request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
        request.setAttribute("movName", movName);
        request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
        request.setAttribute("formName", formName);
        request.setAttribute("dateFormat", date);
        request.setAttribute("day", day);
        request.getRequestDispatcher("upd1Tick.jsp").forward(request, response);
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
