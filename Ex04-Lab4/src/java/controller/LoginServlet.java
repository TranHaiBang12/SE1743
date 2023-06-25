/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author acer
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
        String u = request.getParameter("user");
        String p = request.getParameter("pass");
        String r = request.getParameter("rm");

        AccountDAO acd = new AccountDAO();
        Account a = acd.checkA(u, p);
        if (a != null) {
            Cookie user = new Cookie("user", u);
            Cookie pass = new Cookie("pass", p);
            Cookie rm = new Cookie("rm", "");
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            if (r != null && r.equals("1")) {
                rm.setValue(r);
                user.setMaxAge(60 * 60 * 24 * 7);
                pass.setMaxAge(60 * 60 * 24 * 7);
                rm.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(user);
                response.addCookie(pass);
                response.addCookie(rm);

            } else {
                Cookie cookie = new Cookie("rm", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                cookie = new Cookie("user", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                cookie = new Cookie("pass", "");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            System.out.println(1 + " " + user.getValue() + " " + rm.getValue() + " " + pass.getValue());
            request.setAttribute("ms", "ms");
            request.getRequestDispatcher("Detail.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.removeAttribute("account");
            response.sendRedirect("login.jsp");
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
