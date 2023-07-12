/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.FoodDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Food;

/**
 *
 * @author acer
 */
public class FoodOutOfOrder extends HttpServlet {

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
            out.println("<title>Servlet FoodOutOfOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FoodOutOfOrder at " + request.getContextPath() + "</h1>");
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
        FoodDAO fda = new FoodDAO();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        List<Food> list = fda.getAllFoodOOO();
        if (!list.isEmpty()) {
            String page_raw = request.getParameter("page");
            int page = 0;
            if (page_raw == null) {
                page = 1;
            } else {
                try {
                    page = Integer.parseInt(page_raw);
                    if (page <= 0) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }

            int numPerPage = 9;
            int totalPage = 0;
            int start = 0;
            int end = 0;
            String type = request.getParameter("type");
            String key = request.getParameter("key");
            if (type == null && key == null) {
                totalPage = (list.size() % numPerPage == 0) ? (list.size() / numPerPage) : (list.size() / numPerPage + 1);
                if (page > totalPage) {

                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                start = (page - 1) * numPerPage;
                end = (page * numPerPage > list.size()) ? (list.size() - 1) : (page * numPerPage - 1);
                List<Food> listPerPage = fda.getFoodByPage(list, start, end);
                for (int i = 0; i < listPerPage.size(); i++) {
                    listPerPage.get(i).setPriceOS(decimalFormat.format(listPerPage.get(i).getPrice()));
                    listPerPage.get(i).setPriceNS(decimalFormat.format(listPerPage.get(i).getPrice() - listPerPage.get(i).getPrice() * listPerPage.get(i).getDiscount()));
                }
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setPriceOS(decimalFormat.format(list.get(i).getPrice()));
                    list.get(i).setPriceNS(decimalFormat.format(list.get(i).getPrice() - list.get(i).getPrice() * list.get(i).getDiscount()));
                }
                request.setAttribute("listPerPage", listPerPage);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("data", list);

                for (int i = 0; i < fda.getFoodByPage(list, start, end).size(); i++) {
                    String t = decimalFormat.format(fda.getFoodByPage(list, start, end).get(i).getDiscount());

                    fda.getFoodByPage(list, start, end).get(i).setDiscount(Double.parseDouble(t));
                }

            } else {

                List<Food> listSearch = new ArrayList<>();
                try {
                    listSearch = fda.getFoodByType(type, key);
                    if (listSearch.isEmpty()) {
                        throw new Exception("Loi");
                    }
                } catch (Exception e) {
                    String ms = "Xin lỗi, chúng tôi không phục vụ sản phẩm bạn tìm kiếm";
                    request.setAttribute("ms", ms);
                    request.getRequestDispatcher("store.jsp").forward(request, response);
                }
                for (int i = 0; i < listSearch.size(); i++) {
                    System.out.println(listSearch.get(i).getFoodDescript());
                }
                totalPage = (listSearch.size() % numPerPage == 0) ? (listSearch.size() / numPerPage) : (listSearch.size() / numPerPage + 1);
                if (page > totalPage) {
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
                start = (page - 1) * numPerPage;
                end = (page * numPerPage > listSearch.size()) ? (listSearch.size() - 1) : (page * numPerPage - 1);

                List<Food> listPerPage = fda.getFoodByPage(list, start, end);
                for (int i = 0; i < listPerPage.size(); i++) {
                    listPerPage.get(i).setPriceOS(decimalFormat.format(listPerPage.get(i).getPrice()));
                    listPerPage.get(i).setPriceNS(decimalFormat.format(listPerPage.get(i).getPrice() - listPerPage.get(i).getPrice() * listPerPage.get(i).getDiscount()));
                }
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setPriceOS(decimalFormat.format(list.get(i).getPrice()));
                    list.get(i).setPriceNS(decimalFormat.format(list.get(i).getPrice() - list.get(i).getPrice() * list.get(i).getDiscount()));
                }
                request.setAttribute("type", type);
                request.setAttribute("listPerPage", listPerPage);
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("page", page);
                request.setAttribute("data", list);
            }

            request.getRequestDispatcher("fooo.jsp").forward(request, response);
        }
        else {
             request.setAttribute("ms", "Hiện không có sản phẩm nào hết hàng");
             request.getRequestDispatcher("fooo.jsp").forward(request, response);
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
