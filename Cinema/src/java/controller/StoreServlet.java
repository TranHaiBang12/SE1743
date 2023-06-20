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
import java.util.ArrayList;
import java.util.List;
import model.Food;

/**
 *
 * @author acer
 */
public class StoreServlet extends HttpServlet {

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
            out.println("<title>Servlet StoreServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StoreServlet at " + request.getContextPath() + "</h1>");
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
        FoodDAO fda = new FoodDAO();
        System.out.println("1");
        List<Food> list = fda.getAllFood();
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
            System.out.println(end + "dâdawdawdwa");
            request.setAttribute("listPerPage", fda.getFoodByPage(list, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("data", list);
        } else {
            System.out.println(key + "" + type);
            List<Food> listSearch = new ArrayList<>();
            try {
                listSearch = fda.getFoodByType(type, key);
                if (listSearch.isEmpty()) {
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
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
            System.out.println(start + " " + end + "duaadâ");
            request.setAttribute("type", type);
            System.out.println(end + "dâdawdawdwa");
            request.setAttribute("listPerPage", fda.getFoodByPage(listSearch, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("data", list);
        }

        request.getRequestDispatcher("store.jsp").forward(request, response);
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
        FoodDAO fda = new FoodDAO();
        System.out.println("1");
        List<Food> list = fda.getAllFood();
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
            System.out.println(end + "dâdawdawdwa");
            request.setAttribute("listPerPage", fda.getFoodByPage(list, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("data", list);
        } else {
            System.out.println("4");
            List<Food> listSearch = new ArrayList<>();
            try {
                listSearch = fda.getFoodByType(type, key);
                if (listSearch.isEmpty()) {
                    System.out.println("2da");
                    throw new Exception("Loi");
                }
            } catch (Exception e) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
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
            System.out.println(start + " " + end + "duaadâ");
            request.setAttribute("type", type);
            System.out.println(end + "dâdawdawdwa");
            request.setAttribute("listPerPage", fda.getFoodByPage(listSearch, start, end));
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("page", page);
            request.setAttribute("data", list);
        }

        request.getRequestDispatcher("store.jsp").forward(request, response);
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
