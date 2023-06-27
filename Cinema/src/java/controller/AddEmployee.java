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
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author acer
 */
public class AddEmployee extends HttpServlet {

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
            out.println("<title>Servlet AddEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEmployee at " + request.getContextPath() + "</h1>");
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
        EmployeeDAO ed = new EmployeeDAO();
        CinemaDAO cnd = new CinemaDAO();
        request.setAttribute("allP", ed.getAllPosition());
        request.setAttribute("mn", "Nhân Viên");
        request.setAttribute("allM", ed.getAllManagerByCinID(1, 0));
        request.setAttribute("allR", ed.getAllRole());
        request.setAttribute("allCin", cnd.getAllCinema());

        request.getRequestDispatcher("addEmp.jsp").forward(request, response);
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
        DecimalFormat decimalFormat = new DecimalFormat("#");
        String check = request.getParameter("check");
        String check1 = request.getParameter("check1");
        if (check1.equals("1")) {
            if (check.equals("1")) {
                String cin = request.getParameter("cin");
                int cinID = Integer.parseInt(cin);
                String position = request.getParameter("position");
                EmployeeDAO ed = new EmployeeDAO();
                CinemaDAO cnd = new CinemaDAO();
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("cin", cnd.getCinemaByID(cinID));
                request.setAttribute("mn", position);
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("allCin", cnd.getAllCinema());
                request.setAttribute("check", check);
                request.getRequestDispatcher("addEmp.jsp").forward(request, response);
            }
        } else {
            EmployeeDAO ed = new EmployeeDAO();
            String user = request.getParameter("user");
            String ln = request.getParameter("ln");
            String fn = request.getParameter("fn");
            String gen_raw = request.getParameter("gen");
            String cccd = request.getParameter("cccd");
            String dob_raw = request.getParameter("dob");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String cin_raw = request.getParameter("cin");
            String hiredDate_raw = request.getParameter("hiredDate");
            String position = request.getParameter("position");
            String mngID_raw = request.getParameter("mng");
            int cinID = Integer.parseInt(cin_raw);
            int mngID = 0;
            System.out.println(mngID_raw);
            System.out.println(request.getParameter("mngD"));
            if ((mngID_raw == null && request.getParameter("mngD") != null) || (mngID_raw != null && mngID_raw.equals("") && request.getParameter("mngD") != null) || (mngID_raw == null && request.getParameter("mngD") == null)) {
                mngID = 0;
            } else {
                mngID = Integer.parseInt(mngID_raw);
            }
            String img = request.getParameter("img");
            String salary_raw = request.getParameter("salary");
            String role_raw = request.getParameter("role");
            int role = Integer.parseInt(role_raw);
            String pass = request.getParameter("pass");
            String rePass = request.getParameter("repass");
            Double salary = Double.parseDouble(salary_raw);
            salary = Double.parseDouble(decimalFormat.format(salary));
            int cnt = 0;
            String date = "", year = "", month = "";
            String dateH = "", yearH = "", monthH = "";
            Date dd = Date.valueOf((Calendar.getInstance().getTime().getYear() + 1900) + "-" + (Calendar.getInstance().getTime().getMonth() + 1) + "-" + Calendar.getInstance().getTime().getDate());
            String t = dob_raw.toString();
            for (int i = 0; i < t.length(); i++) {
                if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                    year = t.substring(cnt, i);
                    cnt = i;
                } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                    month = t.substring(cnt + 1, i);
                    cnt = i;
                }
            }
            cnt = 0;
            date = t.substring(cnt + 1);
            t = dd.toString();
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
            AccountDAO acd = new AccountDAO();

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
            if (pd.getAccountPoint(user) == null) {
                point = 0;
            } else {
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
                request.setAttribute("mn", position);
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("check", 1);
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                request.setAttribute("allCin", cnd.getAllCinema());
                request.setAttribute("user", user);
                request.setAttribute("ln", ln);
                request.setAttribute("user", fn);
                request.setAttribute("gen", gen);
                request.setAttribute("cccd", cccd);
                request.setAttribute("dob_raw", dob_raw);
                request.setAttribute("sdt", sdt);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.setAttribute("cin_raw", cinID);
                request.setAttribute("hiredDate_raw", hiredDate_raw);
                request.setAttribute("position", position);
                request.setAttribute("mngID_raw", mngID);
                request.setAttribute("img", img);
                request.setAttribute("salary", salary);
                request.setAttribute("role_raw", role_raw);
                request.setAttribute("pass", pass);
                request.getRequestDispatcher("addEmp.jsp").forward(request, response);
            }
            if (ed.checkDuplicatePhone(sdt, user) != null) {
                String ms = "Số điện thoại đã tồn tại";
                request.setAttribute("ms", ms);
                request.setAttribute("mn", position);
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("check", 1);
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                request.setAttribute("allCin", cnd.getAllCinema());
                request.setAttribute("user", user);
                request.setAttribute("ln", ln);
                request.setAttribute("user", fn);
                request.setAttribute("gen", gen);
                request.setAttribute("cccd", cccd);
                request.setAttribute("dob_raw", dob_raw);
                request.setAttribute("sdt", sdt);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.setAttribute("cin_raw", cinID);
                request.setAttribute("hiredDate_raw", hiredDate_raw);
                request.setAttribute("position", position);
                request.setAttribute("mngID_raw", mngID);
                request.setAttribute("img", img);
                request.setAttribute("salary", salary);
                request.setAttribute("role_raw", role_raw);
                request.setAttribute("pass", pass);
                request.getRequestDispatcher("addEmp.jsp").forward(request, response);
            }

            if (checkEmail(email) == false) {
                String ms = "Vui lòng nhập đúng email";
                request.setAttribute("ms", ms);
                request.setAttribute("mn", position);
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("check", 1);
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                request.setAttribute("allCin", cnd.getAllCinema());
                request.setAttribute("user", user);
                request.setAttribute("ln", ln);
                request.setAttribute("user", fn);
                request.setAttribute("gen", gen);
                request.setAttribute("cccd", cccd);
                request.setAttribute("dob_raw", dob_raw);
                request.setAttribute("sdt", sdt);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.setAttribute("cin_raw", cinID);
                request.setAttribute("hiredDate_raw", hiredDate_raw);
                request.setAttribute("position", position);
                request.setAttribute("mngID_raw", mngID);
                request.setAttribute("img", img);
                request.setAttribute("salary", salary);
                request.setAttribute("role_raw", role_raw);
                request.setAttribute("pass", pass);
                request.getRequestDispatcher("addEmp.jsp").forward(request, response);
            }

            if (ed.checkCCCD(cccd, 0) != null) {
                String ms = "CCCD đã tồn tại";
                request.setAttribute("ms", ms);
                request.setAttribute("mn", position);
                request.setAttribute("allP", ed.getAllPosition());
                request.setAttribute("allR", ed.getAllRole());
                request.setAttribute("check", 1);
                request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                request.setAttribute("allCin", cnd.getAllCinema());
                request.setAttribute("user", user);
                request.setAttribute("ln", ln);
                request.setAttribute("user", fn);
                request.setAttribute("gen", gen);
                request.setAttribute("cccd", cccd);
                request.setAttribute("dob_raw", dob_raw);
                request.setAttribute("sdt", sdt);
                request.setAttribute("email", email);
                request.setAttribute("address", address);
                request.setAttribute("cin_raw", cinID);
                request.setAttribute("hiredDate_raw", hiredDate_raw);
                request.setAttribute("position", position);
                request.setAttribute("mngID_raw", mngID);
                request.setAttribute("img", img);
                request.setAttribute("salary", salary);
                request.setAttribute("role_raw", role_raw);
                request.setAttribute("pass", pass);
                request.getRequestDispatcher("addEmp.jsp").forward(request, response);
            }

            if ((pass != null && !pass.equals("")) || (rePass != null && !rePass.equals(""))) {
                System.out.println("1");
                if (checkPass(pass) == true && rePass.equals(pass)) {
                    ed.insertAccEmp(user, pass, role);
                    int a = ed.insertEmp(ln, fn, gen, Date.valueOf(dob_raw), address, cccd, sdt, email, dd, position, cinID, mngID, salary, img, user);
                    String ms = "Add thành công";
                    request.setAttribute("empID", a);
                    request.setAttribute("ms", ms);
                    request.setAttribute("mp", t);
                    request.setAttribute("mn", position);
                    request.setAttribute("allP", ed.getAllPosition());
                    request.setAttribute("allR", ed.getAllRole());
                    request.setAttribute("check", 1);
                    request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                    request.setAttribute("allCin", cnd.getAllCinema());
                    request.setAttribute("user", user);
                    request.setAttribute("ln", ln);
                    request.setAttribute("fn", fn);
                    request.setAttribute("gen", gen);
                    request.setAttribute("cccd", cccd);
                    request.setAttribute("dob_raw", dob_raw);
                    request.setAttribute("sdt", sdt);
                    request.setAttribute("email", email);
                    request.setAttribute("address", address);
                    request.setAttribute("cin_raw", cinID);
                    request.setAttribute("hiredDate_raw", hiredDate_raw);
                    request.setAttribute("position", position);
                    request.setAttribute("mngID_raw", mngID);
                    request.setAttribute("img", img);
                    request.setAttribute("salary", salary);
                    request.setAttribute("role_raw", role_raw);
                    request.setAttribute("pass", pass);
                    request.getRequestDispatcher("addEmp.jsp").forward(request, response);
                } else {
                    String ms = "Mật khẩu cần có ít nhất 8 ký tự và nhiều nhất là 20 ký tự, bao gồm ít nhất 1 ký tự thường, 1 ký tự viết hoa, 1 chữ số và 1 trong các ký tự đặc biệt sau: ! # $ @ _ + , ? . -";
                    request.setAttribute("ms", ms);
                    
                    request.setAttribute("mn", position);
                    request.setAttribute("allP", ed.getAllPosition());
                    request.setAttribute("allR", ed.getAllRole());
                    request.setAttribute("check", 1);
                    request.setAttribute("allM", ed.getAllManagerByCinID(cinID, 0));
                    request.setAttribute("allCin", cnd.getAllCinema());
                    request.setAttribute("user", user);
                    request.setAttribute("ln", ln);
                    request.setAttribute("user", fn);
                    request.setAttribute("gen", gen);
                    request.setAttribute("cccd", cccd);
                    request.setAttribute("dob_raw", dob_raw);
                    request.setAttribute("sdt", sdt);
                    request.setAttribute("email", email);
                    request.setAttribute("address", address);
                    request.setAttribute("cin_raw", cinID);
                    request.setAttribute("hiredDate_raw", hiredDate_raw);
                    request.setAttribute("position", position);
                    request.setAttribute("mngID_raw", mngID);
                    request.setAttribute("img", img);
                    request.setAttribute("salary", salary);
                    request.setAttribute("role_raw", role_raw);
                    request.setAttribute("pass", pass);
                    request.getRequestDispatcher("addEmp.jsp").forward(request, response);
                }
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
