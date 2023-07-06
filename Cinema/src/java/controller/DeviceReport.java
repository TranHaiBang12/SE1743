/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.DeviceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import model.DeviceDistribution;
import model.DeviceError;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class DeviceReport extends HttpServlet {

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
            out.println("<title>Servlet DeviceReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeviceReport at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("dRp.jsp").forward(request, response);
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        String dateS = "", monthS = "", yearS = "";
        String dateE = "", monthE = "", yearE = "";
        String t = start_raw;
        int cnt = 0;
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
        t = end_raw;
        for (int i = 0; i < t.length(); i++) {
            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                yearE = t.substring(cnt, i);
                cnt = i;
            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                monthE = t.substring(cnt + 1, i);
                cnt = i;
            }
        }
        dateE = t.substring(cnt + 1);

        DeviceDAO dvd = new DeviceDAO();

        //num device import
        int numD = dvd.getNumDeviceDistByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));

        //sum device import
        int sumD = dvd.getSumPriceDeviceDistByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));

        //num device error
        int numE = dvd.getNumDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));

        //sum device error
        int sumE = dvd.getSumPriceDeviceErrorByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));

        String PcD = "0";
        String PcE = "0";
        if (sumD + sumE != 0) {
            PcD = decimalFormat.format((double) sumD / (double) (sumD + sumE));
            PcE = decimalFormat.format((double) sumE / (double) (sumD + sumE));
        }

        TIcketDate TID1 = new TIcketDate("Tổng chi phí nhập thiết bị", sumD, PcD);
        TIcketDate TID2 = new TIcketDate("Tổng chi phí sửa chữa thiết bị", sumE, PcE);

        request.setAttribute("numD", numD);
        request.setAttribute("numE", numE);

        request.setAttribute("TID1", TID1);
        request.setAttribute("TID2", TID2);

        List<DeviceError> listE = dvd.getADeviceErrByDate(Date.valueOf(start_raw), Date.valueOf(end_raw));
        for (int i = 0; i < listE.size(); i++) {
            listE.get(i).setCinName(cnd.getCinemaNameByID(listE.get(i).getCinID()));
        }
        for (int i = 0; i < listE.size(); i++) {
            listE.get(i).setStatus("CHƯA SỬA");
            if (listE.get(i).getDteFixed() != null) {
                listE.get(i).setStatus("ĐÃ SỬA");
            }
        }

        request.setAttribute("listE", listE);

        List<DeviceDistribution> listDevice = dvd.getAllDeviceByD(Date.valueOf(start_raw), Date.valueOf(end_raw));
        for (int i = 0; i < listDevice.size(); i++) {
            listDevice.get(i).setStatus("NORMAL");
            for (int j = 0; j < dvd.getAllDeviceError().size(); j++) {
                if (listDevice.get(i).getDeviceBarCode().equals(dvd.getAllDeviceError().get(j).getDeviceBarCode())) {
                    listDevice.get(i).setStatus("ERROR");
                }
            }
        }
        request.setAttribute("listDevice", listDevice);

        request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
        request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
        request.setAttribute("check", 1);
        request.getRequestDispatcher("dRp.jsp").forward(request, response);
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
