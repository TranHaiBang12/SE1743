/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.EventDAO;
import dal.FoodDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderTicketDetailDAO;
import dal.PointDAO;
import dal.RoomDAO;
import dal.SeatDAO;
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
import java.text.DecimalFormat;
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
import model.AccountPoint;
import model.CartItemFood;
import model.CartItemTicket;
import model.DateMD;
import model.Food;
import model.LocationCinMD;
import model.Room;
import model.Ticket;

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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Cookie[] arr = request.getCookies();
        List<CartItemFood> list = new ArrayList<>();
        List<CartItemTicket> listT = new ArrayList<>();
        FoodDAO fda = new FoodDAO();
        TicketDAO tkd = new TicketDAO();
        String cart = "";
        double price = 0;
        double priceT = 0;
        double priceF = 0;
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            for (Cookie i : arr) {
                if (i.getName().equals(a.getUserName())) {
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
            double pric1e = 0;
            int quantity = 0;
            double discount = 0;
            List<String> listHH = fd.getAllFoodOff();
            List<Ticket> tkB = tkd.getAllTicketBought();
            for (int i = 0; i < cart.length(); i++) {
                if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                    cntT = 0;
                    cntF = 0;
                    if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                        for (int j = 0; j < listHH.size(); j++) {
                            if (cart.substring(i + 1, i + 7).equals(listHH.get(j))) {
                                cntF++;
                                p++;

                                cart = cart.substring(0, i + 1) + cart.substring(i + 7);
                            }
                        }
                        if (cntF == 0) {
                            for (int j = i; j < cart.length(); j++) {
                                if (cart.charAt(j) == 'p' && k1 == 0) {
                                    code = cart.substring(i + 1, j);
                                    cnt = j;
                                    k1++;
                                } else if (cart.charAt(j) == 'p' && k1 == 1) {
                                    quantity = Integer.parseInt(cart.substring(cnt + 1, j));
                                    cnt = j;
                                    k1++;
                                } else if (cart.charAt(j) == 'p' && k1 == 2) {
                                    pric1e = Double.parseDouble(cart.substring(cnt + 1, j));
                                    cnt = j;
                                    k1++;
                                } else if ((cart.charAt(j) == '/' && k1 == 3) || (j == cart.length() - 1 && k1 == 3)) {
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
                            k1 = 0;
                            cnt = 0;

                            list.add(new CartItemFood(fda.getFoodById(code), quantity, discount, pric1e));
                        }
                    } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
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
                                    pric1e = Double.parseDouble(cart.substring(cnt + 1, j));

                                    cnt = j;
                                    k1++;
                                } else if ((cart.charAt(j) == '/' && k1 == 3) || (j == cart.length() - 1 && k1 == 3)) {
                                    if (cart.charAt(j) == '/' && k1 == 3) {
                                        discount = Double.parseDouble(cart.substring(cnt + 1, j));
                                    } else if (j == cart.length() - 1 && k1 == 3) {
                                        discount = Double.parseDouble(cart.substring(cnt + 1, j));
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

            for (int i = 0; i < list.size(); i++) {
                priceF += (list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount()) * list.get(i).getQuantity();
            }
            for (int i = 0; i < listT.size(); i++) {
                priceT += listT.get(i).getTicket().getPrice() - listT.get(i).getTicket().getPrice() * listT.get(i).getTicket().getDiscount();
            }

            price = priceF + priceT;

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
            PointDAO pd = new PointDAO();
            AccountPoint ap = pd.getAccountPoint(a.getUserName());
            int point;
            if (ap != null) {
                point = ap.getPoint();
            } else {
                point = 0;
            }

            EventDAO evd = new EventDAO();
            List<model.Event> evAchieve = new ArrayList<>();

            if (!listT.isEmpty()) {
                for (int j = 0; j < listT.size(); j++) {

                    int evC[] = evd.getEventCodeByCin(listT.get(j).getTicket().getCinID());
                    if (evC.length > 0) {
                        for (int i = 0; i < evC.length; i++) {
                            int ev[] = evd.checkEventMov(listT.get(j).getTicket().getMovie().getMovID(), evC[i]);
                            if (ev.length > 0) {

                                for (int l = 0; l < ev.length; l++) {
                                    evAchieve.add(evd.getEventByCode(ev[l]));
                                }

                            }
                            int ev2[] = evd.checkEventOrder(priceT, evC[i], "TK");
                            if (ev2.length > 0) {
                                for (int l = 0; l < ev2.length; l++) {
                                    evAchieve.add(evd.getEventByCode(ev2[l]));
                                }
                            }
                        }
                    }
                }
            }

            if (!list.isEmpty()) {
                int evC[] = evd.getEventCodeByCin(cnd.getAllCinemaNameAndLoc().get(0).getId());
                if (evC.length > 0) {
                    for (int i = 0; i < evC.length; i++) {
                        int ev2[] = evd.checkEventOrder(priceF, evC[i], "FD");
                        if (ev2.length > 0) {
                            evAchieve.add(evd.getEventByCode(ev2[i]));
                        }
                    }
                }
            }

            for (int i = 0; i < evAchieve.size() - 1; i++) {
                for (int j = i + 1; j < evAchieve.size(); j++) {
                    if (evAchieve.get(i).getEventCode() == evAchieve.get(j).getEventCode()) {
                        evAchieve.remove(i);
                    }
                }
            }
            fd = new FoodDAO();
            List<Food> f = new ArrayList<>();
            discount = 0;
            double tpt = 0;
            for (int i = 0; i < evAchieve.size(); i++) {
                if (evAchieve.get(i).getEventType() == 1) {
                    if (evd.getEventDiscountO(evAchieve.get(i).getEventCode()) != null) {
                        discount = evd.getEventDiscountO(evAchieve.get(i).getEventCode()).getDiscount();
                        String pc = decimalFormat.format(discount * 100);
                        if (evd.getEventDiscountO(evAchieve.get(i).getEventCode()).getType().equals("FD")) {
                            tpt = priceF - priceF * discount + priceT;
                        } else if (evd.getEventDiscountO(evAchieve.get(i).getEventCode()).getType().equals("TK")) {
                            tpt = priceT - priceT * discount + priceF;
                        }
                        if (discount != 0) {
                            System.out.println("pslp");
                            request.setAttribute("tpt", decimalFormat.format(tpt));
                            request.setAttribute("discount", pc);
                        }
                    } else if (evd.getEventDiscountM(evAchieve.get(i).getEventCode()) != null) {
                        discount = evd.getEventDiscountM(evAchieve.get(i).getEventCode()).getDiscount();
                        String pc = decimalFormat.format(discount * 100);
                        tpt = priceT - priceT * discount + priceF;
                        System.out.println(priceT);
                        if (discount != 0) {
                            request.setAttribute("tpt", decimalFormat.format(tpt));
                            request.setAttribute("discount", pc);
                        }
                    }
                } else if (evAchieve.get(i).getEventType() == 2) {
                    if (evd.getAllProductInEvent(evAchieve.get(i).getEventCode()) != null) {
                        List<String> pr = evd.getAllProductInEvent(evAchieve.get(i).getEventCode()).getProduct();
                        for (int j = 0; j < pr.size(); j++) {
                            f.add(fd.getFoodById(pr.get(j)));
                        }
                    }
                }
            }
            if (!evAchieve.isEmpty()) {
                request.setAttribute("ev", evAchieve);
            }
            if (!f.isEmpty()) {
                request.setAttribute("f", f);
            }
            double maxPointUse = 0;
            if (tpt == 0) {
                maxPointUse = Math.floor((price / 1000) * 90 / 100);
            } else {
                maxPointUse = Math.floor((tpt / 1000) * 90 / 100);
            }
            request.setAttribute("point", decimalFormat.format(point));
            if (list.isEmpty()) {
                request.setAttribute("noF", 1);
            }
            request.setAttribute("maxPoint", decimalFormat.format(maxPointUse));
            request.setAttribute("price", decimalFormat.format(price));
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
        double p1 = 0;
        if (request.getParameter("scroll") != null) {
            System.out.println(request.getParameter("scroll"));
            try {
                p1 = Double.parseDouble(request.getParameter("scroll"));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        request.setAttribute("scroll", p1);
        if (request.getParameter("check") == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            if (request.getParameter("check").equals("1")) {
                Cookie[] arr = request.getCookies();
                List<CartItemFood> list = new ArrayList<>();
                List<CartItemTicket> listT = new ArrayList<>();
                FoodDAO fda = new FoodDAO();
                TicketDAO tkd = new TicketDAO();
                String cart = "";
                double price = 0;
                double priceT = 0;
                double priceF = 0;
                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("account");
                if (a != null) {
                    for (Cookie i : arr) {
                        if (i.getName().equals(a.getUserName())) {
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
                    price = 0;
                    int quantity = 0;
                    double discount = 0;
                    List<String> listHH = fd.getAllFoodOff();
                    List<Ticket> tkB = tkd.getAllTicketBought();
                    for (int i = 0; i < cart.length(); i++) {
                        if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                            cntT = 0;
                            cntF = 0;
                            if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {

                                for (int j = 0; j < listHH.size(); j++) {
                                    if (cart.substring(i + 1, i + 7).equals(listHH.get(j))) {
                                        cntF++;
                                        p++;
                                        cart = cart.substring(0, i + 1) + cart.substring(i + 7);
                                    }
                                }
                                if (cntF == 0) {
                                    for (int j = i; j < cart.length(); j++) {
                                        if (cart.charAt(j) == 'p' && k1 == 0) {
                                            code = cart.substring(i + 1, j);
                                            cnt = j;
                                            k1++;
                                        } else if (cart.charAt(j) == 'p' && k1 == 1) {
                                            quantity = Integer.parseInt(cart.substring(cnt + 1, j));
                                            cnt = j;
                                            k1++;
                                        } else if (cart.charAt(j) == 'p' && k1 == 2) {
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
                                    k1 = 0;
                                    cnt = 0;
                                    System.out.println(code + " " + quantity + " " + discount + " " + price);
                                    list.add(new CartItemFood(fda.getFoodById(code), quantity, discount, price));
                                }
                            } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
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
                                    k1 = 0;
                                    cnt = 0;
                                    listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                                }
                            }

                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        priceF += (list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount()) * list.get(i).getQuantity();
                    }
                    for (int i = 0; i < listT.size(); i++) {
                        priceT += listT.get(i).getTicket().getPrice() - listT.get(i).getTicket().getPrice() * listT.get(i).getTicket().getDiscount();
                    }
                    price = priceF + priceT;

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
                    PointDAO pd = new PointDAO();
                    AccountPoint ap = pd.getAccountPoint(a.getUserName());
                    int point;
                    if (ap != null) {
                        point = ap.getPoint();
                    } else {
                        point = 0;
                    }

                    EventDAO evd = new EventDAO();
                    List<model.Event> evAchieve = new ArrayList<>();

                    String cin_raw = request.getParameter("loc");
                    int cinID = 1;
                    System.out.println(cin_raw);
                    try {
                        cinID = Integer.parseInt(cin_raw);
                        if (cnd.getCinemaByID(cinID) == null) {
                            throw new Exception("L");
                        }
                    } catch (Exception e) {
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }

                    if (!listT.isEmpty()) {
                        for (int j = 0; j < listT.size(); j++) {

                            int evC[] = evd.getEventCodeByCin(listT.get(j).getTicket().getCinID());
                            if (evC.length > 0) {
                                for (int i = 0; i < evC.length; i++) {
                                    int ev[] = evd.checkEventMov(listT.get(j).getTicket().getMovie().getMovID(), evC[i]);
                                    if (ev.length > 0) {

                                        for (int l = 0; l < ev.length; l++) {
                                            evAchieve.add(evd.getEventByCode(ev[l]));
                                        }

                                    }
                                    int ev2[] = evd.checkEventOrder(priceT, evC[i], "TK");
                                    if (ev2.length > 0) {
                                        for (int l = 0; l < ev2.length; l++) {
                                            evAchieve.add(evd.getEventByCode(ev2[l]));
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (!list.isEmpty()) {
                        int evC[] = evd.getEventCodeByCin(cinID);
                        if (evC.length > 0) {
                            for (int i = 0; i < evC.length; i++) {
                                int ev2[] = evd.checkEventOrder(priceF, evC[i], "FD");
                                if (ev2.length > 0) {
                                    evAchieve.add(evd.getEventByCode(ev2[i]));
                                }
                            }
                        }
                    }
                    for (int i = 0; i < evAchieve.size() - 1; i++) {
                        for (int j = i + 1; j < evAchieve.size(); j++) {
                            if (evAchieve.get(i).getEventCode() == evAchieve.get(j).getEventCode()) {
                                evAchieve.remove(i);
                            }
                        }
                    }
                    fd = new FoodDAO();
                    List<Food> f = new ArrayList<>();
                    discount = 0;
                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    double tpt = 0;
                    for (int i = 0; i < evAchieve.size(); i++) {
                        if (evAchieve.get(i).getEventType() == 1) {
                            if (evd.getEventDiscountO(evAchieve.get(i).getEventCode()) != null) {
                                discount = evd.getEventDiscountO(evAchieve.get(i).getEventCode()).getDiscount();
                                String pc = decimalFormat.format(discount * 100);
                                if (evd.getEventDiscountO(evAchieve.get(i).getEventCode()).getType().equals("FD")) {
                                    tpt = priceF - priceF * discount + priceT;
                                } else if (evd.getEventDiscountO(evAchieve.get(i).getEventCode()).getType().equals("TK")) {
                                    tpt = priceT - priceT * discount + priceF;
                                }
                                if (discount != 0) {
                                    request.setAttribute("tpt", decimalFormat.format(tpt));
                                    request.setAttribute("discount", pc);
                                }
                            } else if (evd.getEventDiscountM(evAchieve.get(i).getEventCode()) != null) {
                                discount = evd.getEventDiscountM(evAchieve.get(i).getEventCode()).getDiscount();
                                String pc = decimalFormat.format(discount * 100);
                                tpt = priceT - priceT * discount + priceF;
                                if (discount != 0) {
                                    request.setAttribute("tpt", decimalFormat.format(tpt));
                                    request.setAttribute("discount", pc);
                                }
                            }
                        } else if (evAchieve.get(i).getEventType() == 2) {
                            if (evd.getAllProductInEvent(evAchieve.get(i).getEventCode()) != null) {
                                List<String> pr = evd.getAllProductInEvent(evAchieve.get(i).getEventCode()).getProduct();
                                for (int j = 0; j < pr.size(); j++) {
                                    f.add(fd.getFoodById(pr.get(j)));
                                }
                            }
                        }
                    }
                    if (!evAchieve.isEmpty()) {
                        request.setAttribute("ev", evAchieve);
                    }
                    if (!f.isEmpty()) {
                        request.setAttribute("f", f);
                    }
                    double maxPointUse = Math.floor((price / 1000) * 90 / 100);
                    if (list.isEmpty()) {
                        request.setAttribute("noF", 1);
                    }
                    request.setAttribute("point", decimalFormat.format(point));
                    request.setAttribute("cinID", cinID);
                    request.setAttribute("maxPoint", decimalFormat.format(maxPointUse));
                    request.setAttribute("price", decimalFormat.format(price));
                    List<LocationCinMD> loc = cnd.getAllCinemaNameAndLoc();

                    request.setAttribute("loc", loc);
                    request.setAttribute("date", dte);
                    request.setAttribute("listCart", list);
                    request.setAttribute("listTicket", listT);
                    request.getRequestDispatcher("payment.jsp").forward(request, response);
                }
            } else if (request.getParameter("check").equals("0")) {
                Cookie[] arr = request.getCookies();
                List<CartItemFood> list = new ArrayList<>();
                List<CartItemTicket> listT = new ArrayList<>();
                FoodDAO fda = new FoodDAO();
                TicketDAO tkd = new TicketDAO();
                String cart = "";

                HttpSession session = request.getSession();
                Account a = (Account) session.getAttribute("account");
                CinemaDAO cnd = new CinemaDAO();
                double price = 0;
                double priceT = 0;
                double priceF = 0;
                List<LocationCinMD> loc = cnd.getAllCinemaNameAndLoc();
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
                arr = request.getCookies();
                list = new ArrayList<>();
                listT = new ArrayList<>();
                fda = new FoodDAO();
                tkd = new TicketDAO();
                cart = "";
                Cookie user = null;
                if (a != null) {
                    for (Cookie i : arr) {
                        if (i.getName().equals(a.getUserName())) {
                            user = i;
                            cart = i.getValue();
                            break;
                        }
                    }
                    FoodDAO fd = new FoodDAO();
                    List<String> listHH = fd.getAllFoodOff();
                    List<Ticket> tkB = tkd.getAllTicketBought();
                    int p = 0;
                    int cntT = 0, cntF = 0;
                    String code = "";
                    int cnt = 0;
                    int k1 = 0;
                    price = 0;
                    int quantity = 0;
                    double discount = 0;
                    for (int i = 0; i < cart.length(); i++) {
                        if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                            cntT = 0;
                            cntF = 0;
                            if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                                for (int j = 0; j < listHH.size(); j++) {
                                    if (cart.substring(i + 1, i + 7).equals(listHH.get(j))) {
                                        cntF++;
                                        p++;
                                        cart = cart.substring(0, i + 1) + cart.substring(i + 7);
                                    }
                                }
                                if (cntF == 0) {
                                    for (int j = i; j < cart.length(); j++) {
                                        if (cart.charAt(j) == 'p' && k1 == 0) {
                                            code = cart.substring(i + 1, j);
                                            cnt = j;
                                            k1++;
                                        } else if (cart.charAt(j) == 'p' && k1 == 1) {
                                            quantity = Integer.parseInt(cart.substring(cnt + 1, j));
                                            cnt = j;
                                            k1++;
                                        } else if (cart.charAt(j) == 'p' && k1 == 2) {
                                            price = Double.parseDouble(cart.substring(cnt + 1, j));
                                            cnt = j;
                                            k1++;
                                        } else if ((cart.charAt(j) == '/' && k1 == 3) || j == cart.length() - 1 && k1 == 3) {
                                            if (cart.charAt(j) == '/' && k1 == 3 && k1 == 3) {
                                                discount = Double.parseDouble(cart.substring(cnt + 1, j));
                                            } else if (j == cart.length() - 1 && k1 == 3) {
                                                discount = Double.parseDouble(cart.substring(cnt + 1));
                                            }
                                            cnt = j;
                                            k1++;
                                            break;
                                        }
                                    }
                                    k1 = 0;
                                    cnt = 0;
                                    System.out.println(code + " " + quantity + " " + discount + " " + price);
                                    list.add(new CartItemFood(fda.getFoodById(code), quantity, discount, price));
                                }
                            } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
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
                                    k1 = 0;
                                    cnt = 0;
                                    listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                                }
                            }

                        }
                    }

                    for (int i = 0; i < list.size(); i++) {
                        priceF += (list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount()) * list.get(i).getQuantity();
                    }
                    for (int i = 0; i < listT.size(); i++) {
                        priceT += listT.get(i).getTicket().getPrice() - listT.get(i).getTicket().getPrice() * listT.get(i).getTicket().getDiscount();
                    }
                    price = priceF + priceT;
                    PointDAO pd = new PointDAO();
                    AccountPoint ap = pd.getAccountPoint(a.getUserName());
                    int point;
                    if (ap != null) {
                        point = ap.getPoint();
                    } else {
                        point = 0;
                    }
                    double maxPointUse = Math.floor((price / 1000) * 90 / 100);
                    if (checkEmail(email) == false) {
                        String ms = "Vui lòng nhập đúng email";
                        request.setAttribute("ms", ms);
                        request.setAttribute("loc", loc);
                        request.setAttribute("listCart", list);
                        request.setAttribute("date", dte);
                        request.setAttribute("point", point);
                        request.setAttribute("maxPoint", maxPointUse);
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
                        request.setAttribute("point", point);
                        request.setAttribute("maxPoint", maxPointUse);
                        request.setAttribute("listTicket", listT);
                        request.getRequestDispatcher("payment.jsp").forward(request, response);
                    }

                    if (request.getParameter("pm") == null) {
                        String ms = "Vui lòng chọn cách thanh toán";
                        request.setAttribute("ms", ms);
                        request.setAttribute("point", point);
                        request.setAttribute("maxPoint", maxPointUse);
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
                        request.setAttribute("point", point);
                        request.setAttribute("maxPoint", maxPointUse);
                        request.setAttribute("listTicket", listT);
                        request.getRequestDispatcher("payment.jsp").forward(request, response);
                    } else if (request.getParameter("dte").equals("")) {
                        String ms = "Vui lòng chọn ngày nhận";
                        request.setAttribute("ms", ms);
                        request.setAttribute("loc", loc);
                        request.setAttribute("point", point);
                        request.setAttribute("maxPoint", maxPointUse);
                        request.setAttribute("listCart", list);
                        request.setAttribute("date", dte);
                        request.setAttribute("listTicket", listT);
                        request.getRequestDispatcher("payment.jsp").forward(request, response);
                    }
                    if (!request.getParameter("pass").equals(a.getPassword())) {
                        String ms = "Sai mật khẩu";
                        request.setAttribute("ms", ms);
                        request.setAttribute("point", point);
                        request.setAttribute("maxPoint", maxPointUse);
                        request.setAttribute("loc", loc);
                        request.setAttribute("listCart", list);
                        request.setAttribute("date", dte);
                        request.setAttribute("listTicket", listT);
                        request.getRequestDispatcher("payment.jsp").forward(request, response);
                    } else {
                        System.out.println("nk");
                        String m = "THANH TOÁN THÀNH CÔNG. VUI LÒNG VÀO LỊCH SỬ GIAO DỊCH ĐỂ XEM MÃ VÉ, MÃ ĐỒ ĂN CỦA BẠN";
                        String datePick = "";
                        Date dateStart = null;
                        Date dateEnd = null;
                        Time timeStart = null;
                        Time timeEnd = null;
                        for (int i = 0; i < dte.size(); i++) {

                            if (Integer.parseInt(request.getParameter("dte")) == dte.get(i).getId()) {
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

                        try {
                            cinID = Integer.parseInt(lOc);
                        } catch (Exception e) {
                        }
                        OrderDAO ord = new OrderDAO();
                        int id = 0;
                        EventDAO evd = new EventDAO();
                        List<model.Event> evAchieve = new ArrayList<>();
                        if (!list.isEmpty() || !listT.isEmpty()) {
                            for (int j = 0; j < listT.size(); j++) {

                                int evC[] = evd.getEventCodeByCin(listT.get(j).getTicket().getCinID());
                                System.out.println("lem" + evC.length);
                                if (evC.length > 0) {
                                    for (int i = 0; i < evC.length; i++) {
                                        int ev[] = evd.checkEventMov(listT.get(j).getTicket().getMovie().getMovID(), evC[i]);
                                        if (ev.length > 0) {

                                            for (int l = 0; l < ev.length; l++) {
                                                evAchieve.add(evd.getEventByCode(ev[l]));
                                            }

                                        }
                                        int ev2[] = evd.checkEventOrder(priceT, evC[i], "TK");
                                        System.out.println("lem2" + ev2.length);
                                        if (ev2.length > 0) {
                                            for (int l = 0; l < ev2.length; l++) {
                                                evAchieve.add(evd.getEventByCode(ev2[l]));
                                            }
                                        }
                                    }
                                }
                            }
                            if (!list.isEmpty()) {
                                int evC[] = evd.getEventCodeByCin(cinID);
                                if (evC.length > 0) {
                                    for (int i = 0; i < evC.length; i++) {
                                        int ev2[] = evd.checkEventOrder(priceF, evC[i], "FD");
                                        if (ev2.length > 0) {
                                            evAchieve.add(evd.getEventByCode(ev2[i]));
                                        }
                                    }

                                }
                            }
                            for (int i = 0; i < evAchieve.size() - 1; i++) {
                                for (int j = i + 1; j < evAchieve.size(); j++) {
                                    if (evAchieve.get(i).getEventCode() == evAchieve.get(j).getEventCode()) {
                                        evAchieve.remove(i);
                                    }
                                }
                            }

                            id = ord.insert(a.getUserName(), request.getParameter("fName"), request.getParameter("lName"), request.getParameter("sdt"), request.getParameter("email"), request.getParameter("cntry"), request.getParameter("strt"), request.getParameter("dist"), request.getParameter("city"), pm, dd, tt);
                            System.out.println("id" + id);
                            for (int i = 0; i < evAchieve.size(); i++) {
                                evd.insertEventOrderOnline("ONL" + id, evAchieve.get(i).getEventCode());
                            }
                        }
                        if (id != 0) {
                            String orderID = "ONL" + id;
                            OrderDetailDAO odd = new OrderDetailDAO();
                            TransactionCDAO tcd = new TransactionCDAO();
                            OrderTicketDetailDAO otd = new OrderTicketDetailDAO();
                            double price1 = 0;
                            System.out.println("masa" + cinID);
                            if (!list.isEmpty()) {
                                String fdCode = orderID + "fd" + randomAlpha(18 - orderID.length());
                                tcd.insert(orderID, fdCode, 1, dateStart, timeStart, dateEnd, timeEnd, cinID);
                                for (int i = 0; i < list.size(); i++) {
                                    odd.insert(orderID, list.get(i).getFood().getProductCode(), list.get(i).getFood().getDiscount(), list.get(i).getFood().getPrice(), list.get(i).getQuantity());
                                    price1 += ((list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount()) * list.get(i).getQuantity());
                                }
                            }
                            RoomDAO rmd = new RoomDAO();
                            SeatDAO sd = new SeatDAO();
                            if (!listT.isEmpty()) {
                                int cID = listT.get(0).getTicket().getCinID();
                                String tkCode = orderID + "tk" + randomAlpha(18 - orderID.length());
                                tcd.insert(orderID, tkCode, 2, dateStart, timeStart, dateEnd, timeEnd, cID);

                                for (int i = 0; i < listT.size(); i++) {
                                    Room r = rmd.getRoomByTCode(listT.get(i).getTicket().getProductCode());
                                    if (r == null) {
                                        request.getRequestDispatcher("error.jsp").forward(request, response);
                                    }
                                    otd.insert(orderID, listT.get(i).getTicket().getProductCode(), listT.get(i).getSeat().substring(0, 1), Integer.parseInt(listT.get(i).getSeat().substring(1, 2)), listT.get(i).getTicket().getDiscount(), listT.get(i).getTicket().getPrice(), sd.getTypeByCALCR(r.getRoomID(), cID, Integer.parseInt(listT.get(i).getSeat().substring(1, 2)), listT.get(i).getSeat().substring(0, 1)));
                                    price1 += (listT.get(i).getTicket().getPrice() - listT.get(i).getTicket().getPrice() * listT.get(i).getTicket().getDiscount());

                                    if (listT.get(i).getTicket().getCinID() != cID) {
                                        tkCode = orderID + randomAlpha(20 - orderID.length());
                                        cID = listT.get(i).getTicket().getCinID();
                                        tcd.insert(orderID, tkCode, 2, dateStart, timeStart, dateEnd, timeEnd, listT.get(i).getTicket().getCinID());
                                    }

                                }

                            }
                            double b = Math.round(price1 * 100);
                            b = b / 100;
                            int point1 = (int) b / 1000;

                            if (pd.checkAcc(a.getUserName()) != null) {
                                if (!list.isEmpty() || !listT.isEmpty()) {
                                    pd.insertIntoAPD(a.getUserName(), point1, orderID, dd, tt, "NOTUSED");
                                    if (request.getParameter("pntUse") == null || request.getParameter("pntUse").equals("")) {
                                        pd.updPoint(a.getUserName(), point1);
                                    } else {
                                        int pointUse = Integer.parseInt(request.getParameter("pntUse"));
                                        pd.insertIntoAUP(a.getUserName(), pointUse, orderID, dd, tt);
                                        System.out.println(point1 - pointUse);
                                        pd.updPoint(a.getUserName(), point1 - pointUse);
                                        List<AccountPoint> listAP = pd.getAccountPointDetail(a.getUserName());
                                        for (int i = 0; i < listAP.size(); i++) {
                                            System.out.println(listAP.get(i).getTime());
                                        }
                                        int checkStat = 0;
                                        for (int i = 0; i < listAP.size(); i++) {
                                            checkStat += listAP.get(i).getPoint();
                                            if (pd.getAllAccountUsedPoint(a.getUserName()) >= checkStat) {
                                                pd.updStatusAPD(a.getUserName(), listAP.get(i).getOrderID(), "USED");
                                            }
                                        }
                                    }
                                }
                            } else {
                                pd.insertIntoAP(a.getUserName(), point1);
                                pd.insertIntoAPD(a.getUserName(), point1, orderID, dd, tt, "NOTUSED");
                            }

                            removeCookie(response, user.getName());
                            System.out.println("pk");
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
                }

            }
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
