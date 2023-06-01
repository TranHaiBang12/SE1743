/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DateMD;
import model.Movies;

/**
 *
 * @author acer
 */
public class BookingServlet extends HttpServlet {

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
            out.println("<title>Servlet BookingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        MovieDAO mvd = new MovieDAO();
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            if(mvd.getMovieById(id) == null) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            response.sendRedirect("error");
        }
        
        List<Date> date = new ArrayList<>();
        List<DateMD> dte = new ArrayList<>();
        Date d[] = new Date[30];
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
        for (int i = 0; i < d.length; i++) {
            date.add(d[i]);
        }
        CinemaDAO cnm = new CinemaDAO();
        List<String> list = new ArrayList<>();
        list = cnm.getCinemaLocation();

        String t = "2023-05-01";
        Date date1 = Date.valueOf(t);
        for (int i = 0; i < 30; i++) {
            String day;
            String date2;
            String month;
            long time = d[i].getTime() - date1.getTime();
            time = (time / (24 * 60 * 60 * 1000)) % 7;
            if(time == 0) {
                day = "Mon";
            }
            else if(time == 1) {
                day = "Tue";
            }
            else if(time == 2) {
                day = "Wed";
            }
            else if(time == 3) {
                day = "Thu";
            }
            else if(time == 4) {
                day = "Fri";
            }
            else if(time == 5) {
                day = "Sat";
            }
            else {
                day = "Sun";
            }
            if(d[i].getDate() < 10) {
                date2 = "0" + d[i].getDate();
            }
            else {
                date2 = String.valueOf(d[i].getDate());
            }
             if(d[i].getMonth() < 10) {
                month = "0" + (d[i].getMonth() + 1);
            }
            else {
                month = String.valueOf((d[i].getMonth() + 1));
            }
            dte.add(new DateMD(date2, month, day));
        }
        
        Movies m = mvd.getMovieById(id);
        System.out.println(m.getImg());
        request.setAttribute("movie", m);
        request.setAttribute("city", list);
        request.setAttribute("date", dte);
        request.getRequestDispatcher("booking.jsp").forward(request, response);
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
