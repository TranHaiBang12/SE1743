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
import java.text.DecimalFormat;
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
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
            int p = 0;
            int k = 0;
            int cnt = 0;
            String code = "";
            int quantity = 0;
            double price = 0;
            double discount = 0;
            for (int i = 0; i < cart.length(); i++) {
                if (cart.charAt(i) == '/' && i != cart.length() - 1) {
                    cntT = 0;
                    cntF = 0;
                    if (cart.charAt(i + 1) == 'F' && cart.charAt(i + 2) == 'D') {
                        for (int j = 0; j < listHH.size(); j++) {
                            if (cart.substring(i + 1, i + 7).equals(listHH.get(j))) {
                                cntF++;
                                p++;
                                cart = cart.substring(0, i + 1) + cart.substring(i + 7);
                            }
                        }
                        if (cntF == 0) {
                            for (int j = i; j < cart.length(); j++) {
                                if (cart.charAt(j) == 'p' && k == 0) {
                                    code = cart.substring(i + 1, j);
                                    cnt = j;
                                    k++;
                                } else if (cart.charAt(j) == 'p' && k == 1) {
                                    quantity = Integer.parseInt(cart.substring(cnt + 1, j));
                                    cnt = j;
                                    k++;
                                } else if (cart.charAt(j) == 'p' && k == 2) {
                                    price = Double.parseDouble(cart.substring(cnt + 1, j));
                                    cnt = j;
                                    k++;
                                } else if ((cart.charAt(j) == '/' && k == 3) || j == cart.length() - 1&& k == 3) {
                                    if (cart.charAt(j) == '/' && k == 3) {
                                        discount = Double.parseDouble(cart.substring(cnt + 1, j));
                                    } else if (j == cart.length() - 1&& k == 3) {
                                        discount = Double.parseDouble(cart.substring(cnt + 1));
                                    }
                                    cnt = j;
                                    k++;
                                    break;
                                }
                            }
                            k = 0;
                            cnt = 0;
                            System.out.println(code + " " + quantity + " " + discount + " " + price);
                            list.add(new CartItemFood(fda.getFoodById(code), quantity, discount, price));
                        }
                    } else if (cart.charAt(i + 1) == 'T' && cart.charAt(i + 2) == 'K') {
                        for (int j = 0; j < tkB.size(); j++) {
                            if (cart.substring(i + 1, i + 7).equals(tkB.get(j).getProductCode()) && Integer.parseInt(cart.substring(i + 9, i + 10)) == tkB.get(j).getRow() && cart.substring(i + 8, i + 9).equals(tkB.get(j).getCol())) {
                                cntT++;
                                p++;
                                cart = cart.substring(0, i + 1) + cart.substring(i + 7);
                            }
                        }
                        if (cntT == 0) {
                            for (int j = i; j < cart.length(); j++) {
                                if (cart.charAt(j) == 'p' && k == 0) {
                                    code = cart.substring(i + 1, j);
                                    cnt = j;
                                    k++;
                                } else if (cart.charAt(j) == 'p' && k == 1) {
                                    quantity = 1;
                                    cnt = j;
                                    k++;
                                } else if (cart.charAt(j) == 'd' && k == 2) {
                                    price = Double.parseDouble(cart.substring(cnt + 1, j));
                                    cnt = j;
                                    k++;
                                } else if ((cart.charAt(j) == '/' && k == 3) || j == cart.length() - 1 && k == 3) {
                                    if (cart.charAt(j) == '/' && k == 3) {
                                        discount = Double.parseDouble(cart.substring(cnt + 1, j));
                                    }
                                    else if(j == cart.length() - 1 && k == 3) {
                                        discount = Double.parseDouble(cart.substring(cnt + 1));
                                    }
                                    cnt = j;
                                    k++;
                                    break;
                                }
                            }
                            k = 0;
                            cnt = 0;
                            listT.add(new CartItemTicket(tkd.getTicketPByProductCodeRC(cart.substring(i + 1, i + 7), Integer.parseInt(cart.substring(i + 9, i + 10)), cart.substring(i + 8, i + 9)), cart.substring(i + 8, i + 10)));
                        }
                    }

                }
            }
            

            int totalQuantity = 0;
            System.out.println(list.size() + " " + listT.size());
            for (int i = 0; i < list.size(); i++) {
                totalQuantity += list.get(i).getQuantity();
            }
            for (int i = 0; i < listT.size(); i++) {
                totalQuantity += 1;
            }
            double totalAmount = 0;
            
            for (int i = 0; i < list.size(); i++) {
                list.get(i).getFood().setPriceNS(decimalFormat.format((list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount())));
                list.get(i).getFood().setPriceOS(decimalFormat.format(list.get(i).getFood().getPrice()));
            }
            
            for (int i = 0; i < listT.size(); i++) {
                listT.get(i).getTicket().setPriceNS(decimalFormat.format((listT.get(i).getTicket().getPrice() - listT.get(i).getTicket().getPrice() * listT.get(i).getTicket().getDiscount())));
                listT.get(i).getTicket().setPriceOS(decimalFormat.format(listT.get(i).getTicket().getPrice()));
            }

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getFood() != null) {
                    System.out.println((list.get(i).getQuantity() * (list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount())));
                    totalAmount += Double.parseDouble(decimalFormat.format(list.get(i).getQuantity() * (list.get(i).getFood().getPrice() - list.get(i).getFood().getPrice() * list.get(i).getFood().getDiscount())));
                }
            }
            for (int i = 0; i < listT.size(); i++) {
                if (listT.get(i).getTicket() != null) {
                    totalAmount += Double.parseDouble(decimalFormat.format(listT.get(i).getTicket().getPrice() - listT.get(i).getTicket().getPrice() * listT.get(i).getTicket().getDiscount()));
                }
            }
            if (p > 0) {
                request.setAttribute("ms", "Có sản phẩm trong giỏ hàng của bạn đã hết hàng. Vui lòng thanh toán lại");
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            } else {
                request.setAttribute("listCart", list);
                request.setAttribute("listTicket", listT);
                request.setAttribute("totalQuantity", totalQuantity);
                request.setAttribute("totalAmount", decimalFormat.format(totalAmount));
                request.getRequestDispatcher("cart.jsp").forward(request, response);
            }
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
