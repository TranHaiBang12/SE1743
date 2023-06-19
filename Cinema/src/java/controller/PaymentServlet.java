/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.FoodDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderTicketDetailDAO;
import dal.TicketDAO;
import dal.TransactionCDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;
import model.CartItemFood;
import model.CartItemTicket;
import model.DateMD;
import model.LocationCinMD;

/**
 *
 * @author acer
 */
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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
        Cookie[] arr = request.getCookies();
        List<CartItemFood> list = new ArrayList<>();
        List<CartItemTicket> listT = new ArrayList<>();
        FoodDAO fda = new FoodDAO();
        TicketDAO tkd = new TicketDAO();
        String cart = "";
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            for (Cookie i : arr) {
                if (i.getName().equals(a.getUserName())) {
                    cart = i.getValue();
                    break;
                }
            }

            for (int i = 0; i < cart.length(); i++) {
                if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                    if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                        list.add(new CartItemFood(fda.getFoodById(cart.substring(i + 1, i + 7)), Integer.parseInt(cart.substring(i + 8, i + 9))));
                    } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
                        System.out.println(cart.substring(i + 1, i + 7) + " " + Integer.parseInt(cart.substring(i + 9, i + 10)) + " " + cart.substring(i + 8, i + 9) + " " + cart.substring(i + 8, i + 10));
                        listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                    }

                }
            }

            String t = "2023-05-01";
            List<DateMD> dte = new ArrayList<>();
            Date d[] = new Date[7];
            d[0] = Date.valueOf(java.time.LocalDate.now());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String k = formatter.format(d[0]);
            Calendar c1 = Calendar.getInstance();
            c1.setTime(d[0]);
            for (int i = 1; i < d.length; i++) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                try {
                    c.setTime(sdf.parse(k));
                } catch (ParseException ex) {
                    Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                c.add(Calendar.DATE, 1);  // number of days to add
                k = sdf.format(c.getTime());  // dt is now the new date
                d[i] = Date.valueOf(k);
            }
            Date date1 = Date.valueOf(t);
            for (int i = 0; i < 7; i++) {
                String day;
                String date2;
                String month;
                long time = d[i].getTime() - date1.getTime();
                time = (time / (24 * 60 * 60 * 1000)) % 7;
                if (time == 0) {
                    day = "Mon";
                } else if (time == 1) {
                    day = "Tue";
                } else if (time == 2) {
                    day = "Wed";
                } else if (time == 3) {
                    day = "Thu";
                } else if (time == 4) {
                    day = "Fri";
                } else if (time == 5) {
                    day = "Sat";
                } else {
                    day = "Sun";
                }
                if (d[i].getDate() < 10) {
                    date2 = "0" + d[i].getDate();
                } else {
                    date2 = String.valueOf(d[i].getDate());
                }
                if (d[i].getMonth() < 10) {
                    month = "0" + (d[i].getMonth() + 1);
                } else {
                    month = String.valueOf((d[i].getMonth() + 1));
                }

                DateMD dmd = new DateMD(i, date2, month, day, String.valueOf(d[i].getYear()));
                dte.add(dmd);
            }

            CinemaDAO cnd = new CinemaDAO();

            List<LocationCinMD> loc = cnd.getAllCinemaNameAndLoc();
            request.setAttribute("loc", loc);
            request.setAttribute("date", dte);
            request.setAttribute("listCart", list);
            request.setAttribute("listTicket", listT);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        CinemaDAO cnd = new CinemaDAO();
        List<LocationCinMD> loc = cnd.getAllCinemaNameAndLoc();
        Account a = (Account) session.getAttribute("account");
        String email = request.getParameter("email");
        String t = "2023-05-01";
        List<DateMD> dte = new ArrayList<>();
        Date d[] = new Date[7];
        d[0] = Date.valueOf(java.time.LocalDate.now());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String k = formatter.format(d[0]);
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d[0]);
        for (int i = 1; i < d.length; i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(k));
            } catch (ParseException ex) {
                Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            c.add(Calendar.DATE, 1);  // number of days to add
            k = sdf.format(c.getTime());
            d[i] = Date.valueOf(k);
        }
        Date date1 = Date.valueOf(t);
        for (int i = 0; i < 7; i++) {
            String day;
            String date2;
            String month;
            long time = d[i].getTime() - date1.getTime();
            time = (time / (24 * 60 * 60 * 1000)) % 7;
            if (time == 0) {
                day = "Mon";
            } else if (time == 1) {
                day = "Tue";
            } else if (time == 2) {
                day = "Wed";
            } else if (time == 3) {
                day = "Thu";
            } else if (time == 4) {
                day = "Fri";
            } else if (time == 5) {
                day = "Sat";
            } else {
                day = "Sun";
            }
            if (d[i].getDate() < 10) {
                date2 = "0" + d[i].getDate();
            } else {
                date2 = String.valueOf(d[i].getDate());
            }
            if (d[i].getMonth() < 10) {
                month = "0" + (d[i].getMonth() + 1);
            } else {
                month = String.valueOf((d[i].getMonth() + 1));
            }

            DateMD dmd = new DateMD(i, date2, month, day, String.valueOf(d[i].getYear() + 1900));
            dte.add(dmd);
        }
        String sdt = request.getParameter("sdt");
        Cookie[] arr = request.getCookies();
        List<CartItemFood> list = new ArrayList<>();
        List<CartItemTicket> listT = new ArrayList<>();
        FoodDAO fda = new FoodDAO();
        TicketDAO tkd = new TicketDAO();
        String cart = "";
        Cookie user = null;
        if (a != null) {
            for (Cookie i : arr) {
                if (i.getName().equals(a.getUserName())) {
                    user = i;
                    cart = i.getValue();
                    break;
                }
            }

            for (int i = 0; i < cart.length(); i++) {
                if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                    if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                        list.add(new CartItemFood(fda.getFoodById(cart.substring(i + 1, i + 7)), Integer.parseInt(cart.substring(i + 8, i + 9))));
                    } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
                        System.out.println(cart.substring(i + 1, i + 7) + " " + Integer.parseInt(cart.substring(i + 9, i + 10)) + " " + cart.substring(i + 8, i + 9) + " " + cart.substring(i + 8, i + 10));
                        listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                    }

                }
            }
            if (checkEmail(email) == false) {
                String ms = "Vui lòng nhập đúng email";
                request.setAttribute("ms", ms);
                request.setAttribute("loc", loc);
                request.setAttribute("listCart", list);
                request.setAttribute("date", dte);
                request.setAttribute("listTicket", listT);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            }
            try {
                int n = Integer.parseInt(sdt);
                if (sdt.length() != 10) {
                    throw new Exception("Loi moi");
                }
            } catch (Exception e) {
                String ms = "Vui lòng nhập đúng số điện thoại";
                request.setAttribute("ms", ms);
                request.setAttribute("loc", loc);
                request.setAttribute("listCart", list);
                request.setAttribute("date", dte);
                request.setAttribute("listTicket", listT);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            }

            if (request.getParameter("pm") == null) {
                String ms = "Vui lòng chọn cách thanh toán";
                request.setAttribute("ms", ms);
                request.setAttribute("loc", loc);
                request.setAttribute("listCart", list);
                request.setAttribute("date", dte);
                request.setAttribute("listTicket", listT);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            }
            Time tt = Time.valueOf(Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes() + ":" + Calendar.getInstance().getTime().getSeconds());
            Date dd = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());

            String pm = "";
            if (request.getParameter("pm").equals("0")) {
                pm = "VNPay";
            } else if (request.getParameter("pm").equals("1")) {
                pm = "Payoo";
            }
            if (request.getParameter("dte") == null) {
                String ms = "Vui lòng chọn ngày nhận";
                request.setAttribute("ms", ms);
                request.setAttribute("listCart", list);
                request.setAttribute("loc", loc);
                request.setAttribute("date", dte);
                request.setAttribute("listTicket", listT);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            } else if (request.getParameter("dte").equals("")) {
                String ms = "Vui lòng chọn ngày nhận";
                request.setAttribute("ms", ms);
                request.setAttribute("loc", loc);
                request.setAttribute("listCart", list);
                request.setAttribute("date", dte);
                request.setAttribute("listTicket", listT);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            }
            if(!request.getParameter("pass").equals(a.getPassword())) {
                String ms = "Sai mật khẩu";
                request.setAttribute("ms", ms);
                request.setAttribute("loc", loc);
                request.setAttribute("listCart", list);
                request.setAttribute("date", dte);
                request.setAttribute("listTicket", listT);
                request.getRequestDispatcher("payment.jsp").forward(request, response);
            }
            String m = "THANH TOÁN THÀNH CÔNG. VUI LÒNG VÀO LỊCH SỬ GIAO DỊCH ĐỂ XEM MÃ VÉ, MÃ ĐỒ ĂN CỦA BẠN";
            String datePick = "";
            Date dateStart = null;
            Date dateEnd = null;
            Time timeStart = null;
            Time timeEnd = null;
            System.out.println(dte.size());
            for (int i = 0; i < dte.size(); i++) {
                System.out.println(Integer.parseInt(request.getParameter("dte")) + "/");
                System.out.println(dte.get(i).getId());
                if (Integer.parseInt(request.getParameter("dte")) == dte.get(i).getId()) {
                    System.out.println(dte.get(i).getId() + dte.get(i).getDay());
                    datePick = dte.get(i).getDay() + " " + dte.get(i).getDate() + "/" + dte.get(i).getMonth() + "/" + (Calendar.getInstance().getTime().getYear() + 1900);
                    if (dte.get(i).getId() == 0) {
                        timeStart = tt;
                        dateStart = dd;
                        if (i != dte.size() - 1) {
                            dateEnd = Date.valueOf(dte.get(i + 1).getYear() + "-" + dte.get(i + 1).getMonth() + "-" + dte.get(i + 1).getDate());
                        } else {
                            c1.setTime(Date.valueOf(dte.get(i).getYear() + "-" + dte.get(i).getMonth() + "-" + dte.get(i).getDate()));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar c = Calendar.getInstance();
                            try {
                                c.setTime(sdf.parse(k));
                            } catch (ParseException ex) {
                                Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            c.add(Calendar.DATE, 1);  // number of days to add
                            k = sdf.format(c.getTime());
                            dateEnd = Date.valueOf(k);
                        }
                        timeEnd = Time.valueOf("00:00:00");
                        break;

                    } else {
                        datePick = dte.get(i).getDay() + " " + dte.get(i).getDate() + "/" + dte.get(i).getMonth() + "/" + (Calendar.getInstance().getTime().getYear() + 1900);
                        dateStart = Date.valueOf(dte.get(i).getYear() + "-" + dte.get(i).getMonth() + "-" + dte.get(i).getDate());
                        timeStart = Time.valueOf("00:00:00");
                        if (i != dte.size() - 1) {
                            dateEnd = Date.valueOf(dte.get(i + 1).getYear() + "-" + dte.get(i + 1).getMonth() + "-" + dte.get(i + 1).getDate());
                        } else {
                            c1.setTime(Date.valueOf(dte.get(i).getYear() + "-" + dte.get(i).getMonth() + "-" + dte.get(i).getDate()));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar c = Calendar.getInstance();
                            try {
                                c.setTime(sdf.parse(k));
                            } catch (ParseException ex) {
                                Logger.getLogger(BookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            c.add(Calendar.DATE, 1);  // number of days to add
                            k = sdf.format(c.getTime());
                            dateEnd = Date.valueOf(k);
                        }
                        timeEnd = Time.valueOf("00:00:00");
                        break;
                    }

                }

            }

            int cinID = 0;
            String lOc = request.getParameter("loc");
            for (int i = 0; i < lOc.length(); i++) {
                if (lOc.charAt(i) == '.') {
                    cinID = Integer.parseInt(lOc.substring(0, i));
                }
            }
            OrderDAO ord = new OrderDAO();
            int id = ord.insert(a.getUserName(), request.getParameter("fName"), request.getParameter("lName"), request.getParameter("sdt"), request.getParameter("email"), request.getParameter("cntry"), request.getParameter("strt"), request.getParameter("dist"), request.getParameter("city"), pm, dd, tt);
            String orderID = "ONL" + id;
            OrderDetailDAO odd = new OrderDetailDAO();
            TransactionCDAO tcd = new TransactionCDAO();
            OrderTicketDetailDAO otd = new OrderTicketDetailDAO();
            System.out.println(orderID);
            if (!list.isEmpty()) {
                String fdCode = orderID + randomAlpha(20 - orderID.length());
                tcd.insert(orderID, fdCode, 1, dateStart, timeStart, dateEnd, timeEnd, cinID);
                for (int i = 0; i < list.size(); i++) {
                    odd.insert(orderID, list.get(i).getFood().getProductCode(), list.get(i).getFood().getDiscount(), list.get(i).getFood().getPrice(), list.get(i).getQuantity());

                }
            }
            if (!listT.isEmpty()) {
                int cID = listT.get(0).getTicket().getCinID();
                String tkCode = orderID + randomAlpha(20 - orderID.length());
                tcd.insert(orderID, tkCode, 2, dateStart, timeStart, dateEnd, timeEnd, cID);
                for (int i = 0; i < listT.size(); i++) {
                    otd.insert(orderID, listT.get(i).getTicket().getProductCode(), listT.get(i).getSeat().substring(0, 1), Integer.parseInt(listT.get(i).getSeat().substring(1, 2)), listT.get(i).getTicket().getDiscount(), listT.get(i).getTicket().getPrice());
                    if (listT.get(i).getTicket().getCinID() != cID) {
                        tkCode = orderID + randomAlpha(20 - orderID.length());
                        cID = listT.get(i).getTicket().getCinID();
                        tcd.insert(orderID, tkCode, 2, dateStart, timeStart, dateEnd, timeEnd, listT.get(i).getTicket().getCinID());
                    }

                }

            }
            removeCookie(response, user.getName());

            request.setAttribute("m", m);
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("fName", request.getParameter("fName"));
            request.setAttribute("lName", request.getParameter("lName"));
            request.setAttribute("sdt", sdt);
            request.setAttribute("datePick", datePick);
            request.setAttribute("locPick", request.getParameter("loc"));
            request.setAttribute("cntry", request.getParameter("cntry"));
            request.setAttribute("city", request.getParameter("city"));
            request.setAttribute("dist", request.getParameter("dist"));
            request.setAttribute("strt", request.getParameter("strt"));
            request.setAttribute("pm", request.getParameter("pm"));
            request.getRequestDispatcher("payment.jsp").forward(request, response);

        }
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public String randomAlpha(int numCha) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String alpha = alphabet + alphabet.toUpperCase();
        Random generator = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numCha; i++) {
            int number = generator.nextInt(alpha.length() - 1 + 1);
            char ch = alpha.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
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
