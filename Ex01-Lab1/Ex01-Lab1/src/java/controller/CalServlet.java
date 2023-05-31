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

/**
 *
 * @author acer
 */
public class CalServlet extends HttpServlet {

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
            String op = "";
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<form >\n"
                    + "            <label for =\"so1\">First:&nbsp&nbsp &nbsp &nbsp</label>\n"
                    + "            <input type =\"text\" id =\"so1\" name =\"so1\" value = " + (request.getParameter("so1") == null ? " " : request.getParameter("so1")) + ">\n"
                    + "\n"
                    + "            <br/>\n"
                    + "\n"
                    + "            <label for =\"so2\">Second: &nbsp</label>\n"
                    + "            <input type =\"text\" id =\"so2\" name =\"so2\" value = " + (request.getParameter("so2") == null ? " " : request.getParameter("so2")) + ">\n"
                    + "\n"
                    + "            <br/><!-- comment -->\n"
                    + "\n"
                    + "            <label for =\"op\">Operator:</label>\n"
                    + "            <select id = \"op\" name = \"op\" " + ">\n"
                    + "                <option value = \"+\" " + ((request.getParameter("op") != null && request.getParameter("op").equals("+"))  ? "selected" : "") + ">+</option>\n"
                    + "                <option value = \"-\" " + ((request.getParameter("op") != null && request.getParameter("op").equals("-"))  ? "selected" : "") + ">-</option><!-- comment -->\n"
                    + "                <option value = \"*\" " + ((request.getParameter("op") != null && request.getParameter("op").equals("*"))  ? "selected" : "") + ">*</option><!-- comment -->\n"
                    + "                <option value = \"/\" " + ((request.getParameter("op") != null && request.getParameter("op").equals("/"))  ? "selected" : "") + ">/</option>\n"
                    + "            </select>\n"
                    + "\n"
                    + "            <br/>\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            &nbsp\n"
                    + "            <input type =\"submit\" value =\"Compute\"/>\n"
                    + "        </form>");
            
            if (request.getParameter("op") != null && request.getParameter("so1") != null && request.getParameter("so2") != null) {
                try {
                    String so1_raw = request.getParameter("so1");
                    String so2_raw = request.getParameter("so2");
                    op = request.getParameter("op");
                    double so1 = Double.parseDouble(so1_raw);
                    double so2 = Double.parseDouble(so2_raw);
                    String result = result(so1, so2, op);
                    out.println("Result:&nbsp &nbsp &nbsp<input type =\"text\" id =\"result\" name =\"result\" value = " + result + ">\n");
                } catch (Exception e) {
                }

            } else {
                out.println("Result:&nbsp &nbsp &nbsp<input type =\"text\" id =\"result\" name =\"result\" " + "/>\n");
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
        processRequest(request, response);
    }

    private String result(double so1, double so2, String op) {
        
        String result = "";
        switch (op) {
            case "+":
                result += (so1 + so2);
                break;
            case "-":
                result += so1 - so2;
                break;
            case "*":
                result += so1 * so2;
                break;
            case "/":
                if (so2 != 0) {
                    result += so1 / so2;
                }
                else {
                    result = "MathError(DivideByZero)";
                }
                break;
        }
        return result;
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
