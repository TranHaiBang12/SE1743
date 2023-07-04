/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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

/**
 *
 * @author acer
 */
public class IncomeReport extends HttpServlet {

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
            out.println("<title>Servlet IncomeReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet IncomeReport at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("icR.jsp").forward(request, response);
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
        TicketDAO tkd = new TicketDAO();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        FoodDAO fd = new FoodDAO();
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        Date start = null, end = null;
        try {
            start = Date.valueOf(start_raw);
            end = Date.valueOf(end_raw);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        if (start_raw == null || end_raw == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
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
            //food
            int numOnlF = fd.getIncomeOnl(start, end);
            int numOffF = fd.getIncomeOff(start, end);
            int numIAF = numOnlF + numOffF;

            String PCnumONLF = "0";
            String PCnumOFFF = "0";
            if (numIAF != 0) {
                PCnumONLF = decimalFormat.format((double) numOnlF / (double) numIAF * 100);
                PCnumOFFF = decimalFormat.format((double) numOffF / (double) numIAF * 100);
            }

            //TICKET
            int numOnlT = tkd.getOnlineIncome(start, end);
            int numOffT = tkd.getOfflineIncome(start, end);
            int numIAT = numOnlT + numOffT;

            String PCnumONLT = "0";
            String PCnumOFFT = "0";
            if (numIAT != 0) {
                PCnumONLT = decimalFormat.format((double) numOnlT / (double) numIAT * 100);
                PCnumOFFT = decimalFormat.format((double) numOffT / (double) numIAT * 100);
            }
            
            //all
            String PCTAll = "0";
            String PCFAll = "0";
            if ((numIAT + numIAF) != 0) {
                PCTAll = decimalFormat.format((double) numIAT / (double) (numIAT + numIAF) * 100);
                PCFAll = decimalFormat.format((double) numIAF / (double) (numIAT + numIAF) * 100);
            }
            
            request.setAttribute("PCTAll", PCTAll);
            request.setAttribute("PCFAll", PCFAll);
            
            request.setAttribute("PCnumONLT", PCnumONLT);
            request.setAttribute("PCnumOFFT", PCnumOFFT);

            request.setAttribute("numOnlT", numOnlT);
            request.setAttribute("numOffT", numOffT);
            request.setAttribute("numIAT", numIAT);

            request.setAttribute("PCnumONLF", PCnumONLF);
            request.setAttribute("PCnumOFFF", PCnumOFFF);

            request.setAttribute("numOnlF", numOnlF);
            request.setAttribute("numOffF", numOffF);
            request.setAttribute("numIAF", numIAF);

            request.setAttribute("start", dateS + "-" + monthS + "-" + yearS);
            request.setAttribute("end", dateE + "-" + monthE + "-" + yearE);
            request.setAttribute("check", 1);
            request.getRequestDispatcher("icR.jsp").forward(request, response);
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
