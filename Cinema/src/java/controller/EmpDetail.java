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
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
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
                String position = request.getParameter("position");
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
                System.out.println(position);
                if (position.equals("Nhân Viên")) {
                    System.out.println("1");
                } else if (position.equals("Quản lý")) {
                    System.out.println("2");
                }

                request.setAttribute("mn", position);

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
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID, ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));

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
        } else {
            EmployeeDAO ed = new EmployeeDAO();
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
            int mngID = 0;
            System.out.println(mngID_raw);
            System.out.println(request.getParameter("mngD"));
            if ((mngID_raw == null && request.getParameter("mngD") != null) || (mngID_raw != null && mngID_raw.equals("") && request.getParameter("mngD") != null) || (mngID_raw == null && request.getParameter("mngD") == null)) {
                mngID = 0;
            } else {
                mngID = Integer.parseInt(mngID_raw);
            }
            System.out.println(mngID);
            String img = request.getParameter("img");
            String salary_raw = request.getParameter("salary");
            String role_raw = request.getParameter("role");
            int role = Integer.parseInt(role_raw);
            String pass = request.getParameter("pass");
            String rePass = request.getParameter("repass");
            Double salary = Double.parseDouble(salary_raw);
            salary = Double.parseDouble(decimalFormat.format(salary));
            int id = Integer.parseInt(id_raw);
            int cnt = 0;
            String date = "", year = "", month = "";
            String dateH = "", yearH = "", monthH = "";
            String t = ed.getAccEmpByUserName(user).getDob().toString();
            for (int i = 0; i < t.length(); i++) {
                if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                    year = t.substring(cnt, i);
                    cnt = i;
                } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                    month = t.substring(cnt + 1, i);
                    cnt = i;
                }
            }
            AccountDAO acd = new AccountDAO();
            date = t.substring(cnt + 1);
            int gen1;
            if (ed.getAccEmpByUserName(user).getGender().equals("Nam")) {
                gen1 = 1;
            } else if (acd.getAccountByUserName(user).getGender().equals("Nữ")) {
                gen1 = 0;
            } else {
                gen1 = -1;
            }

            String gen;
            if (gen_raw.equals("1")) {
                gen = "Nam";
            } else if (gen_raw.equals("0")) {
                gen = "Nữ";
            } else {
                gen = "Khác";
            }
            CinemaDAO cnd = new CinemaDAO();
            PointDAO pd = new PointDAO();
            int point = 0;
            if(pd.getAccountPoint(user) == null) {
                point = 0;
            }
            else {
                point = pd.getAccountPoint(user).getPoint();
            }
            OrderDAO ord = new OrderDAO();
            try {
                if (sdt.length() != 10) {
                    throw new Exception("Loi");
                }
                int check2 = Integer.parseInt(sdt);
            } catch (Exception e) {
                String ms = "Vui lòng nhập đúng số điện thoại";
                request.setAttribute("ms", ms);
                request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("id", id);
                request.setAttribute("check", 1);
                request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
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
            if (ed.checkDuplicatePhone(sdt, user) != null) {
                String ms = "Số điện thoại đã tồn tại";
                request.setAttribute("ms", ms);
                request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("id", id);
                request.setAttribute("check", 1);
                request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
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

            if (checkEmail(email) == false) {
                String ms = "Vui lòng nhập đúng email";
                request.setAttribute("ms", ms);
                request.setAttribute("check", 1);
                request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("id", id);
                request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
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

            if ((pass != null && !pass.equals("")) || (rePass != null && !rePass.equals(""))) {
                System.out.println("1");
                if (checkPass(pass) == true && rePass.equals(pass)) {
                    acd.updRole(user, role);
                    ed.adminUpdCngPassE(ln, fn, gen, Date.valueOf(dob_raw), address, cccd, sdt, email, Date.valueOf(hiredDate_raw), position, Integer.parseInt(cin_raw), mngID, salary, img, user, pass);
                    String ms = "Cập nhật thành công";
                    request.setAttribute("ms", ms);
                    request.setAttribute("check", 1);
                    request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                    request.setAttribute("allP", ed.getAllPosition());
                    request.setAttribute("allR", ed.getAllRole());
                    request.setAttribute("id", id);
                    request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
                    request.setAttribute("allCin", cnd.getAllCinema());
                    request.setAttribute("cin", cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()));
                    request.setAttribute("mng", ed.getEmployeeByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getManagerID()));
                    request.setAttribute("point", point);
                    request.setAttribute("dob", date + "-" + month + "-" + year);
                    request.setAttribute("accE", ed.getAccEmpByUserName(ed.empD(id).getUsername()));
                    request.setAttribute("hiredDate", dateH + "-" + monthH + "-" + yearH);
                    request.setAttribute("totalOrd", ord.getNumberOfOrderByUserName(ed.empD(id).getUsername()));
                    request.getRequestDispatcher("empDetail.jsp").forward(request, response);
                } else {
                    String ms = "Mật khẩu cần có ít nhất 8 ký tự và nhiều nhất là 20 ký tự, bao gồm ít nhất 1 ký tự thường, 1 ký tự viết hoa, 1 chữ số và 1 trong các ký tự đặc biệt sau: ! # $ @ _ + , ? . -";
                    request.setAttribute("ms", ms);
                    request.setAttribute("check", 1);
                    request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                    request.setAttribute("allP", ed.getAllPosition());
                    request.setAttribute("allR", ed.getAllRole());
                    request.setAttribute("id", id);
                    request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
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
            } else {
                System.out.println("4");
                ed.adminUpdNoCngPassE(ln, fn, gen, Date.valueOf(dob_raw), address, cccd, sdt, email, Date.valueOf(hiredDate_raw), position, Integer.parseInt(cin_raw), mngID, salary, img, user);
                acd.updRole(user, role);
                String ms = "Cập nhật thành công";
                request.setAttribute("ms", ms);
                request.setAttribute("check", 1);
                request.setAttribute("salary", decimalFormat.format(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getSalary()));
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("id", id);
                request.setAttribute("allM", ed.getAllManagerByCinID(cnd.getCinemaByID(ed.getAccEmpByUserName(ed.empD(id).getUsername()).getCinID()).getCinID(), ed.getAccEmpByUserName(ed.empD(id).getUsername()).getEmpID()));
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
        }
    }

    public boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPass(String pass) {
        int check = 0;
        String a = "abcdefghijklmnopqrstuvwxyz";
        String A = a.toUpperCase();
        String num = "0123456789";
        String spe = "/@#$%^&*!";
        for (int i = 0; i < a.length(); i++) {
            if (pass.contains(a.substring(i, i + 1))) {
                check = 1;
                break;
            }
        }
        for (int i = 0; i < A.length(); i++) {
            if (pass.contains(A.substring(i, i + 1))) {
                check++;
                break;
            }
        }
        for (int i = 0; i < num.length(); i++) {
            if (pass.contains(num.substring(i, i + 1))) {
                check++;
                break;
            }
        }
        for (int i = 0; i < spe.length(); i++) {
            if (pass.contains(spe.substring(i, i + 1))) {
                check++;
                break;
            }
        }
        if (check == 4 && pass.length() >= 8) {
            return true;
        }

        return false;
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
