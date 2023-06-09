/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;
import dal.CinemaDAO;
import dal.DeviceDAO;
import dal.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.DeviceDistribution;

/**
 *
 * @author acer
 */
public class UpdDevBC extends HttpServlet {

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
            out.println("<title>Servlet UpdDevBC</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdDevBC at " + request.getContextPath() + "</h1>");
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
        DeviceDAO dvd = new DeviceDAO();

        if (request.getParameter("id") == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            String id = request.getParameter("id");
            DeviceDistribution dd = dvd.getAllDeviceByCAR(id);
            try {
                if (dd == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            dd.setStatus("NORMAL");
            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(id)) {
                    dd.setStatus("ERROR");
                }
            }
            CinemaDAO cnd = new CinemaDAO();
            RoomDAO rd = new RoomDAO();
            List<String> stat = new ArrayList<>();
            stat.add("NORMAL");
            stat.add("ERROR");
            int cinID = dd.getCinID();
            request.setAttribute("id", id);
            request.setAttribute("cinID", cinID);
            request.setAttribute("cin", cnd.getAllCinema());
            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
            request.setAttribute("dd", dd);
            request.setAttribute("type", dvd.getAllDeviceType());
            request.setAttribute("stat", stat);
            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
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
        System.out.println("1");
        Time tme = Time.valueOf(Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes() + ":" + Calendar.getInstance().getTime().getSeconds());
        Date dte = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());

