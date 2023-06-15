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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cinema;
import model.DateMD;

/**
 *
 * @author acer
 */
public class CinemaServlet extends HttpServlet {

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
            out.println("<title>Servlet Cinema</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cinema at " + request.getContextPath() + "</h1>");
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
        CinemaDAO cnd = new CinemaDAO();
        List<Cinema> listM = new ArrayList<>();
        List<String> listLoc = new ArrayList<>();
        String loc = request.getParameter("loc");
        String cinID_raw = request.getParameter("cinID");
        int id = 0;
        if (cinID_raw == null) {
            if (loc == null) {
                if (id_raw == null) {
                    listLoc = cnd.getCinemaLocation();
                } else {
                    id = Integer.parseInt(id_raw);
                    if (id == 1) {
                        listLoc = cnd.getAllCinemaLocByType(1);
                    } else if (id == 2) {
                        listLoc = cnd.getAllCinemaLocByType(2);
                    } else if (id == 3) {
                        listLoc = cnd.getAllCinemaLocByType(3);
                    } else if (id == 0) {
                        listLoc = cnd.getCinemaLocation();
                    }
                }
                request.setAttribute("type", id);
                request.setAttribute("listLoc", listLoc);
                request.getRequestDispatcher("cinema.jsp").forward(request, response);
            } else {
                id = Integer.parseInt(id_raw);
                if (id == 1) {
                    listLoc = cnd.getAllCinemaLocByType(1);
                    listM = cnd.getAllCinemaByTypeALoc(1, loc);
                } else if (id == 2) {
                    listLoc = cnd.getAllCinemaLocByType(2);
                    listM = cnd.getAllCinemaByTypeALoc(2, loc);
                } else if (id == 3) {
                    listLoc = cnd.getAllCinemaLocByType(3);
                    listM = cnd.getAllCinemaByTypeALoc(3, loc);
                } else if (id == 0) {
                    listLoc = cnd.getCinemaLocation();
                    listM = cnd.getAllCinemaByTypeALoc(0, loc);
                }
                request.setAttribute("type", id);
                request.setAttribute("loc", loc);
                request.setAttribute("listM", listM);
                request.setAttribute("listLoc", listLoc);
                request.getRequestDispatcher("cinema.jsp").forward(request, response);
            }
        } else {
            int cnt = 0;
            MovieDAO mvd = new MovieDAO();

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

            String t = "2023-05-01";
            Date date1 = Date.valueOf(t);
            for (int i = 0; i < 30; i++) {
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
            int cinID = Integer.parseInt(cinID_raw);
            id_raw = request.getParameter("id");
            id = Integer.parseInt(id_raw);
            listLoc = cnd.getAllCinemaLocByType(id);
            listM = cnd.getAllCinemaByTypeALoc(id, request.getParameter("loc"));
            Cinema m = cnd.getCinemaByID(cinID);
            request.setAttribute("id", id);
            request.setAttribute("listM", listM);
            request.setAttribute("listLoc", listLoc);
            request.setAttribute("cinID", cinID);
            request.setAttribute("dte", dte);
            request.setAttribute("loc", request.getParameter("loc"));
            request.setAttribute("m", m);
            request.getRequestDispatcher("cinema.jsp").forward(request, response);
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
