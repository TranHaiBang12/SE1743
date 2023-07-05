/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DeviceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DeviceImport;

/**
 *
 * @author acer
 */
public class UpdDevice extends HttpServlet {

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
            out.println("<title>Servlet UpdDevice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdDevice at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("id") == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } else {
            String id = request.getParameter("id");
            DeviceDAO dvd = new DeviceDAO();
            DeviceImport device = null;
            try {
                device = dvd.getDeviceByCode(id);
                if (device == null) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.setAttribute("type", dvd.getAllDeviceType());
            request.setAttribute("device", device);
            request.getRequestDispatcher("updDevice.jsp").forward(request, response);

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
        String code = request.getParameter("code");
        String type = request.getParameter("type");
        String price_raw = request.getParameter("price");
        String descript = request.getParameter("descript");
        String img = request.getParameter("img");
        String oCode = request.getParameter("ocode");

        double price = 0;
        try {
            price = Double.parseDouble(price_raw);
            if (price <= 0) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        DeviceDAO dvd = new DeviceDAO();

        if (!code.equals(oCode)) {
            if (dvd.getDeviceByCode(code) != null) {
                String ms = "Mã thiết bị đã tồn tại";
                DeviceImport device = dvd.getDeviceByCode(oCode);
       
                request.setAttribute("ms", ms);
                request.setAttribute("type", dvd.getAllDeviceType());
                request.setAttribute("device", device);
                request.getRequestDispatcher("updDevice.jsp").forward(request, response);
            }
        }

        dvd.upd(code, type, price, descript, img, oCode);
        DeviceImport device = null;
        try {
            device = dvd.getDeviceByCode(code);
            if (device == null) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        device.setTypeName(dvd.getTypeNameByID(type));
        request.setAttribute("ms", "Sửa thành công");
        request.setAttribute("check", 1);
        request.setAttribute("type", dvd.getAllDeviceType());
        request.setAttribute("device", device);
        request.getRequestDispatcher("updDevice.jsp").forward(request, response);
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
