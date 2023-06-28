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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.CartItemFood;
import model.CartItemTicket;
import model.Ticket;

/**
 *
 * @author acer
 */
public class Cart extends HttpServlet {

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
            out.println("<title>Servlet Cart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cart at " + request.getContextPath() + "</h1>");
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
        Cookie[] arr = request.getCookies();
        List<CartItemFood> list = new ArrayList<>();
        List<CartItemTicket> listT = new ArrayList<>();
        FoodDAO fda = new FoodDAO();
        TicketDAO tkd = new TicketDAO();
        String cart = "";
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a != null) {
            for (Cookie i : arr) {
                if (i.getName().equals(a.getUserName())) {
                    cart = i.getValue();
                    break;
                }
            }

            FoodDAO fd = new FoodDAO();
            List<String> listHH = fd.getAllFoodOff();
            List<Ticket> tkB = tkd.getAllTicketBought();

            int cntF = 0, cntT = 0;

            for (int i = 0; i < cart.length(); i++) {
                if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                    if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                        for (int j = 0; j < listHH.size(); j++) {
                            if (cart.substring(i + 1, i + 7).equals(listHH.get(j))) {
                                cntF++;
                            }
                        }
                        if (cntF == 0) {
                            list.add(new CartItemFood(fda.getFoodById(cart.substring(i + 1, i + 7)), Integer.parseInt(cart.substring(i + 8, i + 9))));
                        }
                    } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
                        for (int j = 0; j < tkB.size(); j++) {
                            if (cart.substring(i + 1, i + 7).equals(tkB.get(j).getProductCode()) && Integer.parseInt(cart.substring(i + 9, i + 10)) == tkB.get(j).getRow() && cart.substring(i + 8, i + 9).equals(tkB.get(j).getCol())) {
                                cntT++;
                            }
                        }
                        if (cntT == 0) {
                            listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                        }
                    }

                }
            }

            int totalQuantity = 0;
            for (int i = 0; i < list.size(); i++) {
                totalQuantity += list.get(i).getQuantity();
            }
            for (int i = 0; i < listT.size(); i++) {
                totalQuantity += 1;
            }
            int totalAmount = 0;
            for (int i = 0; i < list.size(); i++) {
                totalAmount += (list.get(i).getQuantity() * (list.get(i).getFood().getPrice()));
            }
            for (int i = 0; i < listT.size(); i++) {
                totalAmount += (listT.get(i).getTicket().getPrice());
            }
            request.setAttribute("listCart", list);
            request.setAttribute("listTicket", listT);
            request.setAttribute("totalQuantity", totalQuantity);
            request.setAttribute("totalAmount", totalAmount);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            String ms = "Không có vật phẩm nào trong giỏ hàng của bạn";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("cart.jsp").forward(request, response);
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