        if (request.getParameter("check").equals("1")) {
            DeviceDAO dvd = new DeviceDAO();

            if (request.getParameter("id") == null) {
                System.out.println("p");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            } else {
                String id = request.getParameter("id");
                System.out.println(id);
                DeviceDistribution dd = dvd.getAllDeviceByCAR(id);
                try {
                    if (dd == null) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    System.out.println("p1");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                dd.setStatus("NORMAL");
                for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                    if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(id)) {
                        //        dd.setStatus("ERROR");
                    }
                }
                CinemaDAO cnd = new CinemaDAO();
                RoomDAO rd = new RoomDAO();
                List<String> stat = new ArrayList<>();
                stat.add("NORMAL");
                stat.add("ERROR");
                String cinID_raw = request.getParameter("cin");
                int cinID = 1;
                try {
                    cinID = Integer.parseInt(cinID_raw);
                    if (cnd.getCinemaByID(cinID) == null) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    System.out.println("p2");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                
                request.setAttribute("id", id);
                request.setAttribute("cinID", cinID);
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("room", rd.getAllRoomByCinID(cinID));
                request.setAttribute("dd", dd);
                request.setAttribute("type", dvd.getAllDeviceType());
                request.setAttribute("stat", stat);
                request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
            }
        } else {
            String cinID_raw = request.getParameter("cin");
            String roomID_raw = request.getParameter("room");
            String dte_raw = request.getParameter("dte");
            String tme_raw = request.getParameter("tme");
            String bar = request.getParameter("bar");
            String stat = request.getParameter("stat");
            String ostat = request.getParameter("ostat");
            String obar = request.getParameter("obar");
            
            Timestamp t = Timestamp.valueOf(dte_raw + " " + tme_raw);

            RoomDAO rd = new RoomDAO();
            int cinID = 1;
            int roomID = 0;
            try {
                cinID = Integer.parseInt(cinID_raw);
                roomID = Integer.parseInt(roomID_raw);
                if (rd.getRoomByRoomIDAndCinID(roomID, cinID) == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                System.out.println("p4");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            DeviceDAO dvd = new DeviceDAO();
            if (ostat.equals("NORMAL")) {
                System.out.println("1");
                if (stat.equals("NORMAL")) {
                    System.out.println("2");
                    if (!bar.equals(obar)) {
                        if (dvd.checkBarCode(bar) == null) {
                            System.out.println("3");
                            String ms = "Sửa thành công";
                            dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p5");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", bar);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        } else {
                            System.out.println("4");
                            String ms = "Barcode đã tồn tại";
                            String id = request.getParameter("id");
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(id);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p6");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(id)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", id);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("5");
                        String ms = "Sửa thành công";
                        dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                        DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                        try {
                            if (dd == null) {
                                throw new Exception("Loi");
                            }
                        } catch (Exception e) {
                            System.out.println("p7");
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        dd.setStatus("NORMAL");
                        for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                            if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                dd.setStatus("ERROR");
                            }
                        }
                        CinemaDAO cnd = new CinemaDAO();
                        List<String> stat1 = new ArrayList<>();
                        stat1.add("NORMAL");
                        stat1.add("ERROR");
                        int cinID1 = dd.getCinID();
                        request.setAttribute("ms", ms);
                        request.setAttribute("id", bar);
                        request.setAttribute("cinID", cinID1);
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                        request.setAttribute("dd", dd);
                        request.setAttribute("type", dvd.getAllDeviceType());
                        request.setAttribute("stat", stat1);
                        request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                    }
                } else if (stat.equals("ERROR")) {
                    System.out.println("6");
                    if (!bar.equals(obar)) {
                        System.out.println("7");
                        if (dvd.checkBarCode(bar) == null) {
                            System.out.println("8");
                            String ms = "Sửa thành công";
                            dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                            System.out.println(request.getParameter("code"));
                            dvd.insertNewDeviceError(request.getParameter("code"), cinID, roomID, bar);
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p8");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", bar);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        } else {
                            System.out.println("9");
                            String ms = "Barcode đã tồn tại";
                            String id = request.getParameter("id");
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(id);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p9");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(id)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", id);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("10");
                        String ms = "Sửa thành công";
                        dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                        dvd.insertNewDeviceError(request.getParameter("code"), cinID, roomID, bar);
                        DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                        try {
                            if (dd == null) {
                                System.out.println("5");
                                throw new Exception("Loi");
                            }
                        } catch (Exception e) {
                            System.out.println("3");

                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        dd.setStatus("NORMAL");
                        for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                            if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                dd.setStatus("ERROR");
                            }
                        }
                        CinemaDAO cnd = new CinemaDAO();
                        List<String> stat1 = new ArrayList<>();
                        stat1.add("NORMAL");
                        stat1.add("ERROR");
                        int cinID1 = dd.getCinID();
                        request.setAttribute("ms", ms);
                        request.setAttribute("id", bar);
                        request.setAttribute("cinID", cinID1);
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                        request.setAttribute("dd", dd);
                        request.setAttribute("type", dvd.getAllDeviceType());
                        request.setAttribute("stat", stat1);
                        request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                    }
                }

            } else {
                System.out.println("11");
                if (stat.equals("NORMAL")) {
                    String price_raw = request.getParameter("price");

                    double price = 0;
                    try {
                        price = Double.parseDouble(price_raw);
                    } catch (Exception e) {
                        System.out.println("p3");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                    System.out.println("12");
                    if (!bar.equals(obar)) {
                        System.out.println("13");
                        if (dvd.checkBarCode(bar) == null) {
                            System.out.println("14");
                            String ms = "Sửa thành công";
                            dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                            dvd.updDeviceErrorFixed(bar, price);
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p10");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("price", price);
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", bar);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        } else {
                            System.out.println("5");
                            String ms = "Barcode đã tồn tại";
                            String id = request.getParameter("id");
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(id);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p11");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(id)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", id);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("16");
                        String ms = "Sửa thành công";
                        dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                        dvd.updDeviceErrorFixed(bar, price);
                        DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                        try {
                            if (dd == null) {
                                throw new Exception("Loi");
                            }
                        } catch (Exception e) {
                            System.out.println("p12");
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        dd.setStatus("NORMAL");
                        for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                            if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                dd.setStatus("ERROR");
                            }
                        }
                        CinemaDAO cnd = new CinemaDAO();
                        List<String> stat1 = new ArrayList<>();
                        stat1.add("NORMAL");
                        stat1.add("ERROR");
                        int cinID1 = dd.getCinID();
                        request.setAttribute("ms", ms);
                        request.setAttribute("id", bar);
                        request.setAttribute("cinID", cinID1);
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                        request.setAttribute("dd", dd);
                        request.setAttribute("type", dvd.getAllDeviceType());
                        request.setAttribute("stat", stat1);
                        request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                    }
                } else if (stat.equals("ERROR")) {
                    System.out.println("17");
                    if (!bar.equals(obar)) {
                        System.out.println("18");
                        if (dvd.checkBarCode(bar) == null) {
                            System.out.println("19");
                            String ms = "Sửa thành công";
                            dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                            dvd.updDeviceErrorNotFixed(bar);
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p13");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", bar);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        } else {
                            System.out.println("20");
                            String ms = "Barcode đã tồn tại";
                            String id = request.getParameter("id");
                            DeviceDistribution dd = dvd.getAllDeviceByCAR(id);
                            try {
                                if (dd == null) {
                                    throw new Exception("Loi");
                                }
                            } catch (Exception e) {
                                System.out.println("p14");
                                request.getRequestDispatcher("error.jsp").forward(request, response);
                            }
                            dd.setStatus("NORMAL");
                            for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                                if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(id)) {
                                    dd.setStatus("ERROR");
                                }
                            }
                            CinemaDAO cnd = new CinemaDAO();
                            List<String> stat1 = new ArrayList<>();
                            stat1.add("NORMAL");
                            stat1.add("ERROR");
                            int cinID1 = dd.getCinID();
                            request.setAttribute("ms", ms);
                            request.setAttribute("id", id);
                            request.setAttribute("cinID", cinID1);
                            request.setAttribute("cin", cnd.getAllCinema());
                            request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                            request.setAttribute("dd", dd);
                            request.setAttribute("type", dvd.getAllDeviceType());
                            request.setAttribute("stat", stat1);
                            request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                        }
                    } else {
                        System.out.println("21");
                        String ms = "Sửa thành công";
                        dvd.updDeviceDist(bar, cinID, roomID, obar, t);
                        dvd.updDeviceErrorNotFixed(bar);
                        System.out.println(bar);
                        DeviceDistribution dd = dvd.getAllDeviceByCAR(bar);
                        try {
                            if (dd == null) {
                                System.out.println("er");
                                throw new Exception("Loi");
                            }
                        } catch (Exception e) {
                            System.out.println("k");
                            System.out.println("p15");
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        dd.setStatus("NORMAL");
                        for (int i = 0; i < dvd.getAllDeviceError().size(); i++) {
                            if (dvd.getAllDeviceError().get(i).getDeviceBarCode().equals(bar)) {
                                dd.setStatus("ERROR");
                            }
                        }
                        CinemaDAO cnd = new CinemaDAO();
                        List<String> stat1 = new ArrayList<>();
                        stat1.add("NORMAL");
                        stat1.add("ERROR");
                        System.out.println("err");
                        int cinID1 = dd.getCinID();
                        request.setAttribute("ms", ms);
                        request.setAttribute("id", bar);
                        request.setAttribute("cinID", cinID1);
                        request.setAttribute("cin", cnd.getAllCinema());
                        request.setAttribute("room", rd.getAllRoomByCinID(dd.getCinID()));
                        request.setAttribute("dd", dd);
                        request.setAttribute("type", dvd.getAllDeviceType());
                        request.setAttribute("stat", stat1);
                        request.getRequestDispatcher("updDeviceBC.jsp").forward(request, response);
                    }
                }
            }
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
