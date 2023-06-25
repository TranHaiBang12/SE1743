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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.RoomSeat;
import model.Ticket;

/**
 *
 * @author acer
 */
public class UpdTick extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdTick</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdTick at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            for (int i = 0; i < tk.size(); i++) {
                String t = decimalFormat.format(tk.get(i).getDiscount());
                tk.get(i).setDiscount(Double.parseDouble(t));
            }
            System.out.println(scd.getScheduleByID(id).getScheNo());
            if (!tk.isEmpty()) {
                System.out.println("1");
                for (int i = 0; i < tk.size(); i++) {
                    tk.get(i).setStat("Ava");
                }

                for (int i = 0; i < tk.size(); i++) {
                    for (int j = 0; j < tkBought.size(); j++) {

                        if (tk.get(i).getProductCode().equals(tkBought.get(j).getProductCode()) && tk.get(i).getRow() == tkBought.get(j).getRow() && tk.get(i).getCol().equals(tkBought.get(j).getCol())) {
                            tk.get(i).setStat("Buy");
                        }
                    }
                }
                Double nm_price = tkd.getTicketNMPriceBySche(id);
                Double vp_price = tkd.getTicketVPPriceBySche(id);
                Double vt_price = tkd.getTicketVTPriceBySche(id);
                
                Double nm_dc = tkd.getTicketNMDiscountBySche(id);
                Double vp_dc = tkd.getTicketVPDiscountBySche(id);
                Double vt_dc = tkd.getTicketVTDiscountBySche(id);
                
                request.setAttribute("tk", tk);
                request.setAttribute("nm_price", nm_price);
                request.setAttribute("vp_price", vp_price);
                request.setAttribute("vt_price", vt_price);
                
                request.setAttribute("nm_dc", decimalFormat.format(nm_dc));
                request.setAttribute("vp_dc", decimalFormat.format(vp_dc));
                request.setAttribute("vt_dc", decimalFormat.format(vt_dc));
            }
            else {
                System.out.println("2");
                String msT = "Lịch chiếu này hiện chưa có vé";
                request.setAttribute("msT", msT);
            }
            

            String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
            String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
            request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
            request.setAttribute("sche", scd.getScheduleByID(id));
            request.setAttribute("id", id);
            request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
            request.setAttribute("movName", movName);
            request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
            request.setAttribute("formName", formName);
            request.setAttribute("dateFormat", date);
            request.setAttribute("day", day);
            request.getRequestDispatcher("updTick.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        System.out.println("10");
        String id = request.getParameter("id");
        ScheDAO scd = new ScheDAO();
        if (scd.getScheduleByID(id) == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            System.out.println("7");
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
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            for (int i = 0; i < tk.size(); i++) {
                String t = decimalFormat.format(tk.get(i).getDiscount());
                tk.get(i).setDiscount(Double.parseDouble(t));
            }
            System.out.println(scd.getScheduleByID(id).getScheNo());
            if (!tk.isEmpty()) {
                System.out.println("3");
                for (int i = 0; i < tk.size(); i++) {
                    tk.get(i).setStat("Ava");
                }

                for (int i = 0; i < tk.size(); i++) {
                    for (int j = 0; j < tkBought.size(); j++) {

                        if (tk.get(i).getProductCode().equals(tkBought.get(j).getProductCode()) && tk.get(i).getRow() == tkBought.get(j).getRow() && tk.get(i).getCol().equals(tkBought.get(j).getCol())) {
                            tk.get(i).setStat("Buy");
                        }
                    }
                }
                String nm_price_raw = request.getParameter("nm");
                String vp_price_raw = request.getParameter("vp");
                String vt_price_raw = request.getParameter("vt");
                
                String nm_dc_raw = request.getParameter("nm_dc");
                String vp_dc_raw = request.getParameter("vp_dc");
                String vt_dc_raw = request.getParameter("vt_dc");
                
                String aT = decimalFormat.format(Double.parseDouble(nm_dc_raw) * 1000);
                String bT = decimalFormat.format(Double.parseDouble(vp_dc_raw) * 1000);
                String cT = decimalFormat.format(Double.parseDouble(vt_dc_raw) * 1000);
                
                System.out.println(nm_price_raw + " " + vp_price_raw + " " + vt_price_raw + " " + nm_dc_raw + " " + vp_dc_raw + " " + vt_dc_raw + " " + id);
                String productCodeNM = tkd.getTicketBySchedule(id, "NM").getProductCode();
                tkd.updTicketPriceBySche(productCodeNM, Double.parseDouble(nm_price_raw) * 1000, Double.parseDouble(aT));
                String productCodeVP = tkd.getTicketBySchedule(id, "VP").getProductCode();
                tkd.updTicketPriceBySche(productCodeVP, Double.parseDouble(vp_price_raw) * 1000, Double.parseDouble(bT));
                String productCodeVT = tkd.getTicketBySchedule(id, "VT").getProductCode();
                tkd.updTicketPriceBySche(productCodeVT, Double.parseDouble(vt_price_raw) * 1000, Double.parseDouble(cT));
                
                request.setAttribute("ms", "Update thành công");
                request.setAttribute("tk", tk);
                request.setAttribute("nm_price", Double.parseDouble(nm_price_raw) * 1000);
                request.setAttribute("vp_price", Double.parseDouble(vp_price_raw) * 1000);
                request.setAttribute("vt_price", Double.parseDouble(vt_price_raw) * 1000);
                
                request.setAttribute("nm_dc", Double.parseDouble(nm_dc_raw));
                request.setAttribute("vp_dc", Double.parseDouble(vp_dc_raw));
                request.setAttribute("vt_dc", Double.parseDouble(vt_dc_raw));
            }
            else {
                System.out.println("4");
                String msT = "Lịch chiếu này hiện chưa có vé";
                request.setAttribute("msT", msT);
            }
            

            String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
            String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
            request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
            request.setAttribute("sche", scd.getScheduleByID(id));
            request.setAttribute("id", id);
            request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
            request.setAttribute("movName", movName);
            request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
            request.setAttribute("formName", formName);
            request.setAttribute("dateFormat", date);
            request.setAttribute("day", day);
            request.getRequestDispatcher("updTick.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
