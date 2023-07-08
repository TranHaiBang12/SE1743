/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EventDAO;
import dal.FoodDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.OrderTicketDetailDAO;
import dal.PointDAO;
import dal.TransactionCDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Food;
import model.OrderOnl;
import model.OrderDetail;
import model.OrderOff;
import model.OrderTicketDetail;
import model.TransactionCode;

/**
 *
 * @author acer
 */
public class In4ODServlet extends HttpServlet {

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
            out.println("<title>Servlet In4ODServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet In4ODServlet at " + request.getContextPath() + "</h1>");
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String orderID = request.getParameter("id");
        if (orderID.contains("ONL")) {
            OrderDAO ord = new OrderDAO();
            List<OrderOnl> listO = ord.getAllIn4OrderByID(orderID);
            OrderDetailDAO odd = new OrderDetailDAO();
            List<OrderDetail> listOD = odd.getAllProductInOrderByOrderID(orderID);
            if (listOD.isEmpty()) {
                String ms1 = "Hóa đơn này không có đồ ăn";
                //request.getRequestDispatcher("")
                request.setAttribute("ms1", ms1);
            }
            OrderTicketDetailDAO otd = new OrderTicketDetailDAO();
            FoodDAO fd = new FoodDAO();
            List<Food> f = new ArrayList<>();
            EventDAO evd = new EventDAO();
            double price = 0;
            if (evd.getAllOrderEvent(orderID) != null) {
                List<model.Event> e = evd.getAllOrderEvent(orderID);
                for (int l = 0; l < e.size(); l++) {
                    if (evd.getEventByCode(e.get(l).getEventCode()).getEventType() == 1) {
                        if (evd.getEventDiscountO(e.get(l).getEventCode()) != null) {
                            if (evd.getEventDiscountO(e.get(l).getEventCode()).getType().equals("FD")) {
                                double discount = evd.getEventDiscountO(e.get(l).getEventCode()).getDiscount();
                                String pc = decimalFormat.format(discount);
                                if (discount != 0) {
                                    request.setAttribute("pcF", pc);
                                    System.out.println("5");
                                }
                                discount = Double.parseDouble(pc);
                                double priceT = otd.getPriceByOrderID(orderID);
                                double priceF = odd.getPriceByOrderID(orderID);
                                price = priceT + priceF - priceF * discount;
                                pc = decimalFormat.format(price);
                                price = Double.parseDouble(pc);
                            } else if (evd.getEventDiscountO(e.get(l).getEventCode()).getType().equals("TK")) {
                                double discount = evd.getEventDiscountO(e.get(l).getEventCode()).getDiscount();
                                String pc = decimalFormat.format(discount);
                                if (discount != 0) {
                                    request.setAttribute("pcT", pc);
                                    System.out.println("5");
                                }
                                discount = Double.parseDouble(pc);
                                double priceT = otd.getPriceByOrderID(orderID);
                                double priceF = odd.getPriceByOrderID(orderID);
                                price = priceT - priceT * discount + priceF;
                                pc = decimalFormat.format(price);
                                price = Double.parseDouble(pc);
                            }
                        } else if (evd.getEventDiscountM(e.get(l).getEventCode()) != null) {
                            double discount = evd.getEventDiscountM(e.get(l).getEventCode()).getDiscount();
                            String pc = decimalFormat.format(discount);
                            if (discount != 0) {
                                request.setAttribute("pcT", pc);
                                System.out.println("5");
                            }
                            discount = Double.parseDouble(pc);
                            double priceT = otd.getPriceByOrderID(orderID);
                            double priceF = odd.getPriceByOrderID(orderID);
                            price = priceT - priceT * discount + priceF;
                            pc = decimalFormat.format(price);
                            price = Double.parseDouble(pc);
                        }
                    } else if (evd.getEventByCode(e.get(l).getEventCode()).getEventType() == 2) {
                        List<String> pr = evd.getAllProductInEvent(e.get(l).getEventCode()).getProduct();
                        for (int j = 0; j < pr.size(); j++) {
                            f.add(fd.getFoodById(pr.get(j)));
                        }

                    }
                }
            }

            List<model.Event> ev = new ArrayList<>();
            List<model.Event> e = evd.getAllOrderEvent(orderID);
            for (int i = 0; i < e.size(); i++) {
                ev.add(evd.getEventByCode(e.get(i).getEventCode()));
            }

            List<OrderTicketDetail> listOTD = otd.getTkByOrderID(orderID);
            if (listOTD.isEmpty()) {
                String ms2 = "Hóa đơn này không có vé";
                //request.getRequestDispatcher("")
                request.setAttribute("ms2", ms2);
            }
            for (int i = 0; i < listOTD.size(); i++) {
                if (listOTD.get(i).getType().equals("NM")) {
                    listOTD.get(i).setType("Thường");
                } else if (listOTD.get(i).getType().equals("VP")) {
                    listOTD.get(i).setType("VIP");
                } else if (listOTD.get(i).getType().equals("VT")) {
                    listOTD.get(i).setType("Đôi");
                }
            }
            TransactionCDAO tcd = new TransactionCDAO();
            List<TransactionCode> listTCF = tcd.getAllCodeFByOrderID(orderID);
            List<TransactionCode> listTCT = tcd.getAllCodeTByOrderID(orderID);
            PointDAO pd = new PointDAO();
            if (!ev.isEmpty()) {
                request.setAttribute("ev", ev);
            } else {
                request.setAttribute("ms", "Không có ưu đãi nào");
            }
            request.setAttribute("f", f);
            request.setAttribute("tk", ord.getOrderOnlByID(orderID));
            request.setAttribute("point", pd.getPointByOrderID(orderID));
            request.setAttribute("pointAchieve", pd.getPointAchieveByOrderID(orderID));
            request.setAttribute("type", "onl");
            request.setAttribute("orderID", orderID);
            request.setAttribute("listO", listO);
            request.setAttribute("listOD", listOD);
            request.setAttribute("listOTD", listOTD);
            request.setAttribute("listTCF", listTCF);
            request.setAttribute("listTCT", listTCT);
            request.getRequestDispatcher("in4OD.jsp").forward(request, response);
        } else {
            OrderDAO ord = new OrderDAO();

            List<OrderOff> listO = ord.getAllIn4OrderOffByID(orderID);
            OrderDetailDAO odd = new OrderDetailDAO();
            List<OrderDetail> listOD = odd.getAllProductInOrderOffByOrderID(orderID);
            if (listOD.isEmpty()) {
                String ms1 = "Hóa đơn này không có đồ ăn";
                //request.getRequestDispatcher("")
                request.setAttribute("ms1", ms1);
            }
            OrderTicketDetailDAO otd = new OrderTicketDetailDAO();

            List<OrderTicketDetail> listOTD = otd.getTkOffByOrderID(orderID);
            if (listOTD.isEmpty()) {
                String ms2 = "Hóa đơn này không có vé";
                //request.getRequestDispatcher("")
                request.setAttribute("ms2", ms2);
            }
            for (int i = 0; i < listOTD.size(); i++) {
                if (listOTD.get(i).getType().equals("NM")) {
                    listOTD.get(i).setType("Thường");
                } else if (listOTD.get(i).getType().equals("VP")) {
                    listOTD.get(i).setType("VIP");
                } else if (listOTD.get(i).getType().equals("VT")) {
                    listOTD.get(i).setType("Đôi");
                }
            }
            PointDAO pd = new PointDAO();
            System.out.println(pd.getPointByOrderID(orderID));
            System.out.println("1");
            request.setAttribute("point", pd.getPointByOrderID(orderID));
            request.setAttribute("tk", ord.getOrderOffByID(orderID));
            request.setAttribute("pointAchieve", pd.getPointAchieveByOrderID(orderID));
            request.setAttribute("orderID", orderID);
            request.setAttribute("listO", listO);
            request.setAttribute("listOD", listOD);
            request.setAttribute("listOTD", listOTD);

            request.getRequestDispatcher("in4OD.jsp").forward(request, response);
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
