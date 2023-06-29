/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.MovieRate;
import model.Movies;
import model.Rate;

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
        }
        MovieDAO mvd = new MovieDAO();
        RateDAO rd = new RateDAO();

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
            b = decimalFormat.format((double) rd.getSumRate(id) / (double) rd.getNoRate(id));
            p5 = decimalFormat.format((double) rd.getNoRate5(id) / (double) rd.getNoRate(id));
            p4 = decimalFormat.format((double) rd.getNoRate4(id) / (double) rd.getNoRate(id));
            p3 = decimalFormat.format((double) rd.getNoRate3(id) / (double) rd.getNoRate(id));
            p2 = decimalFormat.format((double) rd.getNoRate2(id) / (double) rd.getNoRate(id));
            p1 = decimalFormat.format((double) rd.getNoRate1(id) / (double) rd.getNoRate(id));
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
            pc5 = decimalFormat.format((double) no5 / (double) noD);
            pc4 = decimalFormat.format((double) no4 / (double) noD);
            pc3 = decimalFormat.format((double) no3 / (double) noD);
            pc2 = decimalFormat.format((double) no2 / (double) noD);
            pc1 = decimalFormat.format((double) no1 / (double) noD);
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
        }
        else {
            request.setAttribute("msC", "Bộ phim này chưa có lượt bình luận nào");
        }
        
        List<String> listTT = tkd.getAllTickTypeByTime(dS, eS, id);
        for (int i = 0; i < listTT.size(); i++) {
            if(listTT.get(i).equals("NM")) {
                listTT.set(i, "Thường");
            }
            else if(listTT.get(i).equals("VP")) {
                listTT.set(i, "VIP");
            }
            else if(listTT.get(i).equals("VT")) {
                listTT.set(i, "Đôi");
            }
        }
        
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
