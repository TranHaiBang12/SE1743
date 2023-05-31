/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import model.Student;

/**
 *
 * @author acer
 */
public class StudentServlet extends HttpServlet {

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
            out.println("<title>Servlet StudentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StudentServlet at " + request.getContextPath() + "</h1>");
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
        String num_raw = request.getParameter("num");
        Random generator = new Random();
        List<Student> list = new ArrayList<>();
        boolean gender;
        int id;
        String name = "";
        int date;
        int month;
        String ms = null;
        int year;
        if (num_raw != null && num_raw.equals("")) {
            ms = "Vui long nhap so hoc sinh";
        } else {
            try {
                int num = Integer.parseInt(num_raw);
                if (num <= 0) {
                    ms = "Vui long nhap so lon hon 0";
                }
                for (int i = 0; i < num; i++) {
                    id = i + 1;
                    name = randomAlpha(generator.nextInt(7) + 3);
                    gender = generator.nextBoolean();
                    month = generator.nextInt(12) + 1;
                    year = generator.nextInt((2023 - 1900) + 1) + 1900;
                    if (month == 1
                            || month == 3
                            || month == 5
                            || month == 7
                            || month == 8
                            || month == 10
                            || month == 12) {
                        date = generator.nextInt(31) + 1;
                    } else if (year % 4 == 0 && month == 2) {
                        date = generator.nextInt(28) + 1;
                    } else if (year % 4 != 0 && month == 2) {
                        date = generator.nextInt(29) + 1;
                    } else {
                        date = generator.nextInt(30) + 1;
                    }
                    DateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
                    Date DOB = (Date) parser.parse(date + "-" + month + "-" + year);

                    request.setAttribute("num", num);
                    list.add(new Student(id, name, gender, DOB));

                }
            } catch (Exception e) {

            }
        }
        request.setAttribute("ms", ms);
        request.setAttribute("data", list);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }

    public String randomAlpha(int numCha) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        String alpha = alphabet + alphabet.toUpperCase();
        Random generator = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numCha; i++) {
            int number = generator.nextInt(alpha.length() - 1 + 1);
            char ch = alpha.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
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
