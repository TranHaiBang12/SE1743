/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.CinemaDAO;
import dal.EmployeeDAO;
import dal.OrderDAO;
import dal.PointDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.AccountPoint;

/**
 *
 * @author acer
 */
public class MyAccountServlet extends HttpServlet {

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
            out.println("<title>Servlet MyAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyAccountServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        AccountDAO acd = new AccountDAO();
        OrderDAO ord = new OrderDAO();
        PointDAO pd = new PointDAO();
        if (request.getParameter("user") == null) {
            String date = "", month = "", year = "";
            int cnt = 0;
            int point = 0;
            if (a.getRole() == 2) {
                String t = acd.getAccountByUserName(a.getUserName()).getDob().toString();
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        year = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        month = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                date = t.substring(cnt + 1);
                AccountPoint ap = pd.getAccountPoint(a.getUserName());

                if (ap != null) {
                    point = ap.getPoint();
                } else {
                    point = 0;
                }
                String pass = acd.getAccountByUserName(a.getUserName()).getPassword();
                request.setAttribute("pass", pass);
                request.setAttribute("point", point);
                request.setAttribute("dob", date + "-" + month + "-" + year);
                request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
                request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(a.getUserName()));
                request.getRequestDispatcher("myaccount.jsp").forward(request, response);
            } else {
                String dateH = "", monthH = "", yearH = "";
                EmployeeDAO ed = new EmployeeDAO();
                String t = ed.getAccEmpByUserName(a.getUserName()).getDob().toString();
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        year = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        month = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                date = t.substring(cnt + 1);
                cnt = 0;
                t = ed.getAccEmpByUserName(a.getUserName()).getDob().toString();
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        yearH = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        monthH = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                dateH = t.substring(cnt + 1);
                AccountPoint ap = pd.getAccountPoint(a.getUserName());

                if (ap != null) {
                    point = ap.getPoint();
                } else {
                    point = 0;
                }
                CinemaDAO cnd = new CinemaDAO();
                 String pass = acd.getAccountByUserName(a.getUserName()).getPassword();
                request.setAttribute("pass", pass);
                request.setAttribute("cin", cnd.getCinemaByID(ed.getAccEmpByUserName(a.getUserName()).getCinID()));
                request.setAttribute("mng", ed.getEmployeeByID(ed.getAccEmpByUserName(a.getUserName()).getManagerID()));
                request.setAttribute("point", point);
                request.setAttribute("dob", date + "-" + month + "-" + year);
                request.setAttribute("accE", ed.getAccEmpByUserName(a.getUserName()));
                request.setAttribute("hiredDate", dateH + "-" + monthH + "-" + yearH);
                request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(a.getUserName()));
                request.getRequestDispatcher("myaccount.jsp").forward(request, response);
            }
        }
        else {
            a = acd.getAccountByUserName(request.getParameter("user"));
            String date = "", month = "", year = "";
            int cnt = 0;
            int point = 0;
            if (a.getRole() == 2) {
                String t = acd.getAccountByUserName(a.getUserName()).getDob().toString();
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        year = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        month = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                date = t.substring(cnt + 1);
                AccountPoint ap = pd.getAccountPoint(a.getUserName());

                if (ap != null) {
                    point = ap.getPoint();
                } else {
                    point = 0;
                }
                request.setAttribute("point", point);
                request.setAttribute("dob", date + "-" + month + "-" + year);
                request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
                request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(a.getUserName()));
                request.getRequestDispatcher("myaccount.jsp").forward(request, response);
            } else {
                String dateH = "", monthH = "", yearH = "";
                EmployeeDAO ed = new EmployeeDAO();
                String t = ed.getAccEmpByUserName(request.getParameter("user")).getDob().toString();
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        year = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        month = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                date = t.substring(cnt + 1);
                cnt = 0;
                t = ed.getAccEmpByUserName(request.getParameter("user")).getDob().toString();
                for (int i = 0; i < t.length(); i++) {
                    if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                        yearH = t.substring(cnt, i);
                        cnt = i;
                    } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                        monthH = t.substring(cnt + 1, i);
                        cnt = i;
                    }
                }
                dateH = t.substring(cnt + 1);
                AccountPoint ap = pd.getAccountPoint(request.getParameter("user"));

                if (ap != null) {
                    point = ap.getPoint();
                } else {
                    point = 0;
                }
                CinemaDAO cnd = new CinemaDAO();
                String pass = acd.getAccountByUserName(request.getParameter("user")).getPassword();
                request.setAttribute("pass", pass);
                request.setAttribute("cin", cnd.getCinemaByID(ed.getAccEmpByUserName(request.getParameter("user")).getCinID()));
                request.setAttribute("mng", ed.getEmployeeByID(ed.getAccEmpByUserName(request.getParameter("user")).getManagerID()));
                request.setAttribute("point", point);
                request.setAttribute("dob", date + "-" + month + "-" + year);
                request.setAttribute("accE", ed.getAccEmpByUserName(request.getParameter("user")));
                request.setAttribute("hiredDate", dateH + "-" + monthH + "-" + yearH);
                request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(request.getParameter("user")));
                request.getRequestDispatcher("myaccount.jsp").forward(request, response);
            }
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
