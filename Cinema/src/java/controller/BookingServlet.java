/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.MovieDAO;
import dal.ScheDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DateMD;
import model.FormMD;
import model.LocationCinMD;
import model.MovieTime;
import model.Movies;
import model.Schedule;
import model.Tme;

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
        int cnt = 0;
        MovieDAO mvd = new MovieDAO();
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            if (mvd.getMovieById(id) == null) {
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

        Movies m = mvd.getMovieById(id);

        List<LocationCinMD> loc = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            loc.add(new LocationCinMD(i, list.get(i)));
        }
        int idSche = 0, idLo = 0, idForm = 0;
        String idSche_raw = request.getParameter("schePick");
        String idLoc_raw = request.getParameter("loPick");
        String idForm_raw = request.getParameter("formPick");

        if (idSche_raw == null) {
            request.setAttribute("schePick", dte.get(0).getId());
        } else {
            try {
                idSche = Integer.parseInt(request.getParameter("schePick"));
                if (idSche < 0 || idLo < 0 || idForm < 0) {
                    throw new Exception("Loi moi");
                }
            } catch (Exception e) {
                response.sendRedirect("error");
            }
            request.setAttribute("schePick", idSche);
        }
        if (idLoc_raw == null) {
            request.setAttribute("loPick", loc.get(0).getId());
        } else {
            try {
                idLo = Integer.parseInt(request.getParameter("loPick"));
                if (idSche < 0 || idLo < 0 || idForm < 0) {
                    throw new Exception("Loi moi");
                }
            } catch (Exception e) {
                response.sendRedirect("error");
            }
            request.setAttribute("loPick", idLo);
        }
        int id_sForm, id_lForm;
        if (idSche_raw == null || idSche_raw.equals("")) {
            id_sForm = dte.get(0).getId();
        } else {
            id_sForm = idSche;
        }
        if (idLoc_raw == null || idLoc_raw.equals("")) {
            id_lForm = loc.get(0).getId();
        } else {
            id_lForm = idLo;
        }
        Date start = date.get(id_sForm);
        Date end = date.get(id_sForm + 1);

        List<String> form = mvd.getAllMovieFormByIdAndLocationAndTime(id, loc.get(id_lForm).getLoc(), start, end);

        List<FormMD> frm = new ArrayList<>();
        for (int i = 0; i < form.size(); i++) {
            frm.add(new FormMD(i, form.get(i)));

        }

        if (frm.isEmpty()) {
            String ms = "Xin lỗi, không có suất chiếu vào ngày này, hãy chọn một ngày khác";
            request.setAttribute("formPick", 0);
            request.setAttribute("ms", ms);
            request.setAttribute("id", id);
            request.setAttribute("movie", m);
            request.setAttribute("city", loc);
            request.setAttribute("date", dte);
            System.out.println("1");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        } else {
            if (idForm_raw == null || idForm_raw.equals("")) {

                request.setAttribute("formPick", frm.get(0).getId());
            } else {
                try {
                    idForm = Integer.parseInt(request.getParameter("formPick"));
                    if (idSche < 0 || idLo < 0 || idForm < 0) {
                        throw new Exception("Loi moi");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("formPick", idForm);
            }
            ScheDAO scd = new ScheDAO();
            List<String> strTme = new ArrayList<>();
      
            List<String> mvNameBySche = scd.getCinNameBySchedule(id, loc.get(id_lForm).getLoc(), start, end, frm.get(idForm).getFormName());
            CinemaDAO cnd = new CinemaDAO();
            List<MovieTime> mvt = new ArrayList<>();
            for (int i = 0; i < mvNameBySche.size(); i++) {
                List<Tme> tme = scd.getMovieTimeBySchedule(id, loc.get(id_lForm).getLoc(), start, end, frm.get(idForm).getFormName(), mvNameBySche.get(i));

                String str = "Rạp " + cnd.getCinTypeByName(mvNameBySche.get(i));
                mvt.add(new MovieTime(mvNameBySche.get(i), str, tme));
            }
            for (int i = 0; i < mvt.size(); i++) {
                System.out.println(mvt.get(i).getName());
            }
            request.setAttribute("mvt", mvt);
            request.setAttribute("id", id);
            request.setAttribute("movie", m);
            request.setAttribute("city", loc);
            request.setAttribute("date", dte);
            request.setAttribute("form", frm);
           request.getRequestDispatcher("booking.jsp").forward(request, response);
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
