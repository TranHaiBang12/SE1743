/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.RateDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.DecimalFormat;

/**
 *
 * @author acer
 */
public class ProductReport extends HttpServlet {

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
            out.println("<title>Servlet ProductReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductReport at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        if (type != null) {
            request.setAttribute("type", type);
            request.getRequestDispatcher("prRp.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        String type = request.getParameter("type");
        if (type != null) {
            Date start = Date.valueOf(start_raw);
            Date end = Date.valueOf(end_raw);
            if (type.equals("TK")) {
                int cnt = 0;
                String yearS = "", monthS = "", dateS = "";
                String yearE = "", monthE = "", dateE = "";
                String t = start_raw;
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
                
                TicketDAO tkd = new TicketDAO();
                
                //numTick
                
                int numTick = tkd.getNumTickSellByDate(start, end);
                int numTickOfAllTime = tkd.getNumTickSellOfAllTime();
                String pcNumTick = decimalFormat.format((double)numTick / (double)numTickOfAllTime * 100);
                
                
                request.setAttribute("pcNumTick", pcNumTick);
                request.setAttribute("numTick", numTick);
                request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                request.getRequestDispatcher("tkRp.jsp").forward(request, response);
                
                
            } else if (type.equals("FD")) {
                
                
                int cnt = 0;
                String yearS = "", monthS = "", dateS = "";
                String yearE = "", monthE = "", dateE = "";
                String t = start_raw;
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
                request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
                request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
                request.getRequestDispatcher("fdRp.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("error.jsp").forward(request, response);
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
