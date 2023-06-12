/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FoodDAO;
import dal.OrderDAO;
import dal.TicketDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;
import model.CartItemFood;
import model.CartItemTicket;

/**
 *
 * @author acer
 */
public class PaymentServlet extends HttpServlet {

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
            out.println("<title>Servlet PaymentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath() + "</h1>");
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

            for (int i = 0; i < cart.length(); i++) {
                if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                    if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                        list.add(new CartItemFood(fda.getFoodById(cart.substring(i + 1, i + 7)), Integer.parseInt(cart.substring(i + 8, i + 9))));
                    } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
                        System.out.println(cart.substring(i + 1, i + 7) + " " + Integer.parseInt(cart.substring(i + 9, i + 10)) + " " + cart.substring(i + 8, i + 9) + " " + cart.substring(i + 8, i + 10));
                        listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                    }

                }
            }
            request.setAttribute("listCart", list);
            request.setAttribute("listTicket", listT);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
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
        //processRequest(request, response);
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String email = request.getParameter("email");
        String sdt = request.getParameter("sdt");
        if(checkEmail(email) == false) {
            String ms = "Vui lòng nhập đúng email";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
        try {
            int n = Integer.parseInt(sdt);
            if(sdt.length() != 10) {
                throw new Exception("Loi moi");
            }
        } catch (Exception e) {
            String ms = "Vui lòng nhập đúng số điện thoại";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
        
        if(request.getParameter("pm") == null) {
            String ms = "Vui lòng chọn cách thanh toán";
            request.setAttribute("ms", ms);
            request.getRequestDispatcher("payment.jsp").forward(request, response);
        }
        Time t = Time.valueOf(Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes() + ":" + Calendar.getInstance().getTime().getSeconds());
        Date d = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());

        OrderDAO ord = new OrderDAO();
        String pm = "";
        if(request.getParameter("pm").equals("0")) {
            pm = "VNPay";
        }
        else if(request.getParameter("pm").equals("1")) {
            pm = "Payoo";
        }
        ord.insert(a.getUserName(), request.getParameter("fName"), request.getParameter("lName"), request.getParameter("sdt"), request.getParameter("email"), request.getParameter("cntry"), request.getParameter("strt"), request.getParameter("dist"), request.getParameter("city"), pm, d, t);
        
    }
    
    
    public boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()) {
            return true;
        }
        else {
            return false;
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
