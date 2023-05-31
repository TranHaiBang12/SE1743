/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import DAL.StudentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import model.Student;

/**
 *
 * @author acer
 */
@WebServlet(name = "AddServlet", urlPatterns = {"/add"})
public class AddServlet extends HttpServlet {

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
            StudentDAO std = new StudentDAO();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form method = \"post\">\n"
                    + "            <label for = \"name\">Name:</label>\n"
                    + "            <input type =\"text\" id =\"name\" name =\"name\"/>\n"
                    + "            \n"
                    + "            <br/><!-- comment -->\n"
                    + "            Gender:\n"
                    + "            <input type =\"radio\" id =\"male\" checked name =\"gender\" value = \"1\"/>\n"
                    + "            <label for = \"male\">Male</label>\n"
                    + "            <input type =\"radio\" id =\"female\" name =\"gender\" value = \"0\"/>\n"
                    + "            <label for = \"female\">Female</label>\n"
                    + "            \n"
                    + "            <br/>\n"
                    + "            \n"
                    + "            <label for = \"date\">DOB:</label>\n"
                    + "            <input type =\"date\" id =\"date\" name =\"date\"/>\n"
                    + "            \n"
                    + "            <br/><!-- comment -->\n"
                    + "            \n"
                    + "            <input type =\"submit\" value =\"Create\"/>\n"
                    + "            \n"
                    + "        </form>");
            if (request.getAttribute("ms") != null) {
                out.println("<h2>" + request.getAttribute("ms") + "</h2>");
            }
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
        processRequest(request, response);
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
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String date_raw = request.getParameter("date");
        String ms;
        try {
            if(name.equals("")) {
                ms = "Please enter name";
                request.setAttribute("ms", ms);
                processRequest(request, response);
            }
            if (date_raw.equals("")) {
                ms = "Please enter date";
                request.setAttribute("ms", ms);
                processRequest(request, response);
            } else {
                StudentDAO sd = new StudentDAO();
                Date date = Date.valueOf(date_raw);
                Student s = new Student(name, gender, date);
                sd.insertStudent(s);
                //request.getRequestDispatcher("update").forward(request, response);
                response.sendRedirect("list");
            }
        } catch (Exception e) {

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
