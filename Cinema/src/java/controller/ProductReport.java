/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.FoodDAO;
import dal.MovieDAO;
import dal.RateDAO;
import dal.ScheDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cinema;
import model.Food;
import model.Movies;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class ProductReport extends HttpServlet {

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
            out.println("<title>Servlet ProductReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductReport at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (request.getParameter("id") == null) {
            if (type != null) {
                request.setAttribute("type", type);
                request.getRequestDispatcher("prRp.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } else {

            String id_raw = request.getParameter("id");
            CinemaDAO cnd = new CinemaDAO();
            String start_raw = request.getParameter("start");
            String end_raw = request.getParameter("end");
            if (!start_raw.equals("") && !end_raw.equals("")) {
                int cinID = 1;
                try {
                    cinID = Integer.parseInt(id_raw);
                    if (cnd.getCinemaByID(cinID) == null) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                if (type != null) {
                    if (type.equals("TK")) {
                        int cnt = 0;
                        String yearS = "", monthS = "", dateS = "";
                        String yearE = "", monthE = "", dateE = "";
                        String t = start_raw;
                        Date start = null;
                        Date end = null;
                        try {
                            start = Date.valueOf(start_raw);
                            end = Date.valueOf(end_raw);
                        } catch (Exception e) {
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        for (int i = 0; i < t.length(); i++) {
                            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                                yearS = t.substring(cnt, i);
                                cnt = i;
                            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                                monthS = t.substring(cnt + 1, i);
                                cnt = i;
                            }
                        }
                        dateS = t.substring(cnt + 1);
                        cnt = 0;
                        t = end_raw;
                        for (int i = 0; i < t.length(); i++) {
                            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                                yearE = t.substring(cnt, i);
                                cnt = i;
                            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                                monthE = t.substring(cnt + 1, i);
                                cnt = i;
                            }
                        }
                        dateE = t.substring(cnt + 1);

                        TicketDAO tkd = new TicketDAO();

                        //numTick
                        int numTick = tkd.getNumTickSellByDateAC(start, end, cinID);
                        System.out.println(numTick);
                        int numTickOfAllTime = tkd.getNumTickSellOfAllTimeAC(cinID);
                        System.out.println("1");
                        String pcNumTick = decimalFormat.format((double) numTick / (double) numTickOfAllTime * 100);

                        //tickType
                        List<TIcketDate> listTID = new ArrayList<>();
                        List<String> listType = tkd.getAllTickTypeByDateAC(start, end, cinID);
                        for (int i = 0; i < listType.size(); i++) {
                            String tpe = "";
                            if (listType.get(i).equals("NM")) {
                                tpe = "Thường";
                            } else if (listType.get(i).equals("VP")) {
                                tpe = "VIP";
                            } else if (listType.get(i).equals("VT")) {
                                tpe = "Đôi";
                            }
                            int numSellType = tkd.getNumTickTypeSellByTimeAC(start, end, 0, listType.get(i), cinID);
                            String a = decimalFormat.format((double) numSellType / (double) numTick * 100);
                            TIcketDate ticd = new TIcketDate(tpe, numSellType);
                            ticd.setPc(a);
                            listTID.add(ticd);
                        }

                        //date (cn, T2, ...)
                        int arrD = (int) ((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000) + 1);
                        Date d[] = new Date[arrD];
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String k = formatter.format(start);
                        d[0] = start;
                        Calendar c1 = Calendar.getInstance();
                        c1.setTime(start);
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

                        t = "2023-05-01";
                        List<Date> cn = new ArrayList<>();
                        List<Date> t2 = new ArrayList<>();
                        List<Date> t3 = new ArrayList<>();
                        List<Date> t4 = new ArrayList<>();
                        List<Date> t5 = new ArrayList<>();
                        List<Date> t6 = new ArrayList<>();
                        List<Date> t7 = new ArrayList<>();
                        for (int i = 0; i < d.length; i++) {
                            long time = d[i].getTime() - Date.valueOf(t).getTime();
                            time = (time / (24 * 60 * 60 * 1000)) % 7;
                            if (time == 0) {
                                t2.add(d[i]);
                            } else if (time == 1) {
                                t3.add(d[i]);
                            } else if (time == 2) {
                                t4.add(d[i]);
                            } else if (time == 3) {
                                t5.add(d[i]);
                            } else if (time == 4) {
                                t6.add(d[i]);
                            } else if (time == 5) {
                                t7.add(d[i]);
                            } else if (time == 6) {
                                cn.add(d[i]);
                            }
                        }
                        List<TIcketDate> listTID3 = new ArrayList<>();
                        if (numTick == 0) {
                            String PC = "0";
                            listTID3.add(new TIcketDate("Thứ 2", tkd.getNumTickSellByDayAC(t2, cinID), PC));
                            listTID3.add(new TIcketDate("Thứ 3", tkd.getNumTickSellByDayAC(t3, cinID), PC));
                            listTID3.add(new TIcketDate("Thứ 4", tkd.getNumTickSellByDayAC(t4, cinID), PC));
                            listTID3.add(new TIcketDate("Thứ 5", tkd.getNumTickSellByDayAC(t5, cinID), PC));
                            listTID3.add(new TIcketDate("Thứ 6", tkd.getNumTickSellByDayAC(t6, cinID), PC));
                            listTID3.add(new TIcketDate("Thứ 7", tkd.getNumTickSellByDayAC(t7, cinID), PC));
                            listTID3.add(new TIcketDate("Chủ nhật", tkd.getNumTickSellByDayAC(cn, cinID), PC));
                        } else {
                            String PC1 = "";
                            String PC2 = "";
                            String PC3 = "";
                            String PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(t2, cinID) / (double) numTick);
                            if (tkd.getNumTickSellByDayAC(t2, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t2, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t2, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t2, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(t2, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t2, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(t2, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTID3.add(new TIcketDate("Thứ 2", tkd.getNumTickSellByDayAC(t2, cinID), PC));

                            List<TIcketDate> listTIDte = new ArrayList<>();
                            if (tkd.getNumTickSellByDayAC(t2, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t2, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t2, cinID) * 100);
                            } else {
                                PC1 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(t2, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(t2, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(t2, "VT", cinID), PC3));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();

                            //T3
                            if (tkd.getNumTickSellByDayAC(t3, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t3, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t3, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t3, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(t3, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t3, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(t3, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(t3, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(t3, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(t3, "VT", cinID), PC3));
                            PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(t3, cinID) / (double) numTick * 100);
                            listTID3.add(new TIcketDate("Thứ 3", tkd.getNumTickSellByDayAC(t3, cinID), PC));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();

                            //T4
                            if (tkd.getNumTickSellByDayAC(t4, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t4, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t4, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t4, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(t4, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t4, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(t4, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(t4, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(t4, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(t4, "VT", cinID), PC3));
                            PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(t4, cinID) / (double) numTick * 100);
                            listTID3.add(new TIcketDate("Thứ 4", tkd.getNumTickSellByDayAC(t4, cinID), PC));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();

                            //T5
                            if (tkd.getNumTickSellByDayAC(t5, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t5, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t5, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t5, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(t5, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t5, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(t5, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(t5, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(t5, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(t5, "VT", cinID), PC3));
                            PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(t5, cinID) / (double) numTick * 100);
                            listTID3.add(new TIcketDate("Thứ 5", tkd.getNumTickSellByDayAC(t5, cinID), PC));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();

                            //T6
                            if (tkd.getNumTickSellByDayAC(t6, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t6, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t6, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t6, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(t6, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t6, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(t6, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(t6, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(t6, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(t6, "VT", cinID), PC3));
                            PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(t6, cinID) / (double) numTick * 100);
                            listTID3.add(new TIcketDate("Thứ 6", tkd.getNumTickSellByDayAC(t6, cinID), PC));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();

                            //T7
                            if (tkd.getNumTickSellByDayAC(t7, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t7, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(t7, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t7, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(t7, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(t7, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(t7, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(t7, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(t7, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(t7, "VT", cinID), PC3));
                            PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(t7, cinID) / (double) numTick * 100);
                            listTID3.add(new TIcketDate("Thứ 7", tkd.getNumTickSellByDayAC(t7, cinID), PC));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();

                            //CN
                            if (tkd.getNumTickSellByDayAC(cn, cinID) != 0) {
                                PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(cn, "NM", cinID) / (double) tkd.getNumTickSellByDayAC(cn, cinID) * 100);
                                PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(cn, "VP", cinID) / (double) tkd.getNumTickSellByDayAC(cn, cinID) * 100);
                                PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDayAC(cn, "VT", cinID) / (double) tkd.getNumTickSellByDayAC(cn, cinID) * 100);
                            } else {
                                PC1 = "0";
                                PC2 = "0";
                                PC3 = "0";
                            }
                            listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDayAC(cn, "NM", cinID), PC1));
                            listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDayAC(cn, "VP", cinID), PC2));
                            listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDayAC(cn, "VT", cinID), PC3));
                            PC = decimalFormat.format((double) tkd.getNumTickSellByDayAC(cn, cinID) / (double) numTick * 100);
                            listTID3.add(new TIcketDate("Chủ nhật", tkd.getNumTickSellByDayAC(cn, cinID), PC));
                            listTID3.get(listTID3.size() - 1).setTkd(listTIDte);

                            listTIDte = new ArrayList<>();
                        }

                        //hourTick
                        String ts1 = "08:00:00", te1 = "09:59:00";
                        String ts2 = "10:00:00", te2 = "12:29:00";
                        String ts3 = "12:30:00", te3 = "14:29:00";
                        String ts4 = "14:30:00", te4 = "17:29:00";
                        String ts5 = "17:30:00", te5 = "20:29:00";
                        String ts6 = "20:30:00", te6 = "22:00:00";

                        List<TIcketDate> listTID4 = new ArrayList<>();

                        if (numTick == 0) {
                            String PC = "0";
                            listTID4.add(new TIcketDate(ts1 + " - " + te1, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID), PC));
                            listTID4.add(new TIcketDate(ts2 + " - " + te2, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID), PC));
                            listTID4.add(new TIcketDate(ts3 + " - " + te3, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID), PC));
                            listTID4.add(new TIcketDate(ts4 + " - " + te4, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID), PC));
                            listTID4.add(new TIcketDate(ts5 + " - " + te5, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID), PC));
                            listTID4.add(new TIcketDate(ts6 + " - " + te6, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID), PC));
                        } else {
                            String PC = "";
                            List<TIcketDate> listTIDte = new ArrayList<>();
                            PC = decimalFormat.format((double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) numTick * 100);
                            listTID4.add(new TIcketDate(ts1 + " - " + te1, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID), PC));
                            if (tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) != 0) {
                                listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHADAC(t2, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t2, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHADAC(t3, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t3, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHADAC(t4, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t4, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHADAC(t5, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t5, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHADAC(t6, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t6, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHADAC(t7, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t7, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHADAC(cn, Time.valueOf(ts1), Time.valueOf(te1), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(cn, Time.valueOf(ts1), Time.valueOf(te1), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts1), Time.valueOf(te1), cinID) * 100)));
                                listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                                listTIDte = new ArrayList<>();
                            }

                            PC = decimalFormat.format((double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) numTick * 100);
                            listTID4.add(new TIcketDate(ts2 + " - " + te2, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID), PC));
                            if (tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) != 0) {
                                listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHADAC(t2, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t2, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHADAC(t3, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t3, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHADAC(t4, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t4, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHADAC(t5, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t5, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHADAC(t6, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t6, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHADAC(t7, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t7, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHADAC(cn, Time.valueOf(ts2), Time.valueOf(te2), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(cn, Time.valueOf(ts2), Time.valueOf(te2), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts2), Time.valueOf(te2), cinID) * 100)));
                                listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                                listTIDte = new ArrayList<>();
                            }

                            PC = decimalFormat.format((double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) numTick * 100);
                            listTID4.add(new TIcketDate(ts3 + " - " + te3, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID), PC));
                            if (tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) != 0) {
                                listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHADAC(t2, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t2, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHADAC(t3, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t3, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHADAC(t4, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t4, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHADAC(t5, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t5, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHADAC(t6, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t6, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHADAC(t7, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t7, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHADAC(cn, Time.valueOf(ts3), Time.valueOf(te3), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(cn, Time.valueOf(ts3), Time.valueOf(te3), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts3), Time.valueOf(te3), cinID) * 100)));
                                listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                                listTIDte = new ArrayList<>();
                            }

                            PC = decimalFormat.format((double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) numTick * 100);
                            listTID4.add(new TIcketDate(ts4 + " - " + te4, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID), PC));
                            if (tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) != 0) {
                                listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHADAC(t2, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t2, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHADAC(t3, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t3, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHADAC(t4, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t4, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHADAC(t5, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t5, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHADAC(t6, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t6, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHADAC(t7, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t7, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHADAC(cn, Time.valueOf(ts4), Time.valueOf(te4), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(cn, Time.valueOf(ts4), Time.valueOf(te4), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts4), Time.valueOf(te4), cinID) * 100)));
                                listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                                listTIDte = new ArrayList<>();
                            }

                            PC = decimalFormat.format((double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) numTick * 100);
                            listTID4.add(new TIcketDate(ts5 + " - " + te5, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID), PC));
                            if (tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) != 0) {
                                listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHADAC(t2, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t2, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHADAC(t3, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t3, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHADAC(t4, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t4, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHADAC(t5, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t5, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHADAC(t6, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t6, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHADAC(t7, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t7, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHADAC(cn, Time.valueOf(ts5), Time.valueOf(te5), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(cn, Time.valueOf(ts5), Time.valueOf(te5), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts5), Time.valueOf(te5), cinID) * 100)));
                                listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                                listTIDte = new ArrayList<>();
                            }

                            PC = decimalFormat.format((double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) numTick * 100);
                            listTID4.add(new TIcketDate(ts6 + " - " + te6, tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID), PC));
                            if (tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) != 0) {
                                listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHADAC(t2, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t2, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHADAC(t3, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t3, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHADAC(t4, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t4, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHADAC(t5, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t5, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHADAC(t6, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t6, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHADAC(t7, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(t7, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHADAC(cn, Time.valueOf(ts6), Time.valueOf(te6), cinID), decimalFormat.format((double) tkd.getNumTickByHADAC(cn, Time.valueOf(ts6), Time.valueOf(te6), cinID) / (double) tkd.getNumTickByHourAC(start, end, Time.valueOf(ts6), Time.valueOf(te6), cinID) * 100)));
                                listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                                listTIDte = new ArrayList<>();
                            }
                        }
                        System.out.println("6");
                        //Cinema
                        List<Cinema> cin = tkd.getAllCinemaSellTicketByDate(start, end);

                        List<TIcketDate> listTID5 = new ArrayList<>();
                        for (int i = 0; i < cin.size(); i++) {
                            if (numTick == 0) {
                                String PC = "0";
                                listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()), PC));
                            } else {
                                String PC = decimalFormat.format((double) tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()) / (double) numTick * 100);
                                listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()), PC));
                            }

                        }

                        //onl off
                        TIcketDate listTIDOnl;
                        TIcketDate listTIDOff;

                        if (numTick == 0) {
                            listTIDOnl = new TIcketDate("Bán online", tkd.getNumTickSellByOOADAC(start, end, "ONL", cinID), "0");
                            listTIDOff = new TIcketDate("Bán trực tiếp", tkd.getNumTickSellByOOADAC(start, end, "OFF", cinID), "0");
                        } else {
                            listTIDOnl = new TIcketDate("Bán online", tkd.getNumTickSellByOOADAC(start, end, "ONL", cinID), decimalFormat.format((double) tkd.getNumTickSellByOOADAC(start, end, "ONL", cinID) / (double) numTick));
                            listTIDOff = new TIcketDate("Bán trực tiếp", tkd.getNumTickSellByOOADAC(start, end, "OFF", cinID), decimalFormat.format((double) tkd.getNumTickSellByOOADAC(start, end, "OFF", cinID) / (double) numTick));
                        }

                        //date
                        List<TIcketDate> listTID6 = tkd.getAllTicketBoughtDateByTimeAC(start, end, 0, cinID);

                        for (int i = 0; i < listTID6.size(); i++) {
                            String p = "";
                            cnt = 0;
                            t = "";
                            for (int j = 0; j < listTID6.get(i).getdS().length(); j++) {

                                if (listTID6.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                    p += listTID6.get(i).getdS().substring(0, j);
                                    cnt = j;
                                } else if (listTID6.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                    t = p;
                                    p = listTID6.get(i).getdS().substring(cnt, j + 1);
                                    p += t;
                                    cnt = j;
                                    break;
                                }
                            }
                            t = p;
                            p = listTID6.get(i).getdS().substring(cnt + 1);
                            p += t;
                            listTID6.get(i).setNo(tkd.getNumTickByDateAC(start, end, 0, Date.valueOf(p), cinID));
                            if (numTick != 0) {
                                listTID6.get(i).setPc(decimalFormat.format((double) listTID6.get(i).getNo() / (double) numTick * 100));
                            } else {
                                listTID6.get(i).setPc("0");
                            }
                        }
                        System.out.println("7");
                        //numSellEachDay
                        for (int i = 0; i < listTID6.size(); i++) {
                            String p = "";
                            cnt = 0;
                            t = "";
                            for (int j = 0; j < listTID6.get(i).getdS().length(); j++) {

                                if (listTID6.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                    p += listTID6.get(i).getdS().substring(0, j);
                                    cnt = j;
                                } else if (listTID6.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                    t = p;
                                    p = listTID6.get(i).getdS().substring(cnt, j + 1);
                                    p += t;
                                    cnt = j;
                                    break;
                                }
                            }
                            t = p;
                            p = listTID6.get(i).getdS().substring(cnt + 1);
                            p += t;

                            int numInDay = listTID6.get(i).getNo();

                            List<TIcketDate> TID7 = new ArrayList<>();
                            if (numInDay != 0) {
                                String PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "NM", cinID) / (double) numInDay * 100);
                                TID7.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "NM", cinID), PC));

                                PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VP", cinID) / (double) numInDay * 100);
                                TID7.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VP", cinID), PC));

                                PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VT", cinID) / (double) numInDay * 100);
                                TID7.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VT", cinID), PC));
                                listTID6.get(i).setTkd(TID7);
                            } else {
                                String PC = "0";
                                TID7.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "NM", cinID), PC));
                                TID7.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VP", cinID), PC));
                                TID7.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VT", cinID), PC));
                                listTID6.get(i).setTkd(TID7);
                            }
                        }

                        //phim ban dc ve va phim chua ban dc ve
                        ScheDAO scd = new ScheDAO();
                        MovieDAO mvd = new MovieDAO();
                        RateDAO rd = new RateDAO();
                        List<Movies> listM = tkd.getAllMovSellTicketByDateAC(start, end, cinID);
                        for (int i = 0; i < listM.size(); i++) {
                            listM.get(i).setAllNumTick(tkd.getAllNumTickOfMoviesAC(start, end, listM.get(i).getMovID(), cinID));
                            listM.get(i).setNumTickSell(tkd.getNumTicketSellByTimeAC(start, end, listM.get(i).getMovID(), cinID));
                            if (listM.get(i).getAllNumTick() != 0) {
                                String PC = decimalFormat.format((double) listM.get(i).getNumTickSell() / (double) listM.get(i).getAllNumTick() * 100);
                                listM.get(i).setPcNumTickSell(PC);
                            } else {
                                String PC = "0";
                                listM.get(i).setPcNumTickSell(PC);
                            }
                            Movies m = listM.get(i);
                            m.setNoRate(rd.getNoRate(m.getMovID()));
                            m.setNoRate5(rd.getNoRate5(m.getMovID()));
                            m.setNoRate4(rd.getNoRate4(m.getMovID()));
                            m.setNoRate3(rd.getNoRate3(m.getMovID()));
                            m.setNoRate2(rd.getNoRate2(m.getMovID()));
                            m.setNoRate1(rd.getNoRate1(m.getMovID()));
                            m.setNoRate(rd.getNoRate(m.getMovID()));
                            m.setSumRate(rd.getSumRate(m.getMovID()));

                            String b, p5, p4, p3, p2, p1;
                            if (rd.getNoRate(m.getMovID()) != 0) {
                                b = decimalFormat.format((double) rd.getSumRate(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                                p5 = decimalFormat.format((double) rd.getNoRate5(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                                p4 = decimalFormat.format((double) rd.getNoRate4(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                                p3 = decimalFormat.format((double) rd.getNoRate3(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                                p2 = decimalFormat.format((double) rd.getNoRate2(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                                p1 = decimalFormat.format((double) rd.getNoRate1(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                            } else {
                                decimalFormat = new DecimalFormat("#");
                                b = decimalFormat.format(0);
                                p5 = decimalFormat.format(0);
                                p4 = decimalFormat.format(0);
                                p3 = decimalFormat.format(0);
                                p2 = decimalFormat.format(0);
                                p1 = decimalFormat.format(0);
                            }
                            m.setAvrRate(Double.parseDouble(b));
                            m.setpRate5(Double.parseDouble(p5));
                            m.setpRate4(Double.parseDouble(p4));
                            m.setpRate3(Double.parseDouble(p3));
                            m.setpRate2(Double.parseDouble(p2));
                            m.setpRate1(Double.parseDouble(p1));
                            listM.set(i, m);
                        }

                        List<Movies> listMNS = tkd.getAllMoviesSellTicketButNotBAC(start, end, cinID);
                        for (int i = 0; i < listMNS.size(); i++) {
                            listMNS.get(i).setAllNumTick(tkd.getAllNumTickOfMoviesAC(start, end, listMNS.get(i).getMovID(), cinID));
                            listMNS.get(i).setNumTickSell(tkd.getNumTicketSellByTimeAC(start, end, listMNS.get(i).getMovID(), cinID));
                            if (listMNS.get(i).getAllNumTick() != 0) {
                                String PC = decimalFormat.format((double) listMNS.get(i).getNumTickSell() / (double) listMNS.get(i).getAllNumTick() * 100);
                                listMNS.get(i).setPcNumTickSell(PC);
                            } else {
                                String PC = "0";
                                listMNS.get(i).setPcNumTickSell(PC);
                            }
                        }
                        if (listMNS.size() > 0) {
                            request.setAttribute("listMNS", listMNS);
                        } else {
                            request.setAttribute("msMNS", "Không có bộ phim nào chưa bán được vé");
                        }

                        //income
                        int numOnl = tkd.getOnlineIncomeByCinAD(start, end, cinID);
                        int numOff = tkd.getOfflineIncomeByCinAD(start, end, cinID);
                        int numIA = numOnl + numOff;

                        String PCnumONL = "0";
                        String PCnumOFF = "0";
                        if (numIA != 0) {
                            PCnumONL = decimalFormat.format((double) numOnl / (double) numIA * 100);
                            PCnumOFF = decimalFormat.format((double) numOff / (double) numIA * 100);
                        }
                        request.setAttribute("PCnumONL", PCnumONL);
                        request.setAttribute("PCnumOFF", PCnumOFF);
                        request.setAttribute("cinID", cnd.getCinemaByID(cinID));
                        request.setAttribute("numOnl", numOnl);
                        request.setAttribute("numOff", numOff);
                        request.setAttribute("numIA", numIA);
                        request.setAttribute("listM", listM);
                        request.setAttribute("listTID6", listTID6);
                        request.setAttribute("type", type);
                        request.setAttribute("listTIDOnl", listTIDOnl);
                        request.setAttribute("listTIDOff", listTIDOff);
                        request.setAttribute("listTID5", listTID5);
                        request.setAttribute("listTID4", listTID4);
                        request.setAttribute("listTID3", listTID3);
                        request.setAttribute("listTID", listTID);
                        request.setAttribute("pcNumTick", pcNumTick);
                        request.setAttribute("numTick", numTick);
                        request.setAttribute("startR", start);
                        request.setAttribute("endR", end);
                        request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                        request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                        request.getRequestDispatcher("tkRp.jsp").forward(request, response);
                    } else if (type.equals("FD")) {

                        //food
                        int cnt = 0;
                        String yearS = "", monthS = "", dateS = "";
                        String yearE = "", monthE = "", dateE = "";
                        String t = start_raw;
                        for (int i = 0; i < t.length(); i++) {
                            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                                yearS = t.substring(cnt, i);
                                cnt = i;
                            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                                monthS = t.substring(cnt + 1, i);
                                cnt = i;
                            }
                        }

                        dateS = t.substring(cnt + 1);
                        cnt = 0;
                        t = end_raw;
                        for (int i = 0; i < t.length(); i++) {
                            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                                yearE = t.substring(cnt, i);
                                cnt = i;
                            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                                monthE = t.substring(cnt + 1, i);
                                cnt = i;
                            }
                        }
                        dateE = t.substring(cnt + 1);

                        Date start = null;
                        Date end = null;

                        try {
                            start = Date.valueOf(start_raw);
                            end = Date.valueOf(end_raw);
                        } catch (Exception e) {
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        FoodDAO fd = new FoodDAO();
                        int numFood = fd.getNumFoodByDateAC(start, end, cinID);
                        int numFoodAllTime = fd.getNumFoodAC(cinID);

                        String pcF = "0";
                        if (numFoodAllTime != 0) {
                            pcF = decimalFormat.format((double) numFood / (double) numFoodAllTime * 100);
                        }

                        //foodType
                        List<TIcketDate> listTID = new ArrayList<>();
                        List<Food> ft = fd.getAllFoodTypeByDateAC(start, end, cinID);
                        for (int i = 0; i < ft.size(); i++) {
                            if (numFood == 0) {
                                String PC = "0";
                                listTID.add(new TIcketDate(ft.get(i).getFoodDescript(), fd.getNumFoodTypeSellByDateAType(start, end, ft.get(i).getFoodType()), PC));
                            } else {
                                String PC = decimalFormat.format((double) fd.getNumFoodTypeSellByDateATypeAC(start, end, ft.get(i).getFoodType(), cinID) / (double) numFood * 100);
                                listTID.add(new TIcketDate(ft.get(i).getTypeName(), fd.getNumFoodTypeSellByDateATypeAC(start, end, ft.get(i).getFoodType(), cinID), PC));
                            }
                        }

                        //cin
                        List<TIcketDate> listTID1 = new ArrayList<>();
                        List<Cinema> c = fd.getAllCinByDate(start, end);
                        for (int i = 0; i < c.size(); i++) {
                            if (numFood == 0) {
                                String PC = "0";
                                listTID1.add(new TIcketDate(c.get(i).getCinName(), fd.getNumFoodSellByCinAndDate(start, end, c.get(i).getCinID()), PC));
                            } else {
                                String PC = decimalFormat.format((double) fd.getNumFoodSellByCinAndDate(start, end, c.get(i).getCinID()) / (double) numFood * 100);
                                listTID1.add(new TIcketDate(c.get(i).getCinName(), fd.getNumFoodSellByCinAndDate(start, end, c.get(i).getCinID()), PC));
                            }
                        }

                        //onl off
                        TIcketDate tONL;
                        TIcketDate tOFF;
                        if (numFood == 0) {
                            String PC = "0";
                            tONL = new TIcketDate("Bán online", fd.getNumFoodByOnlAndOffAC(start, end, "ONL", cinID), PC);
                            tOFF = new TIcketDate("Bán trực tiếp", fd.getNumFoodByOnlAndOffAC(start, end, "OFF", cinID), PC);
                        } else {
                            String PC1 = decimalFormat.format((double) fd.getNumFoodByOnlAndOffAC(start, end, "ONL", cinID) / (double) numFood * 100);
                            String PC2 = decimalFormat.format((double) fd.getNumFoodByOnlAndOffAC(start, end, "OFF", cinID) / (double) numFood * 100);
                            tONL = new TIcketDate("Bán online", fd.getNumFoodByOnlAndOffAC(start, end, "ONL", cinID), PC1);
                            tOFF = new TIcketDate("Bán trực tiếp", fd.getNumFoodByOnlAndOffAC(start, end, "OFF", cinID), PC2);
                        }

                        //date
                        List<TIcketDate> listTID2 = fd.getDateByNumTickDateAC(start, end, cinID);
                        for (int i = 0; i < listTID2.size(); i++) {
                            String p = "";
                            cnt = 0;
                            t = "";
                            for (int j = 0; j < listTID2.get(i).getdS().length(); j++) {

                                if (listTID2.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                    p += listTID2.get(i).getdS().substring(0, j);
                                    cnt = j;
                                } else if (listTID2.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                    t = p;
                                    p = listTID2.get(i).getdS().substring(cnt, j + 1);
                                    p += t;
                                    cnt = j;
                                    break;
                                }
                            }
                            t = p;
                            p = listTID2.get(i).getdS().substring(cnt + 1);
                            p += t;
                            System.out.println(p);
                            listTID2.get(i).setNo(fd.getNumFoodByDateEXACTAC(start, end, Date.valueOf(p), cinID));
                            if (numFood != 0) {
                                listTID2.get(i).setPc(decimalFormat.format((double) listTID2.get(i).getNo() / (double) numFood * 100));
                            } else {
                                listTID2.get(i).setPc("0");
                            }
                        }

                        //numSellEachDay
                        for (int i = 0; i < listTID2.size(); i++) {
                            String p = "";
                            cnt = 0;
                            t = "";
                            for (int j = 0; j < listTID2.get(i).getdS().length(); j++) {

                                if (listTID2.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                    p += listTID2.get(i).getdS().substring(0, j);
                                    cnt = j;
                                } else if (listTID2.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                    t = p;
                                    p = listTID2.get(i).getdS().substring(cnt, j + 1);
                                    p += t;
                                    cnt = j;
                                    break;
                                }
                            }
                            t = p;
                            p = listTID2.get(i).getdS().substring(cnt + 1);
                            p += t;

                            int numInDay = listTID2.get(i).getNo();

                            List<TIcketDate> TID7 = new ArrayList<>();

                        }

                        //allFood
                        List<Food> listF = fd.getAllFood();
                        String page_raw = request.getParameter("page");
                        int page = 1;
                        if (page_raw != null) {
                            page = Integer.parseInt(page_raw);
                        }
                        int numPerPage = 5;
                        int totalPage = (listF.size() % numPerPage == 0) ? (listF.size() / numPerPage) : (listF.size() / numPerPage + 1);
                        int startP = (page - 1) * 5;
                        int endP = (page == totalPage) ? (listF.size() - 1) : (page * numPerPage - 1);

                        //income
                        int numOnl = fd.getIncomeOnlByDAC(start, end, cinID);
                        int numOff = fd.getIncomeOffByDAC(start, end, cinID);
                        int numIA = numOnl + numOff;

                        String PCnumONL = "0";
                        String PCnumOFF = "0";
                        if (numIA != 0) {
                            PCnumONL = decimalFormat.format((double) numOnl / (double) numIA * 100);
                            PCnumOFF = decimalFormat.format((double) numOff / (double) numIA * 100);
                        }
                        request.setAttribute("cin", cnd.getCinemaByID(cinID));
                        request.setAttribute("PCnumONL", PCnumONL);
                        request.setAttribute("PCnumOFF", PCnumOFF);

                        request.setAttribute("numOnl", numOnl);
                        request.setAttribute("numOff", numOff);
                        request.setAttribute("numIA", numIA);
                        request.setAttribute("type", type);
                        request.setAttribute("listPerPage", fd.getFoodByPage(listF, startP, endP));
                        request.setAttribute("page", page);
                        request.setAttribute("totalPage", totalPage);
                        request.setAttribute("listTID2", listTID2);
                        request.setAttribute("tONL", tONL);
                        request.setAttribute("tOFF", tOFF);
                        request.setAttribute("listTID1", listTID1);
                        request.setAttribute("listTID", listTID);
                        request.setAttribute("numFood", numFood);
                        request.setAttribute("pcF", pcF);
                        request.setAttribute("startR", start);
                        request.setAttribute("endR", end);
                        request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                        request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                        request.getRequestDispatcher("fdRp.jsp").forward(request, response);

                    } else {
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("rpp");
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        String type = request.getParameter("type");
        if (type != null) {

            if (type.equals("TK")) {
                if (!start_raw.equals("") && !end_raw.equals("")) {
                    Date start = Date.valueOf(start_raw);
                    Date end = Date.valueOf(end_raw);
                    int cnt = 0;
                    String yearS = "", monthS = "", dateS = "";
                    String yearE = "", monthE = "", dateE = "";
                    String t = start_raw;
                    for (int i = 0; i < t.length(); i++) {
                        if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                            yearS = t.substring(cnt, i);
                            cnt = i;
                        } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                            monthS = t.substring(cnt + 1, i);
                            cnt = i;
                        }
                    }
                    dateS = t.substring(cnt + 1);
                    cnt = 0;
                    t = end_raw;
                    for (int i = 0; i < t.length(); i++) {
                        if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                            yearE = t.substring(cnt, i);
                            cnt = i;
                        } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                            monthE = t.substring(cnt + 1, i);
                            cnt = i;
                        }
                    }
                    dateE = t.substring(cnt + 1);

                    TicketDAO tkd = new TicketDAO();

                    //numTick
                    int numTick = tkd.getNumTickSellByDate(start, end);
                    int numTickOfAllTime = tkd.getNumTickSellOfAllTime();
                    String pcNumTick = decimalFormat.format((double) numTick / (double) numTickOfAllTime * 100);

                    //tickType
                    List<TIcketDate> listTID = new ArrayList<>();
                    List<String> listType = tkd.getAllTickTypeByDate(start, end);
                    for (int i = 0; i < listType.size(); i++) {
                        String tpe = "";
                        if (listType.get(i).equals("NM")) {
                            tpe = "Thường";
                        } else if (listType.get(i).equals("VP")) {
                            tpe = "VIP";
                        } else if (listType.get(i).equals("VT")) {
                            tpe = "Đôi";
                        }
                        int numSellType = tkd.getNumTickTypeSellByTime(start, end, 0, listType.get(i));
                        String a = decimalFormat.format((double) numSellType / (double) numTick * 100);
                        TIcketDate ticd = new TIcketDate(tpe, numSellType);
                        ticd.setPc(a);
                        listTID.add(ticd);
                    }

                    //date (cn, T2, ...)
                    int arrD = (int) ((end.getTime() - start.getTime()) / (24 * 60 * 60 * 1000) + 1);
                    Date d[] = new Date[arrD];
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String k = formatter.format(start);
                    d[0] = start;
                    Calendar c1 = Calendar.getInstance();
                    c1.setTime(start);
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

                    t = "2023-05-01";
                    List<Date> cn = new ArrayList<>();
                    List<Date> t2 = new ArrayList<>();
                    List<Date> t3 = new ArrayList<>();
                    List<Date> t4 = new ArrayList<>();
                    List<Date> t5 = new ArrayList<>();
                    List<Date> t6 = new ArrayList<>();
                    List<Date> t7 = new ArrayList<>();
                    for (int i = 0; i < d.length; i++) {
                        long time = d[i].getTime() - Date.valueOf(t).getTime();
                        time = (time / (24 * 60 * 60 * 1000)) % 7;
                        if (time == 0) {
                            t2.add(d[i]);
                        } else if (time == 1) {
                            t3.add(d[i]);
                        } else if (time == 2) {
                            t4.add(d[i]);
                        } else if (time == 3) {
                            t5.add(d[i]);
                        } else if (time == 4) {
                            t6.add(d[i]);
                        } else if (time == 5) {
                            t7.add(d[i]);
                        } else if (time == 6) {
                            cn.add(d[i]);
                        }
                    }
                    List<TIcketDate> listTID3 = new ArrayList<>();
                    if (numTick == 0) {
                        String PC = "0";
                        listTID3.add(new TIcketDate("Thứ 2", tkd.getNumTickSellByDay(t2), PC));
                        listTID3.add(new TIcketDate("Thứ 3", tkd.getNumTickSellByDay(t3), PC));
                        listTID3.add(new TIcketDate("Thứ 4", tkd.getNumTickSellByDay(t4), PC));
                        listTID3.add(new TIcketDate("Thứ 5", tkd.getNumTickSellByDay(t5), PC));
                        listTID3.add(new TIcketDate("Thứ 6", tkd.getNumTickSellByDay(t6), PC));
                        listTID3.add(new TIcketDate("Thứ 7", tkd.getNumTickSellByDay(t7), PC));
                        listTID3.add(new TIcketDate("Chủ nhật", tkd.getNumTickSellByDay(cn), PC));
                    } else {
                        String PC1 = "";
                        String PC2 = "";
                        String PC3 = "";
                        String PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t2) / (double) numTick);
                        if (tkd.getNumTickSellByDay(t2) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "NM") / (double) tkd.getNumTickSellByDay(t2) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "VP") / (double) tkd.getNumTickSellByDay(t2) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "VT") / (double) tkd.getNumTickSellByDay(t2) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTID3.add(new TIcketDate("Thứ 2", tkd.getNumTickSellByDay(t2), PC));

                        List<TIcketDate> listTIDte = new ArrayList<>();
                        if (tkd.getNumTickSellByDay(t2) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "NM") / (double) tkd.getNumTickSellByDay(t2) * 100);
                        } else {
                            PC1 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t2, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t2, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t2, "VT"), PC3));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();

                        //T3
                        if (tkd.getNumTickSellByDay(t3) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "NM") / (double) tkd.getNumTickSellByDay(t3) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "VP") / (double) tkd.getNumTickSellByDay(t3) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "VT") / (double) tkd.getNumTickSellByDay(t3) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t3, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t3, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t3, "VT"), PC3));
                        PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t3) / (double) numTick * 100);
                        listTID3.add(new TIcketDate("Thứ 3", tkd.getNumTickSellByDay(t3), PC));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();

                        //T4
                        if (tkd.getNumTickSellByDay(t4) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "NM") / (double) tkd.getNumTickSellByDay(t4) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "VP") / (double) tkd.getNumTickSellByDay(t4) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "VT") / (double) tkd.getNumTickSellByDay(t4) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t4, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t4, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t4, "VT"), PC3));
                        PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t4) / (double) numTick * 100);
                        listTID3.add(new TIcketDate("Thứ 4", tkd.getNumTickSellByDay(t4), PC));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();

                        //T5
                        if (tkd.getNumTickSellByDay(t5) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "NM") / (double) tkd.getNumTickSellByDay(t5) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "VP") / (double) tkd.getNumTickSellByDay(t5) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "VT") / (double) tkd.getNumTickSellByDay(t5) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t5, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t5, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t5, "VT"), PC3));
                        PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t5) / (double) numTick * 100);
                        listTID3.add(new TIcketDate("Thứ 5", tkd.getNumTickSellByDay(t5), PC));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();

                        //T6
                        if (tkd.getNumTickSellByDay(t6) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "NM") / (double) tkd.getNumTickSellByDay(t6) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "VP") / (double) tkd.getNumTickSellByDay(t6) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "VT") / (double) tkd.getNumTickSellByDay(t6) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t6, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t6, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t6, "VT"), PC3));
                        PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t6) / (double) numTick * 100);
                        listTID3.add(new TIcketDate("Thứ 6", tkd.getNumTickSellByDay(t6), PC));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();

                        //T7
                        if (tkd.getNumTickSellByDay(t7) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "NM") / (double) tkd.getNumTickSellByDay(t7) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "VP") / (double) tkd.getNumTickSellByDay(t7) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "VT") / (double) tkd.getNumTickSellByDay(t7) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t7, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t7, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t7, "VT"), PC3));
                        PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t7) / (double) numTick * 100);
                        listTID3.add(new TIcketDate("Thứ 7", tkd.getNumTickSellByDay(t7), PC));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();

                        //CN
                        if (tkd.getNumTickSellByDay(cn) != 0) {
                            PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "NM") / (double) tkd.getNumTickSellByDay(cn) * 100);
                            PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "VP") / (double) tkd.getNumTickSellByDay(cn) * 100);
                            PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "VT") / (double) tkd.getNumTickSellByDay(cn) * 100);
                        } else {
                            PC1 = "0";
                            PC2 = "0";
                            PC3 = "0";
                        }
                        listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(cn, "NM"), PC1));
                        listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(cn, "VP"), PC2));
                        listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(cn, "VT"), PC3));
                        PC = decimalFormat.format((double) tkd.getNumTickSellByDay(cn) / (double) numTick * 100);
                        listTID3.add(new TIcketDate("Chủ nhật", tkd.getNumTickSellByDay(cn), PC));
                        listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                        for (int i = 0; i < listTID3.get(listTID3.size() - 1).getTkd().size(); i++) {
                            System.out.println(listTID3.get(listTID3.size() - 1).getTkd().get(i).getNo());
                        }
                        listTIDte = new ArrayList<>();
                        System.out.println(listTID3.get(listTID3.size() - 1).getTkd().size());
                    }

                    //hourTick
                    String ts1 = "08:00:00", te1 = "09:59:00";
                    String ts2 = "10:00:00", te2 = "12:29:00";
                    String ts3 = "12:30:00", te3 = "14:29:00";
                    String ts4 = "14:30:00", te4 = "17:29:00";
                    String ts5 = "17:30:00", te5 = "20:29:00";
                    String ts6 = "20:30:00", te6 = "22:00:00";

                    List<TIcketDate> listTID4 = new ArrayList<>();

                    if (numTick == 0) {
                        String PC = "0";
                        listTID4.add(new TIcketDate(ts1 + " - " + te1, tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)), PC));
                        listTID4.add(new TIcketDate(ts2 + " - " + te2, tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)), PC));
                        listTID4.add(new TIcketDate(ts3 + " - " + te3, tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)), PC));
                        listTID4.add(new TIcketDate(ts4 + " - " + te4, tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)), PC));
                        listTID4.add(new TIcketDate(ts5 + " - " + te5, tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)), PC));
                        listTID4.add(new TIcketDate(ts6 + " - " + te6, tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)), PC));
                    } else {
                        String PC = "";
                        List<TIcketDate> listTIDte = new ArrayList<>();
                        PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) / (double) numTick * 100);
                        listTID4.add(new TIcketDate(ts1 + " - " + te1, tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)), PC));
                        if (tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) != 0) {
                            listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(t2, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(t3, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(t4, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(t5, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(t6, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(t7, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double) tkd.getNumTickByHAD(cn, Time.valueOf(ts1), Time.valueOf(te1)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                            listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();
                        }

                        PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) / (double) numTick * 100);
                        listTID4.add(new TIcketDate(ts2 + " - " + te2, tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)), PC));
                        if (tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) != 0) {
                            listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(t2, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)))));
                            listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(t3, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(t4, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(t5, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(t6, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(t7, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                            listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double) tkd.getNumTickByHAD(cn, Time.valueOf(ts2), Time.valueOf(te2)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                            listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();
                        }

                        PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) / (double) numTick * 100);
                        listTID4.add(new TIcketDate(ts3 + " - " + te3, tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)), PC));
                        if (tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) != 0) {
                            listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(t2, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(t3, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(t4, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(t5, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(t6, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(t7, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double) tkd.getNumTickByHAD(cn, Time.valueOf(ts3), Time.valueOf(te3)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                            listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();
                        }

                        PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) / (double) numTick * 100);
                        listTID4.add(new TIcketDate(ts4 + " - " + te4, tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)), PC));
                        if (tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) != 0) {
                            listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(t2, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(t3, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(t4, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(t5, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(t6, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(t7, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double) tkd.getNumTickByHAD(cn, Time.valueOf(ts4), Time.valueOf(te4)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                            listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();
                        }

                        PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) / (double) numTick * 100);
                        listTID4.add(new TIcketDate(ts5 + " - " + te5, tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)), PC));
                        if (tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) != 0) {
                            listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(t2, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(t3, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(t4, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(t5, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(t6, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(t7, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double) tkd.getNumTickByHAD(cn, Time.valueOf(ts5), Time.valueOf(te5)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                            listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();
                        }

                        PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) / (double) numTick * 100);
                        listTID4.add(new TIcketDate(ts6 + " - " + te6, tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)), PC));
                        if (tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) != 0) {
                            listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(t2, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(t3, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(t4, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(t5, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(t6, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(t7, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double) tkd.getNumTickByHAD(cn, Time.valueOf(ts6), Time.valueOf(te6)) / (double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                            listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                            listTIDte = new ArrayList<>();
                        }
                    }

                    //Cinema
                    List<Cinema> cin = tkd.getAllCinemaSellTicketByDate(start, end);
                    List<TIcketDate> listTID5 = new ArrayList<>();
                    for (int i = 0; i < cin.size(); i++) {
                        if (numTick == 0) {
                            String PC = "0";
                            listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()), PC));
                        } else {
                            String PC = decimalFormat.format((double) tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()) / (double) numTick * 100);
                            listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()), PC));
                        }

                    }

                    //onl off
                    TIcketDate listTIDOnl;
                    TIcketDate listTIDOff;

                    if (numTick == 0) {
                        listTIDOnl = new TIcketDate("Bán online", tkd.getNumTickSellByOOAD(start, end, "ONL"), "0");
                        listTIDOff = new TIcketDate("Bán trực tiếp", tkd.getNumTickSellByOOAD(start, end, "OFF"), "0");
                    } else {
                        listTIDOnl = new TIcketDate("Bán online", tkd.getNumTickSellByOOAD(start, end, "ONL"), decimalFormat.format((double) tkd.getNumTickSellByOOAD(start, end, "ONL") / (double) numTick));
                        listTIDOff = new TIcketDate("Bán trực tiếp", tkd.getNumTickSellByOOAD(start, end, "OFF"), decimalFormat.format((double) tkd.getNumTickSellByOOAD(start, end, "OFF") / (double) numTick));
                    }

                    //date
                    List<TIcketDate> listTID6 = tkd.getAllTicketBoughtDateByTime(start, end, 0);
                    for (int i = 0; i < listTID6.size(); i++) {
                        String p = "";
                        cnt = 0;
                        t = "";
                        for (int j = 0; j < listTID6.get(i).getdS().length(); j++) {

                            if (listTID6.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                p += listTID6.get(i).getdS().substring(0, j);
                                cnt = j;
                            } else if (listTID6.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                t = p;
                                p = listTID6.get(i).getdS().substring(cnt, j + 1);
                                p += t;
                                cnt = j;
                                break;
                            }
                        }
                        t = p;
                        p = listTID6.get(i).getdS().substring(cnt + 1);
                        p += t;
                        System.out.println(p);
                        listTID6.get(i).setNo(tkd.getNumTickByDate(start, end, 0, Date.valueOf(p)));
                        if (numTick != 0) {
                            listTID6.get(i).setPc(decimalFormat.format((double) listTID6.get(i).getNo() / (double) numTick * 100));
                        } else {
                            listTID6.get(i).setPc("0");
                        }
                    }

                    //numSellEachDay
                    for (int i = 0; i < listTID6.size(); i++) {
                        String p = "";
                        cnt = 0;
                        t = "";
                        for (int j = 0; j < listTID6.get(i).getdS().length(); j++) {

                            if (listTID6.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                p += listTID6.get(i).getdS().substring(0, j);
                                cnt = j;
                            } else if (listTID6.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                t = p;
                                p = listTID6.get(i).getdS().substring(cnt, j + 1);
                                p += t;
                                cnt = j;
                                break;
                            }
                        }
                        t = p;
                        p = listTID6.get(i).getdS().substring(cnt + 1);
                        p += t;

                        int numInDay = listTID6.get(i).getNo();

                        List<TIcketDate> TID7 = new ArrayList<>();
                        if (numInDay != 0) {
                            String PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "NM") / (double) numInDay * 100);
                            TID7.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "NM"), PC));

                            PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VP") / (double) numInDay * 100);
                            TID7.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VP"), PC));

                            PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VT") / (double) numInDay * 100);
                            TID7.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VT"), PC));
                            listTID6.get(i).setTkd(TID7);
                        } else {
                            String PC = "0";
                            TID7.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "NM"), PC));
                            TID7.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VP"), PC));
                            TID7.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VT"), PC));
                            listTID6.get(i).setTkd(TID7);
                        }
                    }
                    RateDAO rd = new RateDAO();
                    //phim ban dc ve va phim chua ban dc ve
                    List<Movies> listM = tkd.getAllMovSellTicketByDate(start, end);
                    for (int i = 0; i < listM.size(); i++) {
                        listM.get(i).setAllNumTick(tkd.getAllNumTickOfMovies(start, end, listM.get(i).getMovID()));
                        listM.get(i).setNumTickSell(tkd.getNumTicketSellByTime(start, end, listM.get(i).getMovID()));
                        if (listM.get(i).getAllNumTick() != 0) {
                            String PC = decimalFormat.format((double) listM.get(i).getNumTickSell() / (double) listM.get(i).getAllNumTick() * 100);
                            listM.get(i).setPcNumTickSell(PC);
                        } else {
                            String PC = "0";
                            listM.get(i).setPcNumTickSell(PC);
                        }
                        Movies m = listM.get(i);
                        m.setNoRate(rd.getNoRate(m.getMovID()));
                        m.setNoRate5(rd.getNoRate5(m.getMovID()));
                        m.setNoRate4(rd.getNoRate4(m.getMovID()));
                        m.setNoRate3(rd.getNoRate3(m.getMovID()));
                        m.setNoRate2(rd.getNoRate2(m.getMovID()));
                        m.setNoRate1(rd.getNoRate1(m.getMovID()));
                        m.setNoRate(rd.getNoRate(m.getMovID()));
                        m.setSumRate(rd.getSumRate(m.getMovID()));

                        String b, p5, p4, p3, p2, p1;
                        if (rd.getNoRate(m.getMovID()) != 0) {
                            b = decimalFormat.format((double) rd.getSumRate(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                            p5 = decimalFormat.format((double) rd.getNoRate5(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                            p4 = decimalFormat.format((double) rd.getNoRate4(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                            p3 = decimalFormat.format((double) rd.getNoRate3(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                            p2 = decimalFormat.format((double) rd.getNoRate2(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                            p1 = decimalFormat.format((double) rd.getNoRate1(m.getMovID()) / (double) rd.getNoRate(m.getMovID()));
                        } else {
                            decimalFormat = new DecimalFormat("#");
                            b = decimalFormat.format(0);
                            p5 = decimalFormat.format(0);
                            p4 = decimalFormat.format(0);
                            p3 = decimalFormat.format(0);
                            p2 = decimalFormat.format(0);
                            p1 = decimalFormat.format(0);
                        }
                        m.setAvrRate(Double.parseDouble(b));
                        m.setpRate5(Double.parseDouble(p5));
                        m.setpRate4(Double.parseDouble(p4));
                        m.setpRate3(Double.parseDouble(p3));
                        m.setpRate2(Double.parseDouble(p2));
                        m.setpRate1(Double.parseDouble(p1));
                        listM.set(i, m);
                    }

                    if (listM.isEmpty()) {
                        request.setAttribute("msT", "Không có bất kỳ bộ phim nào được chiếu trong khung giờ này");
                    }

                    List<Movies> listMNS = tkd.getAllMoviesSellTicketButNotB(start, end);
                    for (int i = 0; i < listMNS.size(); i++) {
                        listMNS.get(i).setAllNumTick(tkd.getAllNumTickOfMovies(start, end, listMNS.get(i).getMovID()));
                        listMNS.get(i).setNumTickSell(tkd.getNumTicketSellByTime(start, end, listMNS.get(i).getMovID()));
                        if (listMNS.get(i).getAllNumTick() != 0) {
                            String PC = decimalFormat.format((double) listMNS.get(i).getNumTickSell() / (double) listMNS.get(i).getAllNumTick() * 100);
                            listMNS.get(i).setPcNumTickSell(PC);
                        } else {
                            String PC = "0";
                            listMNS.get(i).setPcNumTickSell(PC);
                        }
                    }

                    if (listMNS.size() > 0) {
                        request.setAttribute("listMNS", listMNS);
                    } else {
                        request.setAttribute("msMNS", "Không có bộ phim nào chưa bán được vé");
                    }

                    //income
                    int numOnl = tkd.getOnlineIncome(start, end);
                    int numOff = tkd.getOfflineIncome(start, end);
                    int numIA = numOnl + numOff;

                    String PCnumONL = "0";
                    String PCnumOFF = "0";
                    if (numIA != 0) {
                        PCnumONL = decimalFormat.format((double) numOnl / (double) numIA * 100);
                        PCnumOFF = decimalFormat.format((double) numOff / (double) numIA * 100);
                    }

                    request.setAttribute("PCnumONL", PCnumONL);
                    request.setAttribute("PCnumOFF", PCnumOFF);

                    request.setAttribute("numOnl", numOnl);
                    request.setAttribute("numOff", numOff);
                    request.setAttribute("numIA", numIA);
                    request.setAttribute("listM", listM);
                    request.setAttribute("listTID6", listTID6);
                    request.setAttribute("type", type);
                    request.setAttribute("listTIDOnl", listTIDOnl);
                    request.setAttribute("listTIDOff", listTIDOff);
                    request.setAttribute("listTID5", listTID5);
                    request.setAttribute("listTID4", listTID4);
                    request.setAttribute("listTID3", listTID3);
                    request.setAttribute("listTID", listTID);
                    request.setAttribute("pcNumTick", pcNumTick);
                    request.setAttribute("numTick", numTick);
                    request.setAttribute("startR", start);
                    request.setAttribute("endR", end);
                    request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                    request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                    request.getRequestDispatcher("tkRp.jsp").forward(request, response);

                }
                else {
                    response.sendRedirect("rpp?type=TK");
                }
            } else if (type.equals("FD")) {
                if (!start_raw.equals("") && !end_raw.equals("")) {
                    Date start = Date.valueOf(start_raw);
                    Date end = Date.valueOf(end_raw);
                    int cnt = 0;
                    String yearS = "", monthS = "", dateS = "";
                    String yearE = "", monthE = "", dateE = "";
                    String t = start_raw;
                    for (int i = 0; i < t.length(); i++) {
                        if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                            yearS = t.substring(cnt, i);
                            cnt = i;
                        } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                            monthS = t.substring(cnt + 1, i);
                            cnt = i;
                        }
                    }

                    dateS = t.substring(cnt + 1);
                    cnt = 0;
                    t = end_raw;
                    for (int i = 0; i < t.length(); i++) {
                        if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                            yearE = t.substring(cnt, i);
                            cnt = i;
                        } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                            monthE = t.substring(cnt + 1, i);
                            cnt = i;
                        }
                    }
                    dateE = t.substring(cnt + 1);

                    FoodDAO fd = new FoodDAO();
                    int numFood = fd.getNumFoodByDate(start, end);
                    int numFoodAllTime = fd.getNumFood();

                    String pcF = decimalFormat.format((double) numFood / (double) numFoodAllTime * 100);

                    //foodType
                    List<TIcketDate> listTID = new ArrayList<>();
                    List<Food> ft = fd.getAllFoodTypeByDate(start, end);
                    for (int i = 0; i < ft.size(); i++) {
                        if (numFood == 0) {
                            String PC = "0";
                            listTID.add(new TIcketDate(ft.get(i).getFoodDescript(), fd.getNumFoodTypeSellByDateAType(start, end, ft.get(i).getFoodType()), PC));
                        } else {
                            String PC = decimalFormat.format((double) fd.getNumFoodTypeSellByDateAType(start, end, ft.get(i).getFoodType()) / (double) numFood * 100);
                            listTID.add(new TIcketDate(ft.get(i).getTypeName(), fd.getNumFoodTypeSellByDateAType(start, end, ft.get(i).getFoodType()), PC));
                        }
                    }

                    //cin
                    List<TIcketDate> listTID1 = new ArrayList<>();
                    List<Cinema> c = fd.getAllCinByDate(start, end);
                    for (int i = 0; i < c.size(); i++) {
                        if (numFood == 0) {
                            String PC = "0";
                            listTID1.add(new TIcketDate(c.get(i).getCinName(), fd.getNumFoodSellByCinAndDate(start, end, c.get(i).getCinID()), PC));
                        } else {
                            String PC = decimalFormat.format((double) fd.getNumFoodSellByCinAndDate(start, end, c.get(i).getCinID()) / (double) numFood * 100);
                            listTID1.add(new TIcketDate(c.get(i).getCinName(), fd.getNumFoodSellByCinAndDate(start, end, c.get(i).getCinID()), PC));
                        }
                    }

                    //onl off
                    TIcketDate tONL;
                    TIcketDate tOFF;
                    if (numFood == 0) {
                        String PC = "0";
                        tONL = new TIcketDate("Bán online", fd.getNumFoodByOnlAndOff(start, end, "ONL"), PC);
                        tOFF = new TIcketDate("Bán trực tiếp", fd.getNumFoodByOnlAndOff(start, end, "OFF"), PC);
                    } else {
                        String PC1 = decimalFormat.format((double) fd.getNumFoodByOnlAndOff(start, end, "ONL") / (double) numFood * 100);
                        String PC2 = decimalFormat.format((double) fd.getNumFoodByOnlAndOff(start, end, "OFF") / (double) numFood * 100);
                        tONL = new TIcketDate("Bán online", fd.getNumFoodByOnlAndOff(start, end, "ONL"), PC1);
                        tOFF = new TIcketDate("Bán trực tiếp", fd.getNumFoodByOnlAndOff(start, end, "OFF"), PC2);
                    }

                    //date
                    List<TIcketDate> listTID2 = fd.getDateByNumTickDate(start, end);
                    for (int i = 0; i < listTID2.size(); i++) {
                        String p = "";
                        cnt = 0;
                        t = "";
                        for (int j = 0; j < listTID2.get(i).getdS().length(); j++) {

                            if (listTID2.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                p += listTID2.get(i).getdS().substring(0, j);
                                cnt = j;
                            } else if (listTID2.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                t = p;
                                p = listTID2.get(i).getdS().substring(cnt, j + 1);
                                p += t;
                                cnt = j;
                                break;
                            }
                        }
                        t = p;
                        p = listTID2.get(i).getdS().substring(cnt + 1);
                        p += t;
                        System.out.println(p);
                        listTID2.get(i).setNo(fd.getNumFoodByDateEXACT(start, end, Date.valueOf(p)));
                        if (numFood != 0) {
                            listTID2.get(i).setPc(decimalFormat.format((double) listTID2.get(i).getNo() / (double) numFood * 100));
                        } else {
                            listTID2.get(i).setPc("0");
                        }
                    }

                    //numSellEachDay
                    for (int i = 0; i < listTID2.size(); i++) {
                        String p = "";
                        cnt = 0;
                        t = "";
                        for (int j = 0; j < listTID2.get(i).getdS().length(); j++) {

                            if (listTID2.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                                p += listTID2.get(i).getdS().substring(0, j);
                                cnt = j;
                            } else if (listTID2.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                                t = p;
                                p = listTID2.get(i).getdS().substring(cnt, j + 1);
                                p += t;
                                cnt = j;
                                break;
                            }
                        }
                        t = p;
                        p = listTID2.get(i).getdS().substring(cnt + 1);
                        p += t;

                        int numInDay = listTID2.get(i).getNo();

                        List<TIcketDate> TID7 = new ArrayList<>();

                    }

                    //allFood
                    List<Food> listF = fd.getAllFood();
                    String page_raw = request.getParameter("page");
                    int page = 1;
                    if (page_raw != null) {
                        page = Integer.parseInt(page_raw);
                    }
                    int numPerPage = 5;
                    int totalPage = (listF.size() % numPerPage == 0) ? (listF.size() / numPerPage) : (listF.size() / numPerPage + 1);
                    int startP = (page - 1) * 5;
                    int endP = (page == totalPage) ? (listF.size() - 1) : (page * numPerPage - 1);

                    //income
                    int numOnl = fd.getIncomeOnl(start, end);
                    int numOff = fd.getIncomeOff(start, end);
                    int numIA = numOnl + numOff;

                    String PCnumONL = "0";
                    String PCnumOFF = "0";
                    if (numIA != 0) {
                        PCnumONL = decimalFormat.format((double) numOnl / (double) numIA * 100);
                        PCnumOFF = decimalFormat.format((double) numOff / (double) numIA * 100);
                    }

                    request.setAttribute("PCnumONL", PCnumONL);
                    request.setAttribute("PCnumOFF", PCnumOFF);

                    request.setAttribute("numOnl", numOnl);
                    request.setAttribute("numOff", numOff);
                    request.setAttribute("numIA", numIA);
                    request.setAttribute("type", type);
                    request.setAttribute("listPerPage", fd.getFoodByPage(listF, startP, endP));
                    request.setAttribute("page", page);
                    request.setAttribute("totalPage", totalPage);
                    request.setAttribute("listTID2", listTID2);
                    request.setAttribute("tONL", tONL);
                    request.setAttribute("tOFF", tOFF);
                    request.setAttribute("listTID1", listTID1);
                    request.setAttribute("listTID", listTID);
                    request.setAttribute("numFood", numFood);
                    request.setAttribute("pcF", pcF);
                    request.setAttribute("startR", start);
                    request.setAttribute("endR", end);
                    request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                    request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                    request.getRequestDispatcher("fdRp.jsp").forward(request, response);
                }
                else {
                response.sendRedirect("rpp?type=FD");
            }
            } 
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
