/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import dal.CinemaDAO;
import dal.DeviceDAO;
import dal.RoomDAO;
import dal.SeatDAO;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import static java.net.Proxy.Type.HTTP;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.DeviceDistribution;
import model.Room;
import org.eclipse.persistence.exceptions.JSONException;

/**
 *
 * @author acer
 */
public class UpdSeat extends HttpServlet {

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
            out.println("<title>Servlet UpdSeat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdSeat at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        SeatDAO sd = new SeatDAO();
        if (request.getParameter("seatC") != null) {
            int cnt = 0;
            int count = 0;
            int check1 = 0;
            int check2 = 0;
            String seat = request.getParameter("seatC");
            System.out.println(seat);
            if (!seat.equals("")) {
                for (int i = 0; i < seat.length(); i++) {
                    if (seat.charAt(i) == ',') {
                        cnt++;
                    }
                }
                String a[] = new String[cnt + 1];
                for (int i = 0; i < seat.length(); i++) {
                    if (seat.charAt(i) == ',') {
                        check2 = i;
                        a[count] = seat.substring(check1, check2);
                        count++;
                        check1 = check2 + 1;

                    }
                    if (i == seat.length() - 1) {
                        a[count] = seat.substring(check2 + 1);
                    }
                }

                count = 0;
                check1 = 0;
                check2 = 0;
                String col = "";
                String type = "";
                int row = 0;
                int roomID = 0;
                int cinID = 0;
                for (int i = 0; i < a.length; i++) {
                    for (int j = 0; j < a[i].length(); j++) {
                        if (a[i].substring(j, j + 1).equals("-") && check1 == 0) {
                            check1 = 1;
                            count = j;

                        }
                        if (check1 == 1 && a[i].substring(j, j + 1).equals("?")) {
                            col = a[i].substring(count + 1, j);
                            check1++;
                            count = j;

                        } else if (check1 == 2 && a[i].substring(j, j + 1).equals("?")) {
                            row = Integer.parseInt(a[i].substring(count + 1, j));
                            count = j;
                            check1++;
                        } else if (check1 == 3 && a[i].substring(j, j + 1).equals("/") == true) {
                            cinID = Integer.parseInt(a[i].substring(count + 1, j));

                            count = j;
                            check1++;
                            System.out.println(check1);
                        } else if (check1 == 4 && a[i].substring(j, j + 1).equals("-") == true) {
                            roomID = Integer.parseInt(a[i].substring(count + 1, j));
                            count = j;
                            check1++;
                        } else if (check1 == 5 && a[i].substring(j, j + 1).equals("-")) {
                            type = a[i].substring(count + 1, j);
                            count = j;
                            sd.updSeat(cinID, roomID, row, col, type);
                            check1 = 0;
                        }
                    }
                }
                

                CinemaDAO cnd = new CinemaDAO();
                RoomDAO rd = new RoomDAO();
                DeviceDAO dvd = new DeviceDAO();
                RoomDAO rmd = new RoomDAO();

                List<Room> listR = new ArrayList<>();
                if (request.getParameter("cin") == null) {
                    listR = rd.getAllRoomByCinID(1);
                    request.setAttribute("cinID", 1);
                    if (request.getParameter("room") == null) {
                        request.setAttribute("roomID", listR.get(0).getRoomID());
                        if (dvd.getAllDeviceByCAR(1, listR.get(0).getRoomID()).isEmpty()) {
                            request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                        }
                        List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(1, listR.get(0).getRoomID());
                        for (int i = 0; i < listDevice.size(); i++) {
                            listDevice.get(i).setStatus("NORMAL");
                            for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                                if (listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                                    listDevice.get(i).setStatus("ERROR");
                                }
                            }
                        }
                        request.setAttribute("tk", sd.selectSeatByRoomIDAndCinID(1, listR.get(0).getRoomID()));
                        request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(1, listR.get(0).getRoomID()));
                        request.setAttribute("listDevice", listDevice);
                    } else {
                        int room = 1;
                        try {
                            room = Integer.parseInt(request.getParameter("room"));
                        } catch (Exception e) {
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        request.setAttribute("roomID", room);
                        if (dvd.getAllDeviceByCAR(1, room).isEmpty()) {
                            request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                        }
                        List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(1, room);
                        for (int i = 0; i < listDevice.size(); i++) {
                            listDevice.get(i).setStatus("NORMAL");
                            for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {

                                if (listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
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
                        if (dvd.getAllDeviceByCAR(cin, listR.get(0).getRoomID()).isEmpty()) {
                            request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                        }
                        List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(cin, listR.get(0).getRoomID());
                        for (int i = 0; i < listDevice.size(); i++) {
                            listDevice.get(i).setStatus("NORMAL");
                            for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                                if (listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                                    listDevice.get(i).setStatus("ERROR");
                                }
                            }
                        }
                        request.setAttribute("tk", sd.selectSeatByRoomIDAndCinID(listR.get(0).getRoomID(), cin));
                        request.setAttribute("room", rmd.getRoomByRoomIDAndCinID(listR.get(0).getRoomID(), cin));
                        request.setAttribute("listDevice", listDevice);
                    } else {
                        int room = 1;
                        try {
                            room = Integer.parseInt(request.getParameter("room"));
                        } catch (Exception e) {
                            request.getRequestDispatcher("error.jsp").forward(request, response);
                        }
                        request.setAttribute("roomID", room);
                        if (dvd.getAllDeviceByCAR(cin, room).isEmpty()) {
                            request.setAttribute("ms", "Phòng này hiện chưa có thiết bị nào");
                        }
                        List<DeviceDistribution> listDevice = dvd.getAllDeviceByCAR(cin, room);
                        for (int i = 0; i < listDevice.size(); i++) {
                            listDevice.get(i).setStatus("NORMAL");
                            for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                                if (listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
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
                //request.getRequestDispatcher("roomRp.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

    }

    public String getJson(String jsonLine) {
        JsonElement jelement = new JsonParser().parse(jsonLine);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("data");
        JsonArray jarray = jobject.getAsJsonArray("translations");
        jobject = jarray.get(0).getAsJsonObject();
        String result = jobject.get("translatedText").getAsString();
        return result;
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
