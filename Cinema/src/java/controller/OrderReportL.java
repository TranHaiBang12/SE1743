/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.OrderOff;
import model.OrderOffByDate;
import model.OrderOnl;
import model.OrderOnlByDate;

/**
 *
 * @author acer
 */
public class OrderReportL extends HttpServlet {

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
            out.println("<title>Servlet OrderReportL</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderReportL at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("rRpD.jsp").forward(request, response);
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
            System.out.println("4");
            OrderDAO ord = new OrderDAO();
            List<OrderOnl> list = ord.getAllOrderOnlByDate(start, end);
            List<OrderOff> listOff = ord.getAllOrderOffByDate(start, end);
            List<Date> dte = new ArrayList<>();
            List<Date> dte2 = new ArrayList<>();
            dte.add(list.get(0).getPaymentDate());
            dte2.add(listOff.get(0).getPaymentDate());
            int k = 0;
            for (int i = 1; i < list.size(); i++) {
                if (!list.get(i).getPaymentDate().toString().equals(dte.get(k).toString())) {
                    k++;
                    dte.add(list.get(i).getPaymentDate());
                }
            }
            Date swap;
            for (int i = 0; i < dte.size() - 1; i++) {
                for (int j = i + 1; j < dte.size(); j++) {
                    if (dte.get(i).compareTo(dte.get(j)) >= 0) {
                        swap = dte.get(i);
                        dte.set(i, dte.get(j));
                        dte.set(j, swap);
                    }
                }

            }
            List<OrderOnlByDate> listOBD = new ArrayList<>();
            for (int i = 0; i < dte.size(); i++) {
                listOBD.add(ord.getAllOrderOnlByUserNameAPDate(null, dte.get(i)));
            }
            k = 0;
            
            for (int i = 1; i < listOff.size(); i++) {
                if (!listOff.get(i).getPaymentDate().toString().equals(dte2.get(k).toString())) {
                    k = i;
                    System.out.println("2");
                    dte2.add(listOff.get(i).getPaymentDate());
                }
            }
            for (int i = 0; i < dte2.size() - 1; i++) {
                for (int j = i + 1; j < dte2.size(); j++) {
                    if (dte2.get(i).compareTo(dte2.get(j)) >= 0) {
                        swap = dte2.get(i);
                        dte2.set(i, dte2.get(j));
                        dte2.set(j, swap);
                    }
                }

            }
            List<OrderOffByDate> listOFBD = new ArrayList<>();
            for (int i = 0; i < dte2.size(); i++) {
                listOFBD.add(ord.getAllOrderOffByUserNameAPDate(null, dte2.get(i)));
            }

        
            request.setAttribute("check", 1);
            request.setAttribute("listOBD", listOBD);
            request.setAttribute("listOFBD", listOFBD);
            request.getRequestDispatcher("rRpD.jsp").forward(request, response);
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
