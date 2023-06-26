/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.AccountPoint;
import model.Employee;

/**
 *
 * @author acer
 */
public class EmpDetail extends HttpServlet {

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
            out.println("<title>Servlet EmpDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmpDetail at " + request.getContextPath() + "</h1>");
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
        String id_raw = request.getParameter("id");
        EmployeeDAO ed = new EmployeeDAO();
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        Employee e = ed.empD(id);
        String date = "", month = "", year = "";
        String dateH = "", monthH = "", yearH = "";
        int cnt = 0;
        String t = ed.empD(id).getDob().toString();
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
        PointDAO pd = new PointDAO();
        t = ed.empD(id).getHiredDate().toString();
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
        int point;
        AccountPoint ap = pd.getAccountPoint(ed.empD(id).getUsername());

        if (ap != null) {
            point = ap.getPoint();
        } else {
            point = 0;
        }
        OrderDAO ord = new OrderDAO();
        CinemaDAO cnd = new CinemaDAO();
        request.setAttribute("cin", cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()));
        request.setAttribute("mng", ed.getEmployeeByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getManagerID()));
        request.setAttribute("point", point);
        request.setAttribute("dob", date + "-" + month + "-" + year);
        request.setAttribute("accE", ed.getAccEmpByUserName(ed.empD(id).getUsername()));
        request.setAttribute("hiredDate", dateH + "-" + monthH + "-" + yearH);
        request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(ed.empD(id).getUsername()));
        request.getRequestDispatcher("empDetail.jsp").forward(request, response);
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
