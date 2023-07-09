/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.CinemaDAO;
import dal.EventDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderTicketDetailDAO;
import dal.TransactionCDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.LocationCinMD;
import model.OrderOff;
import model.OrderOffByDate;
import model.OrderOnl;
import model.OrderOnlByDate;

/**
 *
 * @author acer
 */
public class TransactionServlet extends HttpServlet {

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
            out.println("<title>Servlet TransactionServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TransactionServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        OrderDAO ord = new OrderDAO();
        OrderDetailDAO odd = new OrderDetailDAO();
        OrderTicketDetailDAO otd = new OrderTicketDetailDAO();
        TransactionCDAO tcd = new TransactionCDAO();

        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        if (request.getParameter("acc") == null) {
            List<OrderOnlByDate> listOBD = new ArrayList<>();
            List<OrderOffByDate> listOFBD = new ArrayList<>();
            int k = 0;
            Date swap;
            List<OrderOnl> list = ord.getAllOrderOnlByUserName(a.getUserName());
            List<OrderOff> listOff = ord.getAllOrderOffByUserName(a.getUserName());
            List<Date> dte = new ArrayList<>();
            List<Date> dte2 = new ArrayList<>();
            if (!list.isEmpty()) {
                dte.add(list.get(0).getPaymentDate());

                for (int i = 1; i < list.size(); i++) {
                    if (!list.get(i).getPaymentDate().toString().equals(dte.get(k).toString())) {
                        System.out.println(k + " " + i);
                        k++;
                        dte.add(list.get(i).getPaymentDate());
                        System.out.println(k + " " + i);
                    }
                }

                for (int i = 0; i < dte.size() - 1; i++) {
                    for (int j = i + 1; j < dte.size(); j++) {
                        if (dte.get(i).compareTo(dte.get(j)) >= 0) {
                            swap = dte.get(i);
                            dte.set(i, dte.get(j));
                            dte.set(j, swap);
                        }
                    }

                }

                for (int i = 0; i < dte.size(); i++) {
                    listOBD.add(ord.getAllOrderOnlByUserNameAPDate(a.getUserName(), dte.get(i)));
                }
            }
            if (!listOff.isEmpty()) {
                dte2.add(listOff.get(0).getPaymentDate());

                for (int i = 1; i < listOff.size(); i++) {
                    if (!listOff.get(i).getPaymentDate().toString().equals(dte2.get(k).toString())) {
                        k = i;
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

                for (int i = 0; i < dte2.size(); i++) {
                    listOFBD.add(ord.getAllOrderOffByUserNameAPDate(a.getUserName(), dte2.get(i)));
                }
            }
            EventDAO evd = new EventDAO();
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            if (!listOBD.isEmpty()) {
                for (int i = 0; i < listOBD.size(); i++) {
                    for (int j = 0; j < listOBD.get(i).getO().size(); j++) {
                        if (evd.getAllOrderEvent(listOBD.get(i).getO().get(j).getOrderID()) != null) {
                            List<model.Event> e = evd.getAllOrderEvent(listOBD.get(i).getO().get(j).getOrderID());
                            for (int l = 0; l < e.size(); l++) {
                                if (evd.getEventByCode(e.get(l).getEventCode()).getEventType() == 1) {
                                    if (evd.getEventDiscountO(e.get(l).getEventCode()) != null) {
                                        if (evd.getEventDiscountO(e.get(l).getEventCode()).getType().equals("FD")) {
                                            double discount = evd.getEventDiscountO(e.get(l).getEventCode()).getDiscount();
                                            String pc = decimalFormat.format(discount);
                                            discount = Double.parseDouble(pc);
                                            double priceT = otd.getPriceByOrderID(listOBD.get(i).getO().get(j).getOrderID());
                                            double priceF = odd.getPriceByOrderID(listOBD.get(i).getO().get(j).getOrderID());
                                            double price = priceT + priceF - priceF * discount;
                                            pc = decimalFormat.format(price);
                                            price = Double.parseDouble(pc);
                                            listOBD.get(i).getO().get(j).setTotalAmount(price);
                                        } else if (evd.getEventDiscountO(e.get(l).getEventCode()).getType().equals("TK")) {
                                            double discount = evd.getEventDiscountO(e.get(l).getEventCode()).getDiscount();
                                            String pc = decimalFormat.format(discount);
                                            discount = Double.parseDouble(pc);
                                            double priceT = otd.getPriceByOrderID(listOBD.get(i).getO().get(j).getOrderID());
                                            double priceF = odd.getPriceByOrderID(listOBD.get(i).getO().get(j).getOrderID());
                                            double price = priceT - priceT * discount + priceF;
                                            pc = decimalFormat.format(price);
                                            price = Double.parseDouble(pc);
                                            listOBD.get(i).getO().get(j).setTotalAmount(price);
                                        }
                                    } else if (evd.getEventDiscountM(e.get(l).getEventCode()) != null) {
                                        double discount = evd.getEventDiscountM(e.get(l).getEventCode()).getDiscount();
                                        String pc = decimalFormat.format(discount);
                                        discount = Double.parseDouble(pc);
                                        double priceT = otd.getPriceByOrderID(listOBD.get(i).getO().get(j).getOrderID());
                                        double priceF = odd.getPriceByOrderID(listOBD.get(i).getO().get(j).getOrderID());
                                        double price = priceT - priceT * discount + priceF;
                                        pc = decimalFormat.format(price);
                                        price = Double.parseDouble(pc);
                                        listOBD.get(i).getO().get(j).setTotalAmount(price);
                                    }
                                }
                            }
                        }
                    }

                }

                request.setAttribute("listOBD", listOBD);
            } else {
                request.setAttribute("ms", "Không có hóa đơn nào");
            }
            if (listOFBD.isEmpty()) {
                request.setAttribute("msOFF", "Không có hóa đơn nào");
            } else {
                request.setAttribute("listOFBD", listOFBD);
            }
            request.getRequestDispatcher("transact.jsp").forward(request, response);
        } else {
            AccountDAO acd = new AccountDAO();
            a = acd.getAccountByUserName(request.getParameter("acc"));
            List<OrderOnl> list = ord.getAllOrderOnlByUserName(a.getUserName());
            List<OrderOff> listOff = ord.getAllOrderOffByUserName(a.getUserName());
            List<Date> dte = new ArrayList<>();
            List<Date> dte2 = new ArrayList<>();
            dte.add(list.get(0).getPaymentDate());
            dte2.add(listOff.get(0).getPaymentDate());
            int k = 0;
            for (int i = 1; i < list.size(); i++) {
                if (!list.get(i).getPaymentDate().toString().equals(dte.get(k).toString())) {
                    System.out.println(k + " " + i);
                    k++;
                    dte.add(list.get(i).getPaymentDate());
                    System.out.println(k + " " + i);
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
                listOBD.add(ord.getAllOrderOnlByUserNameAPDate(a.getUserName(), dte.get(i)));
            }

            for (int i = 1; i < listOff.size(); i++) {
                if (!listOff.get(i).getPaymentDate().toString().equals(dte2.get(k).toString())) {
                    k = i;
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
                listOFBD.add(ord.getAllOrderOffByUserNameAPDate(a.getUserName(), dte2.get(i)));
            }
            if (listOBD.isEmpty()) {
                System.out.println("4");
                request.setAttribute("ms", "Không có hóa đơn nào");
            } else {
                request.setAttribute("listOBD", listOBD);
            }
            if (listOFBD.isEmpty()) {
                System.out.println("3");
                request.setAttribute("msOFF", "Không có hóa đơn nào");
            } else {
                request.setAttribute("listOFBD", listOFBD);
            }
            request.getRequestDispatcher("transact.jsp").forward(request, response);
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
