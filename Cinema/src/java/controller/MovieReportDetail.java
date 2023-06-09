/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Cinema;
import model.MovieForm;
import model.MovieRate;
import model.MovieTicket;
import model.Movies;
import model.Rate;
import model.Schedule;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class MovieReportDetail extends HttpServlet {

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
            out.println("<title>Servlet MovieReportDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovieReportDetail at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        String start = request.getParameter("start");
        String end = request.getParameter("end");

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if (request.getParameter("cin") == null) {
            RateDAO rd = new RateDAO();
            String sD_raw = "", eD_raw = "";
            int cnt = 0;
            for (int i = 0; i < start.length(); i++) {
                if (start.charAt(i) == '-' && cnt == 0) {
                    sD_raw += start.substring(0, i);
                    cnt = i;
                } else if (start.charAt(i) == '-' && cnt != 0) {
                    String t = sD_raw;
                    sD_raw = start.substring(cnt, i + 1);
                    sD_raw += t;
                    cnt = i;
                    break;
                }
            }
            String t = sD_raw;
            sD_raw = start.substring(cnt + 1);
            sD_raw += t;
            cnt = 0;
            for (int i = 0; i < end.length(); i++) {
                if (end.charAt(i) == '-' && cnt == 0) {
                    eD_raw += end.substring(0, i);
                    cnt = i;
                } else if (end.charAt(i) == '-' && cnt != 0) {
                    t = eD_raw;
                    eD_raw = end.substring(cnt, i + 1);
                    eD_raw += t;
                    cnt = i;
                    break;
                }
            }
            t = eD_raw;
            eD_raw = end.substring(cnt + 1);
            eD_raw += t;
            Date dS = Date.valueOf(sD_raw);
            Date eS = Date.valueOf(eD_raw);

            ScheDAO scd = new ScheDAO();
            int id = 0;
            try {
                id = Integer.parseInt(id_raw);
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            MovieDAO mvd = new MovieDAO();

            Movies m = mvd.getMovieById(id);
            m.setNoRate(rd.getNoRate(id));
            m.setNoRate5(rd.getNoRate5(id));
            m.setNoRate4(rd.getNoRate4(id));
            m.setNoRate3(rd.getNoRate3(id));
            m.setNoRate2(rd.getNoRate2(id));
            m.setNoRate1(rd.getNoRate1(id));
            m.setNoRate(rd.getNoRate(id));
            m.setSumRate(rd.getSumRate(id));

            String b, p5, p4, p3, p2, p1;
            if (rd.getNoRate(id) != 0) {
                b = decimalFormat.format((double) rd.getSumRate(id) / (double) rd.getNoRate(id) * 100);
                p5 = decimalFormat.format((double) rd.getNoRate5(id) / (double) rd.getNoRate(id) * 100);
                p4 = decimalFormat.format((double) rd.getNoRate4(id) / (double) rd.getNoRate(id) * 100);
                p3 = decimalFormat.format((double) rd.getNoRate3(id) / (double) rd.getNoRate(id) * 100);
                p2 = decimalFormat.format((double) rd.getNoRate2(id) / (double) rd.getNoRate(id) * 100);
                p1 = decimalFormat.format((double) rd.getNoRate1(id) / (double) rd.getNoRate(id) * 100);
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

            int noD = rd.getNoRateByTime(dS, eS, id);
            int sumD = rd.getSumRateByTime(dS, eS, id);
            double avr = 0;
            if (noD != 0) {
                avr = Double.parseDouble(decimalFormat.format((double) sumD / (double) noD));
            } else {
                avr = 0;
            }

            int no5 = rd.getNoRateDByTime(dS, eS, 5, id);
            int no4 = rd.getNoRateDByTime(dS, eS, 4, id);
            int no3 = rd.getNoRateDByTime(dS, eS, 3, id);
            int no2 = rd.getNoRateDByTime(dS, eS, 2, id);
            int no1 = rd.getNoRateDByTime(dS, eS, 1, id);

            String pc5 = "", pc4 = "", pc3 = "", pc2 = "", pc1 = "";
            if (noD != 0) {
                pc5 = decimalFormat.format((double) no5 / (double) noD * 100);
                pc4 = decimalFormat.format((double) no4 / (double) noD * 100);
                pc3 = decimalFormat.format((double) no3 / (double) noD * 100);
                pc2 = decimalFormat.format((double) no2 / (double) noD * 100);
                pc1 = decimalFormat.format((double) no1 / (double) noD * 100);
            } else {
                pc5 = "0";
                pc4 = "0";
                pc3 = "0";
                pc2 = "0";
                pc1 = "0";
            }

            List<Rate> list = rd.getCommentByTime(dS, eS, id);
            String page_raw = request.getParameter("page");
            int page = 1;
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
            int numPerPage = 5;
            int totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
            int startP = (page - 1) * 5;
            int endP = (page == totalPage) ? (list.size() - 1) : (page * numPerPage - 1);

            MovieRate mr = new MovieRate(noD, sumD, avr, no5, no4, no3, no2, no1, pc5, pc4, pc3, pc2, pc1);

            TicketDAO tkd = new TicketDAO();
            int numTick = tkd.getNumTicketSellByTime(dS, eS, id);

            if (!rd.getRateByPage(list, startP, endP).isEmpty()) {
                request.setAttribute("listPerPage", rd.getRateByPage(list, startP, endP));
            } else {
                request.setAttribute("msC", "Bộ phim này chưa có lượt bình luận nào");
            }

            List<String> listTT = tkd.getAllTickTypeByTime(dS, eS, id);

            for (int i = 0; i < listTT.size(); i++) {
                if (listTT.get(i).equals("NM")) {
                    listTT.set(i, "Thường");
                } else if (listTT.get(i).equals("VP")) {
                    listTT.set(i, "VIP");
                } else if (listTT.get(i).equals("VT")) {
                    listTT.set(i, "Đôi");
                }
            }

            int nm = tkd.getNumTickTypeSellByTime(dS, eS, id, "NM");
            int vp = tkd.getNumTickTypeSellByTime(dS, eS, id, "VP");
            int vt = tkd.getNumTickTypeSellByTime(dS, eS, id, "VT");

            String pcnm = "", pcvp = "", pcvt = "";
            if (numTick != 0) {
                pcnm = decimalFormat.format((double) nm / (double) numTick * 100);
                pcvp = decimalFormat.format((double) vp / (double) numTick * 100);
                pcvt = decimalFormat.format((double) vt / (double) numTick * 100);
            } else {
                pcnm = "0";
                pcvp = "0";
                pcvt = "0";
            }

            MovieTicket mt = new MovieTicket(nm, vp, vt, pcnm, pcvp, pcvt);
            List<MovieForm> listMF = new ArrayList<>();
            ScheDAO sd = new ScheDAO();
            List<Schedule> listS = sd.getScheTypeByTime(dS, eS, id);
            for (int i = 0; i < listS.size(); i++) {
                String PC = "";
                if (numTick == 0) {
                    PC = "0";
                } else {
                    PC = decimalFormat.format((double) tkd.getNumTickFormByTime(dS, eS, id, listS.get(i).getFormID()) / (double) numTick * 100);
                }
                listMF.add(new MovieForm(listS.get(i).getFormID(), listS.get(i).getFormName(), tkd.getNumTickFormByTime(dS, eS, id, listS.get(i).getFormID()), PC));
            }

            List<TIcketDate> listTID = tkd.getAllTicketBoughtDateByTime(dS, eS, id);
            for (int i = 0; i < listTID.size(); i++) {
                String p = "";
                cnt = 0;
                t = "";
                for (int j = 0; j < listTID.get(i).getdS().length(); j++) {

                    if (listTID.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                        p += listTID.get(i).getdS().substring(0, j);
                        cnt = j;
                    } else if (listTID.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                        t = p;
                        p = listTID.get(i).getdS().substring(cnt, j + 1);
                        p += t;
                        cnt = j;
                        break;
                    }
                }
                t = p;
                p = listTID.get(i).getdS().substring(cnt + 1);
                p += t;
                listTID.get(i).setNo(tkd.getNumTickByDate(dS, eS, id, Date.valueOf(p)));
                if (numTick != 0) {
                    listTID.get(i).setPc(decimalFormat.format((double) listTID.get(i).getNo() / (double) numTick * 100));
                } else {
                    listTID.get(i).setPc("0");
                }
            }
            if (rd.getNoRate(id) != 0) {
                request.setAttribute("pcARate", decimalFormat.format((double) mr.getNoD() / (double) rd.getNoRate(id) * 100));
            } else {
                request.setAttribute("pcARate", 0);
            }
            if (tkd.getNumTickByMovID(id) != 0) {
                request.setAttribute("pcATick", decimalFormat.format((double) numTick / (double) tkd.getNumTickByMovID(id) * 100));
            } else {
                request.setAttribute("pcATick", 0);
            }

            //numSellEachDay
            for (int i = 0; i < listTID.size(); i++) {
                String p = "";
                cnt = 0;
                t = "";
                for (int j = 0; j < listTID.get(i).getdS().length(); j++) {

                    if (listTID.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                        p += listTID.get(i).getdS().substring(0, j);
                        cnt = j;
                    } else if (listTID.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                        t = p;
                        p = listTID.get(i).getdS().substring(cnt, j + 1);
                        p += t;
                        cnt = j;
                        break;
                    }
                }
                t = p;
                p = listTID.get(i).getdS().substring(cnt + 1);
                p += t;

                int numInDay = listTID.get(i).getNo();

                List<TIcketDate> TID2 = new ArrayList<>();
                if (numInDay != 0) {
                    String PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "NM") / (double) numInDay * 100);
                    TID2.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "NM"), PC));

                    PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VP") / (double) numInDay * 100);
                    TID2.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VP"), PC));

                    PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VT") / (double) numInDay * 100);
                    TID2.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VT"), PC));
                    listTID.get(i).setTkd(TID2);
                } else {
                    String PC = "0";
                    TID2.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "NM"), PC));
                    TID2.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VP"), PC));
                    TID2.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLY(Date.valueOf(p), 0, "VT"), PC));
                    listTID.get(i).setTkd(TID2);
                }
            }

            int allTick = tkd.getAllNumTickOfMovies(dS, eS, id);

            String tPC;
            if (allTick == 0) {
                tPC = "0";
            } else {
                tPC = decimalFormat.format((double) numTick / (double) allTick * 100);
            }
            CinemaDAO cnd = new CinemaDAO();
            List<Cinema> cin = cnd.getAllCinema();
            List<TIcketDate> listTID5 = new ArrayList<>();
            for (int i = 0; i < cin.size(); i++) {
                if (numTick == 0) {
                    String PC = "0";
                    listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCADMV(dS, eS, cin.get(i).getCinID(), id), PC));
                } else {
                    String PC = decimalFormat.format((double) tkd.getNumTickSellByCADMV(dS, eS, cin.get(i).getCinID(), id) / (double) numTick * 100);
                    listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCADMV(dS, eS, cin.get(i).getCinID(), id), PC));
                }

            }

            request.setAttribute("listTID5", listTID5);
            request.setAttribute("tPC", tPC);
            request.setAttribute("allTick", allTick);
            request.setAttribute("listTID", listTID);
            request.setAttribute("listMF", listMF);
            request.setAttribute("mt", mt);
            request.setAttribute("listTT", listTT);
            request.setAttribute("numTick", numTick);
            request.setAttribute("id", id);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("start", start);
            request.setAttribute("mr", mr);
            request.setAttribute("end", end);
            request.setAttribute("m", m);
            request.getRequestDispatcher("mvRpD.jsp").forward(request, response);
        } else {
            CinemaDAO cnd = new CinemaDAO();
            String cinID_raw = request.getParameter("cin");
            int cinID = 1;
            try {
                cinID = Integer.parseInt(cinID_raw);
                if (cnd.getCinemaByID(cinID) == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            int cnt = 0;
            String yearS = "", monthS = "", dateS = "";
            String yearE = "", monthE = "", dateE = "";
            String t = start;
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
            t = end;
            for (int i = 0; i < t.length(); i++) {
                if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                    yearE = t.substring(cnt, i);
                    cnt = i;
                } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                    monthE = t.substring(cnt + 1, i);
                    cnt = i;
                }
            }
            RateDAO rd = new RateDAO();
            dateE = t.substring(cnt + 1);
            Date dS = Date.valueOf(start);
            Date eS = Date.valueOf(end);

            ScheDAO scd = new ScheDAO();
            int id = 0;
            try {
                id = Integer.parseInt(id_raw);
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            MovieDAO mvd = new MovieDAO();

            Movies m = mvd.getMovieById(id);
            m.setNoRate(rd.getNoRate(id));
            m.setNoRate5(rd.getNoRate5(id));
            m.setNoRate4(rd.getNoRate4(id));
            m.setNoRate3(rd.getNoRate3(id));
            m.setNoRate2(rd.getNoRate2(id));
            m.setNoRate1(rd.getNoRate1(id));
            m.setNoRate(rd.getNoRate(id));
            m.setSumRate(rd.getSumRate(id));

            String b, p5, p4, p3, p2, p1;
            if (rd.getNoRate(id) != 0) {
                b = decimalFormat.format((double) rd.getSumRate(id) / (double) rd.getNoRate(id) * 100);
                p5 = decimalFormat.format((double) rd.getNoRate5(id) / (double) rd.getNoRate(id) * 100);
                p4 = decimalFormat.format((double) rd.getNoRate4(id) / (double) rd.getNoRate(id) * 100);
                p3 = decimalFormat.format((double) rd.getNoRate3(id) / (double) rd.getNoRate(id) * 100);
                p2 = decimalFormat.format((double) rd.getNoRate2(id) / (double) rd.getNoRate(id) * 100);
                p1 = decimalFormat.format((double) rd.getNoRate1(id) / (double) rd.getNoRate(id) * 100);
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

            int noD = rd.getNoRateByTime(dS, eS, id);
            int sumD = rd.getSumRateByTime(dS, eS, id);
            double avr = 0;
            if (noD != 0) {
                avr = Double.parseDouble(decimalFormat.format((double) sumD / (double) noD));
            } else {
                avr = 0;
            }

            int no5 = rd.getNoRateDByTime(dS, eS, 5, id);
            int no4 = rd.getNoRateDByTime(dS, eS, 4, id);
            int no3 = rd.getNoRateDByTime(dS, eS, 3, id);
            int no2 = rd.getNoRateDByTime(dS, eS, 2, id);
            int no1 = rd.getNoRateDByTime(dS, eS, 1, id);

            String pc5 = "", pc4 = "", pc3 = "", pc2 = "", pc1 = "";
            if (noD != 0) {
                pc5 = decimalFormat.format((double) no5 / (double) noD * 100);
                pc4 = decimalFormat.format((double) no4 / (double) noD * 100);
                pc3 = decimalFormat.format((double) no3 / (double) noD * 100);
                pc2 = decimalFormat.format((double) no2 / (double) noD * 100);
                pc1 = decimalFormat.format((double) no1 / (double) noD * 100);
            } else {
                pc5 = "0";
                pc4 = "0";
                pc3 = "0";
                pc2 = "0";
                pc1 = "0";
            }
            List<Rate> list = rd.getCommentByTime(dS, eS, id);
            String page_raw = request.getParameter("page");
            int page = 1;
            if (page_raw != null) {
                page = Integer.parseInt(page_raw);
            }
            int numPerPage = 5;
            int totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
            int startP = (page - 1) * 5;
            int endP = (page == totalPage) ? (list.size() - 1) : (page * numPerPage - 1);

            MovieRate mr = new MovieRate(noD, sumD, avr, no5, no4, no3, no2, no1, pc5, pc4, pc3, pc2, pc1);

            TicketDAO tkd = new TicketDAO();
            int numTick = tkd.getNumTicketSellByTimeAC(dS, eS, id, cinID);
            if (!rd.getRateByPage(list, startP, endP).isEmpty()) {
                request.setAttribute("listPerPage", rd.getRateByPage(list, startP, endP));
            } else {
                request.setAttribute("msC", "Bộ phim này chưa có lượt bình luận nào");
            }

            List<String> listTT = tkd.getAllTickTypeByTimeAC(dS, eS, id, cinID);
            for (int i = 0; i < listTT.size(); i++) {
                if (listTT.get(i).equals("NM")) {
                    listTT.set(i, "Thường");
                } else if (listTT.get(i).equals("VP")) {
                    listTT.set(i, "VIP");
                } else if (listTT.get(i).equals("VT")) {
                    listTT.set(i, "Đôi");
                }
            }

            int nm = tkd.getNumTickTypeSellByTimeAC(dS, eS, id, "NM", cinID);
            int vp = tkd.getNumTickTypeSellByTimeAC(dS, eS, id, "VP", cinID);
            int vt = tkd.getNumTickTypeSellByTimeAC(dS, eS, id, "VT", cinID);
            String pcnm = "", pcvp = "", pcvt = "";
            if (numTick != 0) {
                pcnm = decimalFormat.format((double) nm / (double) numTick * 100);
                pcvp = decimalFormat.format((double) vp / (double) numTick * 100);
                pcvt = decimalFormat.format((double) vt / (double) numTick * 100);
            } else {
                pcnm = "0";
                pcvp = "0";
                pcvt = "0";
            }

            MovieTicket mt = new MovieTicket(nm, vp, vt, pcnm, pcvp, pcvt);
            List<MovieForm> listMF = new ArrayList<>();
            ScheDAO sd = new ScheDAO();
            List<Schedule> listS = sd.getScheTypeByTimeAC(dS, eS, id, cinID);
            for (int i = 0; i < listS.size(); i++) {
                String PC = "";
                if (numTick == 0) {
                    PC = "0";
                } else {
                    PC = decimalFormat.format((double) tkd.getNumTickFormByTimeAC(dS, eS, id, listS.get(i).getFormID(), cinID) / (double) numTick * 100);
                }
                listMF.add(new MovieForm(listS.get(i).getFormID(), listS.get(i).getFormName(), tkd.getNumTickFormByTimeAC(dS, eS, id, listS.get(i).getFormID(), cinID), PC));
            }
            List<TIcketDate> listTID = tkd.getAllTicketBoughtDateByTimeAC(dS, eS, id, cinID);
            for (int i = 0; i < listTID.size(); i++) {
                String p = "";
                cnt = 0;
                t = "";
                for (int j = 0; j < listTID.get(i).getdS().length(); j++) {

                    if (listTID.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                        p += listTID.get(i).getdS().substring(0, j);
                        cnt = j;
                    } else if (listTID.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                        t = p;
                        p = listTID.get(i).getdS().substring(cnt, j + 1);
                        p += t;
                        cnt = j;
                        break;
                    }
                }
                t = p;
                p = listTID.get(i).getdS().substring(cnt + 1);
                p += t;
                listTID.get(i).setNo(tkd.getNumTickByDateAC(dS, eS, id, Date.valueOf(p), cinID));
                if (numTick != 0) {
                    listTID.get(i).setPc(decimalFormat.format((double) listTID.get(i).getNo() / (double) numTick * 100));
                } else {
                    listTID.get(i).setPc("0");
                }
            }
            if (rd.getNoRate(id) != 0) {
                request.setAttribute("pcARate", decimalFormat.format((double) mr.getNoD() / (double) rd.getNoRate(id) * 100));
            } else {
                request.setAttribute("pcARate", 0);
            }
            if (tkd.getNumTickByMovIDAC(id, cinID) != 0) {
                request.setAttribute("pcATick", decimalFormat.format((double) numTick / (double) tkd.getNumTickByMovIDAC(id, cinID) * 100));
            } else {
                request.setAttribute("pcATick", 0);
            }
            System.out.println("9");
            //numSellEachDay
            for (int i = 0; i < listTID.size(); i++) {
                String p = "";
                cnt = 0;
                t = "";
                for (int j = 0; j < listTID.get(i).getdS().length(); j++) {

                    if (listTID.get(i).getdS().charAt(j) == '-' && cnt == 0) {
                        p += listTID.get(i).getdS().substring(0, j);
                        cnt = j;
                    } else if (listTID.get(i).getdS().charAt(j) == '-' && cnt != 0) {
                        t = p;
                        p = listTID.get(i).getdS().substring(cnt, j + 1);
                        p += t;
                        cnt = j;
                        break;
                    }
                }
                t = p;
                p = listTID.get(i).getdS().substring(cnt + 1);
                p += t;

                int numInDay = listTID.get(i).getNo();

                List<TIcketDate> TID2 = new ArrayList<>();
                if (numInDay != 0) {
                    String PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "NM", cinID) / (double) numInDay * 100);
                    TID2.add(new TIcketDate("Thường", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "NM", cinID), PC));

                    PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VP", cinID) / (double) numInDay * 100);
                    TID2.add(new TIcketDate("VIP", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VP", cinID), PC));

                    PC = decimalFormat.format((double) tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VT", cinID) / (double) numInDay * 100);
                    TID2.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VT", cinID), PC));
                    listTID.get(i).setTkd(TID2);
                } else {
                    String PC = "0";
                    TID2.add(new TIcketDate("NM", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "NM", cinID), PC));
                    TID2.add(new TIcketDate("VP", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VP", cinID), PC));
                    TID2.add(new TIcketDate("VT", tkd.getNumTickTypeSellByDateEXCTLYAC(Date.valueOf(p), 0, "VT", cinID), PC));
                    listTID.get(i).setTkd(TID2);
                }
            }

            int allTick = tkd.getAllNumTickOfMoviesAC(dS, eS, id, cinID);
            List<Cinema> cin = cnd.getAllCinema();
            List<TIcketDate> listTID5 = new ArrayList<>();
            for (int i = 0; i < cin.size(); i++) {
                if (numTick == 0) {
                    String PC = "0";
                    listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCADMV(dS, eS, cin.get(i).getCinID(), id), PC));
                } else {
                    String PC = decimalFormat.format((double) tkd.getNumTickSellByCADMV(dS, eS, cin.get(i).getCinID(), id) / (double) numTick * 100);
                    listTID5.add(new TIcketDate(cin.get(i).getCinName(), tkd.getNumTickSellByCADMV(dS, eS, cin.get(i).getCinID(), id), PC));
                }

            }

            String tPC;
            if (allTick == 0) {
                tPC = "0";
            } else {
                tPC = decimalFormat.format((double) numTick / (double) allTick * 100);
            }
            request.setAttribute("listTID5", listTID5);
            request.setAttribute("cin", cnd.getCinemaByID(cinID));
            request.setAttribute("tPC", tPC);
            request.setAttribute("allTick", allTick);
            request.setAttribute("listTID", listTID);
            request.setAttribute("listMF", listMF);
            request.setAttribute("mt", mt);
            request.setAttribute("listTT", listTT);
            request.setAttribute("numTick", numTick);
            request.setAttribute("id", id);
            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
            request.setAttribute("mr", mr);
            request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
            request.setAttribute("m", m);
            request.getRequestDispatcher("mvRpD.jsp").forward(request, response);
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
