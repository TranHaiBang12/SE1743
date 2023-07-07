/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CinemaDAO;
import dal.DeviceDAO;
import dal.FoodDAO;
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
import model.Cinema;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class CinemaReport extends HttpServlet {
   
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
            out.println("<title>Servlet CinemaReport</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CinemaReport at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("cinRp.jsp").forward(request, response);
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        CinemaDAO cnd = new CinemaDAO();
        DeviceDAO dvd = new DeviceDAO();
        TicketDAO tkd = new TicketDAO();
        FoodDAO fd = new FoodDAO();
        
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
        
        
        List<Cinema> list = cnd.getAllCinema();
        List<TIcketDate> listTID1 = new ArrayList<>();
        
        
        //doanh thu tat ca rap
        int a[] = new int[list.size()];
        int numOnlAllT = 0;
        int numOffAllT = 0;
        int numOnlAllF = 0;
        int numOffAllF = 0;
        for (int i = 0; i < list.size(); i++) {
            numOnlAllT += tkd.getOnlineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            numOffAllT += tkd.getOfflineIncomeByCinAD(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            numOnlAllF += fd.getIncomeOnlByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
            numOffAllF += fd.getIncomeOffByDAC(Date.valueOf(start_raw), Date.valueOf(end_raw), list.get(i).getCinID());
        }
        
        
        
        for (int i = 0; i < list.size(); i++) {
            String PCOnlT = "0";
            String PCOffT = "0";
            String PCOnlF = "0";
            String PCOffF = "0";
        }
        
        
        //onl va off
        int numOnlA = numOnlAllT + numOnlAllF;
        int numOffA = numOffAllT + numOffAllF;
        
        String PcOnlA = "0";
        String PcOffA = "0";
        if(numOnlA + numOffA != 0) {
            PcOnlA = decimalFormat.format((double)numOnlA / (double)(numOnlA + numOffA));
            PcOffA = decimalFormat.format((double)numOffA / (double)(numOnlA + numOffA));
        }
        
        TIcketDate TIDonl = new TIcketDate("Website", numOnlA, PcOnlA);
        TIcketDate TIDoff = new TIcketDate("Trực tiếp", numOffA, PcOffA);
        
        String PcOnlT = "0";
        String PcOffT = "0";
        String PcOnlF = "0";
        String PcOffF = "0";
        if(numOnlA != 0) {
            PcOnlT = decimalFormat.format((double)numOnlAllT / (double)(numOnlA));
            PcOnlF = decimalFormat.format((double)numOnlAllF / (double)(numOnlA));
        }
        
        listTID1.add(new TIcketDate("Vé", numOnlAllT, PcOnlT));
        listTID1.add(new TIcketDate("Đồ Ăn", numOnlAllF, PcOnlF));
        
        List<TIcketDate> listTID2 = new ArrayList<>();
        
        if(numOffA != 0) {
            PcOffT = decimalFormat.format((double)numOffAllT / (double)(numOffA));
            PcOffF = decimalFormat.format((double)numOffAllF / (double)(numOffA));
        }
        
        listTID2.add(new TIcketDate("Vé", numOffAllT, PcOffT));
        listTID2.add(new TIcketDate("Đồ Ăn", numOffAllF, PcOffF));
        
        request.setAttribute("listTID1", listTID1);
        request.setAttribute("listTID2", listTID2);
        
        request.setAttribute("TIDonl", TIDonl);
        request.setAttribute("TIDoff", TIDoff);
        request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
        request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
        request.setAttribute("check", 1);
        request.getRequestDispatcher("cinRp.jsp").forward(request, response);
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
