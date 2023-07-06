/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.DeviceDAO;
import dal.RoomDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import model.DeviceDistribution;
import model.DeviceImport;

/**
 *
 * @author acer
 */
public class AddDvDist extends HttpServlet {

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
            out.println("<title>Servlet AddDvDist</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddDvDist at " + request.getContextPath() + "</h1>");
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
        CinemaDAO cnd = new CinemaDAO();
        RoomDAO rd = new RoomDAO();
        DeviceDAO dvd = new DeviceDAO();

        request.setAttribute("cinID", 1);
        request.setAttribute("roomID", rd.getAllRoomByCinID(1).get(0).getRoomID());
        request.setAttribute("code", dvd.getAllDeviceImpCode());
        request.setAttribute("cin", cnd.getAllCinema());
        request.setAttribute("room", rd.getAllRoomByCinID(1));
        request.getRequestDispatcher("addDvD.jsp").forward(request, response);
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
        CinemaDAO cnd = new CinemaDAO();
        RoomDAO rd = new RoomDAO();
        DeviceDAO dvd = new DeviceDAO();

        if (request.getParameter("check").equals("0")) {
            String code = request.getParameter("code");
            String cinID_raw = request.getParameter("cin");
            String roomID_raw = request.getParameter("room");
            String dte_raw = request.getParameter("dte");
            String tme_raw = request.getParameter("tme");
            String barcode = request.getParameter("bar");
            System.out.println(dte_raw + " "+ tme_raw);
            Timestamp t = Timestamp.valueOf(dte_raw + " " + tme_raw + ":00");

            int cinID = 1;
            int roomID = 0;
            try {
                cinID = Integer.parseInt(cinID_raw);
                roomID = Integer.parseInt(roomID_raw);
                if (cnd.getCinemaByID(cinID) == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                System.out.println("3");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            if (dvd.checkBarCode(barcode) == null) {
                dvd.insertDeviceDist(code, cinID, roomID, t, barcode);
                DeviceImport di = dvd.getDeviceByCode(code);
                DeviceDistribution device = dvd.getAllDeviceByCAR(barcode);
                String ms = "Thêm thành công";
                request.setAttribute("check", 1);
                request.setAttribute("ms", ms);
                request.setAttribute("device", device);
                request.setAttribute("img", di.getImg());
                request.getRequestDispatcher("addDvD.jsp").forward(request, response);
            } else {
                String ms = "Barcode đã tồn tại";
                request.setAttribute("ms", ms);
                request.setAttribute("cinID", cinID);
                request.setAttribute("roomID", rd.getAllRoomByCinID(cinID).get(0).getRoomID());
                request.setAttribute("code", dvd.getAllDeviceImpCode());
                request.setAttribute("cin", cnd.getAllCinema());
                request.setAttribute("room", rd.getAllRoomByCinID(cinID));
                request.getRequestDispatcher("addDvD.jsp").forward(request, response);
            }

        } else if (request.getParameter("check").equals("1")) {
            String cin_raw = request.getParameter("cin");
            int cinID = 1;
            try {
                cinID = Integer.parseInt(cin_raw);
                if (cnd.getCinemaByID(cinID) == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                System.out.println("4");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.setAttribute("cinID", cinID);
            request.setAttribute("roomID", rd.getAllRoomByCinID(cinID).get(0).getRoomID());
            request.setAttribute("code", dvd.getAllDeviceImpCode());
            request.setAttribute("cin", cnd.getAllCinema());
            request.setAttribute("room", rd.getAllRoomByCinID(cinID));
            request.getRequestDispatcher("addDvD.jsp").forward(request, response);
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
