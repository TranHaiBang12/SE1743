/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.RateDAO;
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
        if (type != null) {
            request.setAttribute("type", type);
            request.getRequestDispatcher("prRp.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
            Date start = Date.valueOf(start_raw);
            Date end = Date.valueOf(end_raw);
            if (type.equals("TK")) {
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
                    String a = decimalFormat.format((double) numSellType / (double) numTick  * 100);
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
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "NM") / (double) tkd.getNumTickSellByDay(t2)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "VP") / (double) tkd.getNumTickSellByDay(t2) * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "VT") / (double) tkd.getNumTickSellByDay(t2)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTID3.add(new TIcketDate("Thứ 2", tkd.getNumTickSellByDay(t2), PC));

                    List<TIcketDate> listTIDte = new ArrayList<>();
                    if (tkd.getNumTickSellByDay(t2) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "NM") / (double) tkd.getNumTickSellByDay(t2)  * 100);
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
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "NM") / (double) tkd.getNumTickSellByDay(t3)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "VP") / (double) tkd.getNumTickSellByDay(t3)  * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "VT") / (double) tkd.getNumTickSellByDay(t3)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t3, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t3, "VP"), PC2));
                    listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t3, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t3) / (double) numTick  * 100);
                    listTID3.add(new TIcketDate("Thứ 3", tkd.getNumTickSellByDay(t3), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte = new ArrayList<>();

                    //T4
                    if (tkd.getNumTickSellByDay(t4) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "NM") / (double) tkd.getNumTickSellByDay(t4)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "VP") / (double) tkd.getNumTickSellByDay(t4)  * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "VT") / (double) tkd.getNumTickSellByDay(t4)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t4, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t4, "VP"), PC2));
                    listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t4, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t4) / (double) numTick  * 100);
                    listTID3.add(new TIcketDate("Thứ 4", tkd.getNumTickSellByDay(t4), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte = new ArrayList<>();

                    //T5
                    if (tkd.getNumTickSellByDay(t5) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "NM") / (double) tkd.getNumTickSellByDay(t5)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "VP") / (double) tkd.getNumTickSellByDay(t5)  * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "VT") / (double) tkd.getNumTickSellByDay(t5)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t5, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t5, "VP"), PC2));
                    listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t5, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t5) / (double) numTick  * 100);
                    listTID3.add(new TIcketDate("Thứ 5", tkd.getNumTickSellByDay(t5), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte = new ArrayList<>();

                    //T6
                    if (tkd.getNumTickSellByDay(t6) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "NM") / (double) tkd.getNumTickSellByDay(t6)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "VP") / (double) tkd.getNumTickSellByDay(t6)  * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "VT") / (double) tkd.getNumTickSellByDay(t6)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t6, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t6, "VP"), PC2));
                    listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t6, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t6) / (double) numTick  * 100);
                    listTID3.add(new TIcketDate("Thứ 6", tkd.getNumTickSellByDay(t6), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte = new ArrayList<>();

                    //T7
                    if (tkd.getNumTickSellByDay(t7) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "NM") / (double) tkd.getNumTickSellByDay(t7)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "VP") / (double) tkd.getNumTickSellByDay(t7)  * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "VT") / (double) tkd.getNumTickSellByDay(t7)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(t7, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(t7, "VP"), PC2));
                    listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(t7, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t7) / (double) numTick  * 100);
                    listTID3.add(new TIcketDate("Thứ 7", tkd.getNumTickSellByDay(t7), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte = new ArrayList<>();

                    //CN
                    if (tkd.getNumTickSellByDay(cn) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "NM") / (double) tkd.getNumTickSellByDay(cn)  * 100);
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "VP") / (double) tkd.getNumTickSellByDay(cn)  * 100);
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "VT") / (double) tkd.getNumTickSellByDay(cn)  * 100);
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDay(cn, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDay(cn, "VP"), PC2));
                    listTIDte.add(new TIcketDate("Đôi", tkd.getNumTickTypeSellByDay(cn, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(cn) / (double) numTick  * 100);
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
                    if (tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) != 0)  {
                        listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(t2, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(t3, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(t4, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(t5, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(t6, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(t7, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts1), Time.valueOf(te1)), decimalFormat.format((double)tkd.getNumTickByHAD(cn, Time.valueOf(ts1), Time.valueOf(te1)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts1), Time.valueOf(te1)) * 100)));
                        listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();
                    }
                    
                    PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) / (double) numTick * 100);
                    listTID4.add(new TIcketDate(ts2 + " - " + te2, tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)), PC));
                    if (tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) != 0)  {
                        listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(t2, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)))));
                        listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(t3, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(t4, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(t5, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(t6, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(t7, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                        listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts2), Time.valueOf(te2)), decimalFormat.format((double)tkd.getNumTickByHAD(cn, Time.valueOf(ts2), Time.valueOf(te2)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts2), Time.valueOf(te2)) * 100)));
                        listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();
                    }

                    PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) / (double) numTick * 100);
                    listTID4.add(new TIcketDate(ts3 + " - " + te3, tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)), PC));
                    if (tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) != 0)  {
                        listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(t2, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(t3, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(t4, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(t5, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(t6, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(t7, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts3), Time.valueOf(te3)), decimalFormat.format((double)tkd.getNumTickByHAD(cn, Time.valueOf(ts3), Time.valueOf(te3)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts3), Time.valueOf(te3)) * 100)));
                        listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();
                    }

                    PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) / (double) numTick * 100);
                    listTID4.add(new TIcketDate(ts4 + " - " + te4, tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)), PC));
                    if (tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) != 0)  {
                        listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(t2, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(t3, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(t4, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(t5, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(t6, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(t7, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts4), Time.valueOf(te4)), decimalFormat.format((double)tkd.getNumTickByHAD(cn, Time.valueOf(ts4), Time.valueOf(te4)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts4), Time.valueOf(te4)) * 100)));
                        listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();
                    }

                    PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) / (double) numTick * 100);
                    listTID4.add(new TIcketDate(ts5 + " - " + te5, tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)), PC));
                    if (tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) != 0)  {
                        listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(t2, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(t3, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(t4, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(t5, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(t6, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(t7, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts5), Time.valueOf(te5)), decimalFormat.format((double)tkd.getNumTickByHAD(cn, Time.valueOf(ts5), Time.valueOf(te5)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts5), Time.valueOf(te5)) * 100)));
                        listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();
                    }

                    PC = decimalFormat.format((double) tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) / (double) numTick * 100);
                    listTID4.add(new TIcketDate(ts6 + " - " + te6, tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)), PC));
                    if (tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) != 0)  {
                        listTIDte.add(new TIcketDate("Thứ 2", tkd.getNumTickByHAD(t2, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(t2, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 3", tkd.getNumTickByHAD(t3, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(t3, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 4", tkd.getNumTickByHAD(t4, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(t4, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 5", tkd.getNumTickByHAD(t5, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(t5, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 6", tkd.getNumTickByHAD(t6, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(t6, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTIDte.add(new TIcketDate("Thứ 7", tkd.getNumTickByHAD(t7, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(t7, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTIDte.add(new TIcketDate("Chủ nhật", tkd.getNumTickByHAD(cn, Time.valueOf(ts6), Time.valueOf(te6)), decimalFormat.format((double)tkd.getNumTickByHAD(cn, Time.valueOf(ts6), Time.valueOf(te6)) / (double)tkd.getNumTickByHour(start, end, Time.valueOf(ts6), Time.valueOf(te6)) * 100)));
                        listTID4.get(listTID4.size() - 1).setTkd(listTIDte);
                        listTIDte = new ArrayList<>();
                    }
                }
                
                //Cinema
                List<Cinema> cin = tkd.getAllCinemaSellTicketByDate(start, end);
                List<TIcketDate> listTID5 = new ArrayList<>();
                for (int i = 0; i < cin.size(); i++) {
                    if(numTick == 0) {
                        String PC = "0";
                        listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()), PC));
                    }
                    else {
                        String PC = decimalFormat.format((double)tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()) / (double)numTick * 100);
                        listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCAD(start, end, cin.get(i).getCinID()), PC));
                    }
                    
                }
                
                request.setAttribute("listTID5", listTID5);
                request.setAttribute("listTID4", listTID4);
                request.setAttribute("listTID3", listTID3);
                request.setAttribute("listTID", listTID);
                request.setAttribute("pcNumTick", pcNumTick);
                request.setAttribute("numTick", numTick);
                request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                request.getRequestDispatcher("tkRp.jsp").forward(request, response);

            } else if (type.equals("FD")) {

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
                request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                request.getRequestDispatcher("fdRp.jsp").forward(request, response);
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
