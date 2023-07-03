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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                    String a = decimalFormat.format((double) numSellType / (double) numTick);
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
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "NM") / (double) tkd.getNumTickSellByDay(t2));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "VP") / (double) tkd.getNumTickSellByDay(t2));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "VT") / (double) tkd.getNumTickSellByDay(t2));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTID3.add(new TIcketDate("Thứ 2", tkd.getNumTickSellByDay(t2), PC));

                    List<TIcketDate> listTIDte = new ArrayList<>();
                    if (tkd.getNumTickTypeSellByDay(t3, "NM") != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t2, "NM") / (double) tkd.getNumTickSellByDay(t2));
                    } else {
                        PC1 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(t2, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(t2, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(t2, "VT"), PC3));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                    
                    //T3
                    if (tkd.getNumTickSellByDay(t3) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "NM") / (double) tkd.getNumTickSellByDay(t3));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "VP") / (double) tkd.getNumTickSellByDay(t3));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t3, "VT") / (double) tkd.getNumTickSellByDay(t3));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(t3, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(t3, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(t3, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t3) / (double) numTick);
                    listTID3.add(new TIcketDate("Thứ 3", tkd.getNumTickSellByDay(t3), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                    
                    //T4
                    if (tkd.getNumTickSellByDay(t4) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "NM") / (double) tkd.getNumTickSellByDay(t4));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "VP") / (double) tkd.getNumTickSellByDay(t4));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t4, "VT") / (double) tkd.getNumTickSellByDay(t4));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(t4, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(t4, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(t4, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t4) / (double) numTick);
                    listTID3.add(new TIcketDate("Thứ 4", tkd.getNumTickSellByDay(t4), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                    
                    //T5
                    if (tkd.getNumTickSellByDay(t5) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "NM") / (double) tkd.getNumTickSellByDay(t5));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "VP") / (double) tkd.getNumTickSellByDay(t5));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t5, "VT") / (double) tkd.getNumTickSellByDay(t5));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(t5, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(t5, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(t5, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t5) / (double) numTick);
                    listTID3.add(new TIcketDate("Thứ 5", tkd.getNumTickSellByDay(t5), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                    
                    //T6
                    if (tkd.getNumTickSellByDay(t6) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "NM") / (double) tkd.getNumTickSellByDay(t6));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "VP") / (double) tkd.getNumTickSellByDay(t6));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t6, "VT") / (double) tkd.getNumTickSellByDay(t6));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(t6, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(t6, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(t6, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t6) / (double) numTick);
                    listTID3.add(new TIcketDate("Thứ 6", tkd.getNumTickSellByDay(t6), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                    
                    //T7
                    if (tkd.getNumTickSellByDay(t7) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "NM") / (double) tkd.getNumTickSellByDay(t7));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "VP") / (double) tkd.getNumTickSellByDay(t7));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(t7, "VT") / (double) tkd.getNumTickSellByDay(t7));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(t7, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(t7, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(t7, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(t7) / (double) numTick);
                    listTID3.add(new TIcketDate("Thứ 7", tkd.getNumTickSellByDay(t7), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                    
                    //CN
                    if (tkd.getNumTickSellByDay(cn) != 0) {
                        PC1 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "NM") / (double) tkd.getNumTickSellByDay(cn));
                        PC2 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "VP") / (double) tkd.getNumTickSellByDay(cn));
                        PC3 = decimalFormat.format((double) tkd.getNumTickTypeSellByDay(cn, "VT") / (double) tkd.getNumTickSellByDay(cn));
                    } else {
                        PC1 = "0";
                        PC2 = "0";
                        PC3 = "0";
                    }
                    listTIDte.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDay(cn, "NM"), PC1));
                    listTIDte.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDay(cn, "VP"), PC2));
                    listTIDte.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDay(cn, "VT"), PC3));
                    PC = decimalFormat.format((double) tkd.getNumTickSellByDay(cn) / (double) numTick);
                    listTID3.add(new TIcketDate("Chủ nhật", tkd.getNumTickSellByDay(cn), PC));
                    listTID3.get(listTID3.size() - 1).setTkd(listTIDte);
                    listTIDte.clear();
                }

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
