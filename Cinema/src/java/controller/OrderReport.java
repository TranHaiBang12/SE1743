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
public class OrderReport extends HttpServlet {

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
            out.println("<title>Servlet OrderReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderReport at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("p") == null) {
            if (request.getParameter("t") == null) {
                String id_raw = "", date_raw = "";
                if (request.getParameter("id") != null) {
                    id_raw = request.getParameter("id");
                }
                if (request.getParameter("date") != null) {
                    date_raw = request.getParameter("date");
                }
                int id = 0;
                try {
                    id = Integer.parseInt(id_raw);
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                String p = "", t;
                int cnt = 0;
                for (int j = 0; j < date_raw.length(); j++) {

                    if (date_raw.charAt(j) == '-' && cnt == 0) {
                        p += date_raw.substring(0, j);
                        cnt = j;
                    } else if (date_raw.charAt(j) == '-' && cnt != 0) {
                        t = p;
                        p = date_raw.substring(cnt, j + 1);
                        p += t;
                        cnt = j;
                        break;
                    }
                }
                t = p;
                p = date_raw.substring(cnt + 1);
                p += t;
                Date date = Date.valueOf(p);
                OrderDAO ord = new OrderDAO();

                OrderOnlByDate onl = ord.getAllOrderTicketOnlByIDAD(id, date);
                OrderOffByDate off = ord.getAllOrderTicketOffByIDAD(id, date);

                List<OrderOnlByDate> listOBD = new ArrayList<>();
                listOBD.add(onl);
                List<OrderOffByDate> listOFBD = new ArrayList<>();
                listOFBD.add(off);
                if (onl.getO().isEmpty()) {
                    request.setAttribute("msONL", "Không có sản phẩm nào được mua thông qua website vào ngày này");
                } else {
                    request.setAttribute("listOBD", listOBD);
                }
                if (off.getOf().isEmpty()) {
                    System.out.println("2");
                    request.setAttribute("msOFF", "Không có sản phẩm nào được mua trực tiếp vào ngày này");
                } else {
                    request.setAttribute("listOFBD", listOFBD);
                }

                request.setAttribute("date", date);
                request.getRequestDispatcher("ordr.jsp").forward(request, response);
            } else {
                String id_raw = "", date_raw = "";
                if (request.getParameter("id") != null) {
                    id_raw = request.getParameter("id");
                }
                if (request.getParameter("date") != null) {
                    date_raw = request.getParameter("date");
                }
                int id = 0;
                try {
                    id = Integer.parseInt(id_raw);
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                String p = "", t;
                int cnt = 0;
                for (int j = 0; j < date_raw.length(); j++) {

                    if (date_raw.charAt(j) == '-' && cnt == 0) {
                        p += date_raw.substring(0, j);
                        cnt = j;
                    } else if (date_raw.charAt(j) == '-' && cnt != 0) {
                        t = p;
                        p = date_raw.substring(cnt, j + 1);
                        p += t;
                        cnt = j;
                        break;
                    }
                }
                t = p;
                p = date_raw.substring(cnt + 1);
                p += t;
                Date date = Date.valueOf(p);
                OrderDAO ord = new OrderDAO();

                OrderOnlByDate onl = ord.getAllOrderFoodOnlByIDAD(date);
                OrderOffByDate off = ord.getAllOrderFoodOffByIDAD(date);

                List<OrderOnlByDate> listOBD = new ArrayList<>();
                listOBD.add(onl);
                List<OrderOffByDate> listOFBD = new ArrayList<>();
                listOFBD.add(off);
                if (onl.getO().isEmpty()) {
                    request.setAttribute("msONL", "Không có sản phẩm nào được mua thông qua website vào ngày này");
                } else {
                    request.setAttribute("listOBD", listOBD);
                }
                if (off.getOf().isEmpty()) {
                    System.out.println("2");
                    request.setAttribute("msOFF", "Không có sản phẩm nào được mua trực tiếp vào ngày này");
                } else {
                    request.setAttribute("listOFBD", listOFBD);
                }

                request.setAttribute("date", date);
                request.getRequestDispatcher("ordr.jsp").forward(request, response);
            }
        } else {
            OrderDAO ord = new OrderDAO();
            List<OrderOnl> list = ord.getAllOrderOnlByUserName(null);
            List<OrderOff> listOff = ord.getAllOrderOffByUserName(null);
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

            String page_rawONL = request.getParameter("pageONL");
            int pageONL = 0;

            if (page_rawONL == null) {
                pageONL = 1;
            } else {
                try {
                    pageONL = Integer.parseInt(page_rawONL);
                    if (pageONL <= 0) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }

            int numPerPageONL = 20;
            int totalPageONL = (listOBD.size() % numPerPageONL == 0) ? (listOBD.size() / numPerPageONL) : (listOBD.size() / numPerPageONL + 1);
            if (pageONL > totalPageONL && !listOBD.isEmpty()) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }

            int start = (pageONL - 1) * numPerPageONL;
            int end = (pageONL * numPerPageONL > listOBD.size()) ? (listOBD.size() - 1) : (pageONL * numPerPageONL - 1);
            
            request.setAttribute("page", pageONL);
            request.setAttribute("totalPage", totalPageONL);
            request.setAttribute("listOBD", listOBD);
            request.setAttribute("listOFBD", listOFBD);
            request.getRequestDispatcher("ordr.jsp").forward(request, response);
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
        processRequest(request, response);
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
