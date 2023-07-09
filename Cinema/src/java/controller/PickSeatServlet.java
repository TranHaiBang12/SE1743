/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.FoodDAO;
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
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.CartItemFood;
import model.CartItemTicket;
import model.RoomSeat;
import model.Ticket;

/**
 *
 * @author acer
 */
public class PickSeatServlet extends HttpServlet {

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
            out.println("<title>Servlet PickSeatServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PickSeatServlet at " + request.getContextPath() + "</h1>");
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
            Cookie[] arr = request.getCookies();
            HttpSession session = request.getSession();
            List<CartItemTicket> listT = new ArrayList<>();
            String cart = "";
            Account acc = (Account) session.getAttribute("account");
            if(acc == null) {
                response.sendRedirect("login");
            }
            if (acc != null) {
                for (Cookie i : arr) {
                    if (i.getName().equals(acc.getUserName())) {
                        cart = i.getValue();
                        break;
                    }
                }

                String code = "";
                int cnt = 0;
                FoodDAO fd = new FoodDAO();
                int cntT = 0;
                int cntF = 0;
                int k1 = 0;
                int p = 0;
                double price = 0;
                int quantity = 0;
                double discount = 0;
                List<String> listHH = fd.getAllFoodOff();
                List<Ticket> tkB = tkd.getAllTicketBought();
                for (int i = 0; i < cart.length(); i++) {
                    if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                        cntT = 0;
                        cntF = 0;
                        if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
                            for (int j = 0; j < tkB.size(); j++) {
                                if (cart.substring(i + 1, i + 7).equals(tkB.get(j).getProductCode()) && Integer.parseInt(cart.substring(i + 9, i + 10)) == tkB.get(j).getRow() && cart.substring(i + 8, i + 9).equals(tkB.get(j).getCol())) {
                                    cntT++;
                                    p++;
                                    cart = cart.substring(0, i + 1) + cart.substring(i + 7);
                                }
                            }
                            if (cntT == 0) {
                                for (int j = i; j < cart.length(); j++) {
                                    if (cart.charAt(j) == 'p' && k1 == 0) {
                                        code = cart.substring(i + 1, j);
                                        cnt = j;
                                        k1++;
                                    } else if (cart.charAt(j) == 'p' && k1 == 1) {
                                        quantity = 1;
                                        cnt = j;
                                        k1++;
                                    } else if (cart.charAt(j) == 'd' && k1 == 2) {
                                        price = Double.parseDouble(cart.substring(cnt + 1, j));
                                        cnt = j;
                                        k1++;
                                    } else if ((cart.charAt(j) == '/' && k1 == 3) || j == cart.length() - 1 && k1 == 3) {
                                        if (cart.charAt(j) == '/' && k1 == 3) {
                                            discount = Double.parseDouble(cart.substring(cnt + 1, j));
                                        } else if (j == cart.length() - 1 && k1 == 3) {
                                            discount = Double.parseDouble(cart.substring(cnt + 1));
                                        }
                                        cnt = j;
                                        k1++;
                                        break;
                                    }
                                }
                                cnt = 0;
                                k1 = 0;
                                listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                            }
                        }

                    }
                }
                

                List<RoomSeat> rs = sed.selectSeatByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID());

                List<Ticket> tkBought = tkd.getAllTicketBoughtBySchedule(scd.getScheduleByID(id).getScheNo());
                List<Ticket> tk = tkd.getTicketPByScheduleRCS(scd.getScheduleByID(id).getScheNo());
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                for (int i = 0; i < tk.size(); i++) {
                    String t = decimalFormat.format(tk.get(i).getDiscount());
                    tk.get(i).setDiscount(Double.parseDouble(t));
                }
                System.out.println(scd.getScheduleByID(id).getScheNo());
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

                String movName = mvd.getMovieById(scd.getScheduleByID(id).getMovID()).getMovName();
                String formName = fmd.getFormById(scd.getScheduleByID(id).getFormID()).getFormName();
                request.setAttribute("cin", cnd.getCinemaByID(scd.getScheduleByID(id).getCinID()));
                request.setAttribute("sche", scd.getScheduleByID(id));
                request.setAttribute("tk", tk);
                request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(scd.getScheduleByID(id).getRoomID(), scd.getScheduleByID(id).getCinID()));
                request.setAttribute("movName", movName);
                request.setAttribute("mov", mvd.getMovieById(scd.getScheduleByID(id).getMovID()));
                request.setAttribute("formName", formName);
                request.setAttribute("dateFormat", date);
                request.setAttribute("day", day);
                request.getRequestDispatcher("pickSeat.jsp").forward(request, response);
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
        processRequest(request, response);
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
