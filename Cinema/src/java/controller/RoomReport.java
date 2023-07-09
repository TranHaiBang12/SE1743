/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CinemaDAO;
import dal.DeviceDAO;
import dal.OrderDAO;

import dal.RoomDAO;
import dal.SeatDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.DeviceDistribution;
import model.Room;

/**
 *
 * @author acer
 */
public class RoomReport extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RoomReport</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoomReport at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
        SeatDAO sd = new SeatDAO();
        RoomDAO rmd = new RoomDAO();

        List<Room> listR = new ArrayList<>();
        if (request.getParameter("cin") == null) {
            listR = rd.getAllRoomByCinID(1);
            request.setAttribute("cinID", 1);
            if (request.getParameter("room") == null) {
                System.out.println("am");
                request.setAttribute("roomID", listR.get(0).getRoomID());
                if(dvd.getAllDeviceByCAR(1, listR.get(0).getRoomID()).isEmpty()) {
                    request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                }
                List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(1, listR.get(0).getRoomID());
                for (int i = 0; i < listDevice.size(); i++) {
                    listDevice.get(i).setStatus("NORMAL");
                    for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                        if(listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                            listDevice.get(i).setStatus("ERROR");
                        }
                    }
                }
                System.out.println(listR.get(0).getRoomID());
                request.setAttribute("tk", sd.selectSeatByRoomIDAndCinID(listR.get(0).getRoomID(), 1));
                request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(listR.get(0).getRoomID(), 1));
                request.setAttribute("listDevice", listDevice);
            }
            else {
                int room = 1;
                try {
                    room = Integer.parseInt(request.getParameter("room"));
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("roomID", room);
                if(dvd.getAllDeviceByCAR(1, room).isEmpty()) {
                    request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                }
                List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(1, room);
                for (int i = 0; i < listDevice.size(); i++) {
                    listDevice.get(i).setStatus("NORMAL");
                    for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                        
                        if(listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                            listDevice.get(i).setStatus("ERROR");
                        }
                    }
                }
                request.setAttribute("tk", sd.selectSeatByRoomIDAndCinID(room, 1));
                request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(room, 1));
                request.setAttribute("listDevice", listDevice);
            }
        } else {
            String cin_raw = request.getParameter("cin");
            int cin = 1;
            try {
                cin = Integer.parseInt(cin_raw);
                if (rd.getAllRoomByCinID(cin) == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            listR = rd.getAllRoomByCinID(cin);
            request.setAttribute("cinID", cin);
            

            if (request.getParameter("room") == null) {
                request.setAttribute("roomID", listR.get(0).getRoomID());
                if(dvd.getAllDeviceByCAR(cin, listR.get(0).getRoomID()).isEmpty()) {
                    request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                }
                List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(cin, listR.get(0).getRoomID());
                for (int i = 0; i < listDevice.size(); i++) {
                    listDevice.get(i).setStatus("NORMAL");
                    for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                        if(listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                            listDevice.get(i).setStatus("ERROR");
                        }
                    }
                }
                request.setAttribute("tk", sd.selectSeatByRoomIDAndCinID(listR.get(0).getRoomID(), cin));
                request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(listR.get(0).getRoomID(), cin));
                request.setAttribute("listDevice", listDevice);
            }
            else {
                int room = 1;
                try {
                    room = Integer.parseInt(request.getParameter("room"));
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                request.setAttribute("roomID", room);
                if(dvd.getAllDeviceByCAR(cin, room).isEmpty()) {
                    request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                }
                List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(cin, room);
                for (int i = 0; i < listDevice.size(); i++) {
                    listDevice.get(i).setStatus("NORMAL");
                    for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                        if(listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                            listDevice.get(i).setStatus("ERROR");
                        }
                    }
                }
                request.setAttribute("tk", sd.selectSeatByRoomIDAndCinID(room, cin));
                request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(room, cin));
                request.setAttribute("listDevice", listDevice);
            }
        }
        
        
        request.setAttribute("listRoom", listR);
        request.setAttribute("listCin", cnd.getAllCinema());
        request.getRequestDispatcher("roomRp.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
