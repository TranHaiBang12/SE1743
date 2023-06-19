/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author acer
 */
public class UpdateMyAccount extends HttpServlet {

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
            out.println("<title>Servlet UpdateMyAccount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMyAccount at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        AccountDAO acd = new AccountDAO();
        OrderDAO ord = new OrderDAO();
        String date = "", month = "", year = "";
        int cnt = 0;
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
        int gen;
        if (acd.getAccountByUserName(a.getUserName()).getGender().equals("Nam")) {
            gen = 1;
        } else if (acd.getAccountByUserName(a.getUserName()).getGender().equals("Nữ")) {
            gen = 0;
        } else {
            gen = -1;
        }
        request.setAttribute("gen", gen);
        request.setAttribute("dob", date + "-" + month + "-" + year);
        request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
        request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        AccountDAO acd = new AccountDAO();
        Account a = (Account) session.getAttribute("account");

        String gen_raw = request.getParameter("gen");
        String dob = request.getParameter("dob");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String city = request.getParameter("city");
        String pass = request.getParameter("pass");
        String rePass = request.getParameter("repass");
        String date = "", month = "", year = "";
        int cnt = 0;
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
        int gen1;
        if (acd.getAccountByUserName(a.getUserName()).getGender().equals("Nam")) {
            gen1 = 1;
        } else if (acd.getAccountByUserName(a.getUserName()).getGender().equals("Nữ")) {
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

        try {
            if (sdt.length() != 10) {
                throw new Exception("Loi");
            }
            int check = Integer.parseInt(sdt);
        } catch (Exception e) {
            String ms = "Vui lòng nhập đúng số điện thoại";
            request.setAttribute("ms", ms);
            request.setAttribute("gen", gen1);
            request.setAttribute("dob", date + "-" + month + "-" + year);
            request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
            request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
        }
        if(acd.checkDuplicatePhone(sdt, a.getUserName()) != null) {
            String ms = "Số điện thoại đã tồn tại";
            request.setAttribute("ms", ms);
            request.setAttribute("gen", gen1);
            request.setAttribute("dob", date + "-" + month + "-" + year);
            request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
            request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
        }

        if (checkEmail(email) == false) {
            String ms = "Vui lòng nhập đúng email";
            request.setAttribute("ms", ms);
            request.setAttribute("gen", gen1);
            request.setAttribute("dob", date + "-" + month + "-" + year);
            request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
            request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
        }

        if ((pass != null && !pass.equals("")) || (rePass != null && !rePass.equals(""))) {
            if (checkPass(pass) == true && rePass.equals(pass)) {
                acd.updAccountCngPass(a.getUserName(), gen, Date.valueOf(dob), sdt, email, city, pass);
                String ms = "Cập nhật thành công";
                request.setAttribute("ms", ms);
                request.setAttribute("gen", gen_raw);
                request.setAttribute("dob", date + "-" + month + "-" + year);
                request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
                request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
            } else {
                String ms = "Mật khẩu cần có ít nhất 8 ký tự và nhiều nhất là 20 ký tự, bao gồm ít nhất 1 ký tự thường, 1 ký tự viết hoa, 1 chữ số và 1 trong các ký tự đặc biệt sau: ! # $ @ _ + , ? . -";
                request.setAttribute("ms", ms);
                request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
            }
        } else {
            acd.updAccountNoCngPass(a.getUserName(), gen, Date.valueOf(dob), sdt, email, city);
            String ms = "Cập nhật thành công";
            request.setAttribute("ms", ms);
            request.setAttribute("gen", gen_raw);
            request.setAttribute("dob", date + "-" + month + "-" + year);
            request.setAttribute("acc", acd.getAccountByUserName(a.getUserName()));
            request.getRequestDispatcher("updMyAcc.jsp").forward(request, response);
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
