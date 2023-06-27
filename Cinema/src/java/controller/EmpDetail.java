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
import java.text.DecimalFormat;
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
        DecimalFormat decimalFormat = new DecimalFormat("#");
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
        request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
        request.setAttribute("allP", ed.getAllPosition());
        request.setAttribute("allR", ed.getAllRole());
        request.setAttribute("id", id);
        request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID()));
        request.setAttribute("allCin", cnd.getAllCinema());
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
        String check = request.getParameter("check");
        String check1 = request.getParameter("check1");
        DecimalFormat decimalFormat = new DecimalFormat("#");
        if (check1.equals("1")) {
            if (check.equals("1")) {
                String cin_raw = request.getParameter("cin");
                int cinID = 0;
                CinemaDAO cnd = new CinemaDAO();
                try {
                    cinID = Integer.parseInt(cin_raw);
                    if (cnd.getCinemaByID(cinID) == null) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
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
                request.setAttribute("id", id);
                request.setAttribute("check", check);
                request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID));
                request.setAttribute("allCin", cnd.getAllCinema());
                request.setAttribute("cin", cnd.getCinemaByID(cinID));
                request.setAttribute("mng", ed.getEmployeeByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getManagerID()));
                request.setAttribute("point", point);
                request.setAttribute("dob", date + "-" + month + "-" + year);
                request.setAttribute("accE", ed.getAccEmpByUserName(ed.empD(id).getUsername()));
                request.setAttribute("hiredDate", dateH + "-" + monthH + "-" + yearH);
                request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(ed.empD(id).getUsername()));
                request.getRequestDispatcher("empDetail.jsp").forward(request, response);
            }
        }
        else {
            String empID = request.getParameter("empID");
            String user = request.getParameter("user");
            String ln = request.getParameter("ln");
            String fn = request.getParameter("fn");
            String gen_raw = request.getParameter("gen");
            String id_raw = request.getParameter("id");
            String cccd = request.getParameter("cccd");
            String dob_raw = request.getParameter("dob");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String cin_raw = request.getParameter("cin");
            String hiredDate_raw = request.getParameter("hiredDate");
            String position = request.getParameter("position");
            String mngID_raw = request.getParameter("mng");
            String img = request.getParameter("img");
            String salary_raw = request.getParameter("salary");
            String role_raw = request.getParameter("role");
            System.out.println(empID + " " + user + " " + ln + " " + fn + " " + gen_raw + " " + id_raw + " " + cccd + " " + dob_raw + " " + sdt + " " + email + " " + address + " " + cin_raw + " " + hiredDate_raw + " " + position + " " + mngID_raw + " " + img + " " + salary_raw + " " + role_raw);
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
