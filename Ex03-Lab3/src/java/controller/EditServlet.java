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
import model.Student;

/**
 *
 * @author acer
 */
@WebServlet(name = "EditServlet", urlPatterns = {"/edit"})
public class EditServlet extends HttpServlet {

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
            int id = 0;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {

            }
            Student s = std.getStudent(id);
            if (s == null) {
                String error = "ID khong ton tai";
                out.println("<h2>" + error + "</h2>");
   
            } else {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet AddServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<form method = \"post\">\n"
                        + "            <label for = \"id\">Id:</label>\n"
                        + "            <input type =\"text\" readonly id =\"id\" name =\"id\"  value = " + request.getParameter("id") + ">\n"
                        + "            <br/>"
                        + "            <label for = \"name\">Name:</label>\n"
                        + "            <input type =\"text\" id =\"name\" placeholder = " + s.getName() + " name =\"name\"/>\n"
                        + "            \n"
                        + "            <br/><!-- comment -->\n"
                        + "            Gender:\n");
                if (s.getGender().equals("1")) {
                    out.println("            <input type =\"radio\" id =\"male\" checked name =\"gender\"value = \"1\"/>\n");
                    out.println("            <label for = \"male\">Male</label>\n"
                            + "            <input type =\"radio\" id =\"female\"  name =\"gender\" value = \"0\"/>\n"
                            + "            <label for = \"female\">Female</label>\n");
                } else if (s.getGender().equals("0")) {
                    out.println("            <input type =\"radio\" id =\"male\" name =\"gender\"value = \"1\"/>\n");
                    out.println("            <label for = \"male\">Male</label>\n"
                            + "            <input type =\"radio\" id =\"female\" checked  name =\"gender\" value = \"0\"/>\n"
                            + "            <label for = \"female\">Female</label>\n");
                }
                out.println("            \n"
                        + "            <br/>\n"
                        + "            \n"
                        + "            <label for = \"date\">DOB:</label>\n"
                        + "            <input type =\"date\" id =\"date\" placeholder = " + s.getDob() + " name =\"date\"/>\n"
                        + "            \n"
                        + "            <br/><!-- comment -->\n"
                        + "            \n"
                        + "            <input type =\"submit\" value =\"Update\"/>\n"
                        + "            \n"
                        + "        </form>");
                if (request.getAttribute("ms") != null) {
                    out.println("<h2>" + request.getAttribute("ms") + "</h2>");
                }
                out.println("</body>");
                out.println("</html>");
            }
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
        String id_raw = request.getParameter("id");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String date_raw = request.getParameter("date");
        System.out.println(id_raw);
        String ms;
        try {
            int id = Integer.parseInt(id_raw);
            if (name.equals("")) {
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
                s.setId(id);
                sd.updateStudent(s);
                response.sendRedirect("list");
            }
        } catch (Exception e) {
            ms = "ID dont exist";
            request.setAttribute("ms", ms);
            processRequest(request, response);
            System.out.println("23");

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
