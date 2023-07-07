/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.MovieDAO;
import dal.RateDAO;
import dal.ScheDAO;
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
import model.Movies;

/**
 *
 * @author acer
 */
public class MovieReport extends HttpServlet {

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
            out.println("<title>Servlet MovieReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MovieReport at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("cin") == null) {
            request.getRequestDispatcher("mvRp.jsp").forward(request, response);
        } else {
            CinemaDAO cnd = new CinemaDAO();
            String start = request.getParameter("start");
            String end = request.getParameter("end");
            String cinID_raw = request.getParameter("cin");
            int cinID = 1;
            try {
                cinID = Integer.parseInt(cinID_raw);
                if(cnd.getCinemaByID(cinID) == null) {
                    throw new Exception("lOI");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
             ScheDAO scd = new ScheDAO();
            int a[] = scd.getAllScheInTimeAC(Date.valueOf(start), Date.valueOf(end), cinID);
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
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
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
            MovieDAO mvd = new MovieDAO();
            List<Movies> listM = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                Movies m = mvd.getMovieById(a[i]);
                m.setNoRate(rd.getNoRate(a[i]));
                m.setNoRate5(rd.getNoRate5(a[i]));
                m.setNoRate4(rd.getNoRate4(a[i]));
                m.setNoRate3(rd.getNoRate3(a[i]));
                m.setNoRate2(rd.getNoRate2(a[i]));
                m.setNoRate1(rd.getNoRate1(a[i]));
                m.setNoRate(rd.getNoRate(a[i]));
                m.setSumRate(rd.getSumRate(a[i]));

                System.out.println(rd.getSumRate(a[i]) + " " + rd.getNoRate(a[i]));
                String b, p5, p4, p3, p2, p1;
                if (rd.getNoRate(a[i]) != 0) {
                    b = decimalFormat.format((double) rd.getSumRate(a[i]) / (double) rd.getNoRate(a[i]));
                    p5 = decimalFormat.format((double) rd.getNoRate5(a[i]) / (double) rd.getNoRate(a[i]));
                    p4 = decimalFormat.format((double) rd.getNoRate4(a[i]) / (double) rd.getNoRate(a[i]));
                    p3 = decimalFormat.format((double) rd.getNoRate3(a[i]) / (double) rd.getNoRate(a[i]));
                    p2 = decimalFormat.format((double) rd.getNoRate2(a[i]) / (double) rd.getNoRate(a[i]));
                    p1 = decimalFormat.format((double) rd.getNoRate1(a[i]) / (double) rd.getNoRate(a[i]));
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
                listM.add(m);

            }
            if (listM.isEmpty()) {
                request.setAttribute("msT", "Không có bất kỳ bộ phim nào được chiếu trong khung giờ này");
            }
            request.setAttribute("cin", cnd.getCinemaByID(cinID));
            request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
            request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
            request.setAttribute("startR", start);
            request.setAttribute("endR", end);
            request.setAttribute("listM", listM);
            request.getRequestDispatcher("mvRp.jsp").forward(request, response);
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
        String start = request.getParameter("start");
        String end = request.getParameter("end");
        if (start.equals("") || end.equals("")) {
            String ms = "Vui lòng nhập cả ngày tháng bắt đầu và kết thúc để xem thống kê trong khoảng thời gian này";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("mvRp.jsp").forward(request, response);
        } else {
            ScheDAO scd = new ScheDAO();
            int a[] = scd.getAllScheInTime(Date.valueOf(start), Date.valueOf(end));
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
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
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
            MovieDAO mvd = new MovieDAO();
            List<Movies> listM = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                Movies m = mvd.getMovieById(a[i]);
                m.setNoRate(rd.getNoRate(a[i]));
                m.setNoRate5(rd.getNoRate5(a[i]));
                m.setNoRate4(rd.getNoRate4(a[i]));
                m.setNoRate3(rd.getNoRate3(a[i]));
                m.setNoRate2(rd.getNoRate2(a[i]));
                m.setNoRate1(rd.getNoRate1(a[i]));
                m.setNoRate(rd.getNoRate(a[i]));
                m.setSumRate(rd.getSumRate(a[i]));

                System.out.println(rd.getSumRate(a[i]) + " " + rd.getNoRate(a[i]));
                String b, p5, p4, p3, p2, p1;
                if (rd.getNoRate(a[i]) != 0) {
                    b = decimalFormat.format((double) rd.getSumRate(a[i]) / (double) rd.getNoRate(a[i]));
                    p5 = decimalFormat.format((double) rd.getNoRate5(a[i]) / (double) rd.getNoRate(a[i]));
                    p4 = decimalFormat.format((double) rd.getNoRate4(a[i]) / (double) rd.getNoRate(a[i]));
                    p3 = decimalFormat.format((double) rd.getNoRate3(a[i]) / (double) rd.getNoRate(a[i]));
                    p2 = decimalFormat.format((double) rd.getNoRate2(a[i]) / (double) rd.getNoRate(a[i]));
                    p1 = decimalFormat.format((double) rd.getNoRate1(a[i]) / (double) rd.getNoRate(a[i]));
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
                listM.add(m);

            }
            if (listM.isEmpty()) {
                request.setAttribute("msT", "Không có bất kỳ bộ phim nào được chiếu trong khung giờ này");
            }
            request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
            request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
            request.setAttribute("listM", listM);
            request.getRequestDispatcher("mvRp.jsp").forward(request, response);
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
