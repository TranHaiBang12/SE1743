/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CinemaDAO;
import dal.EventDAO;
import dal.FoodDAO;
import dal.MovieDAO;
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
public class UpdEvent extends HttpServlet {

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
            out.println("<title>Servlet UpdEvent</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdEvent at " + request.getContextPath() + "</h1>");
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        EventDAO evd = new EventDAO();
        String id_raw = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            if (evd.getEventByCode(id) == null) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        model.Event e = evd.getEventByCode(id);

        String dateS = "", monthS = "", yearS = "";
        String dateE = "", monthE = "", yearE = "";
        String t = e.getStartDate().toString();
        int cnt = 0;
        for (int i = 0; i < t.length(); i++) {
            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                yearS = t.substring(cnt, i);
                cnt = i;
            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                monthS = t.substring(cnt + 1, i);
                cnt = i;
            }
        }
        dateS = t.substring(cnt + 1);
        cnt = 0;
        t = e.getEndDate().toString();
        for (int i = 0; i < t.length(); i++) {
            if (t.substring(i, i + 1).equals("-") && i != cnt && cnt == 0) {
                yearE = t.substring(cnt, i);
                cnt = i;
            } else if (t.substring(i, i + 1).equals("-") && i != cnt && cnt != 0) {
                monthE = t.substring(cnt + 1, i);
                cnt = i;
            }
        }
        dateE = t.substring(cnt + 1);

        CinemaDAO cnd = new CinemaDAO();
        MovieDAO mvd = new MovieDAO();

        String typeApply = "";
        if (evd.getEventMov(id) != null) {
            typeApply = "Phim";
            System.out.println("k");
            int movID = evd.getEventMov(id).getMovID();
            request.setAttribute("movID", movID);
            
        } else if (evd.getEventOrder(id) != null) {
            typeApply = "Hóa Đơn";
            System.out.println("p");
            double price = evd.getEventOrder(id).getDiscount();
            String tst = decimalFormat.format(price);
            price = Double.parseDouble(tst);
            request.setAttribute("price", price);
        }

        List<Food> f = new ArrayList<>();
        FoodDAO fd = new FoodDAO();
        String pc = "";
        double discount = 0;
        List<String> listTypeA = new ArrayList<>();
        listTypeA.add("Phim");
        listTypeA.add("Hóa Đơn");
        request.setAttribute("listTypeA", listTypeA);
        request.setAttribute("typeApply", typeApply);
        e.setStartS(dateS + "-" + monthS + "-" + yearS);
        e.setEndS(dateE + "-" + monthE + "-" + yearE);
        if (evd.getEventDiscountO(id) != null) {
            if (evd.getEventDiscountO(id).getType().equals("FD")) {
                pc = "Giảm giá vé";
                discount = evd.getEventDiscountO(id).getDiscount();
                String tst = decimalFormat.format(discount);
                discount = Double.parseDouble(tst);
            } else if (evd.getEventDiscountO(id).getType().equals("TK")) {
                pc = "Giảm giá đồ ăn, nước";
                discount = evd.getEventDiscountO(id).getDiscount();
                String tst = decimalFormat.format(discount);
                discount = Double.parseDouble(tst);
            }
            request.setAttribute("pc", pc);
            request.setAttribute("discount", discount);
        } else if (evd.getEventDiscountM(id) != null) {
            pc = "Giảm giá vé";
            discount = evd.getEventDiscountM(id).getDiscount();
            String tst = decimalFormat.format(discount);
            discount = Double.parseDouble(tst);
            request.setAttribute("pc", pc);
            request.setAttribute("discount", discount);
        } else if (evd.getAllProductInEvent(id) != null) {
            List<String> prCode = evd.getAllProductInEvent(id).getProduct();
            for (int i = 0; i < prCode.size(); i++) {
                f.add(fd.getFoodById(prCode.get(i)));
            }
            request.setAttribute("f", f);
            request.setAttribute("allF", fd.getAllFood());
        }
        int eventType = e.getEventType();
        request.setAttribute("evType", eventType);
        request.setAttribute("id", id);
        request.setAttribute("e", e);
        request.setAttribute("mov", mvd.getAllMovies());
        request.setAttribute("type", evd.getAllEventType());
        request.setAttribute("cin", cnd.getAllCinema());
        request.getRequestDispatcher("updEv.jsp").forward(request, response);
        //processRequest(request, response);
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        EventDAO evd = new EventDAO();
        MovieDAO mvd = new MovieDAO();
        FoodDAO fd = new FoodDAO();
        CinemaDAO cnd = new CinemaDAO();
        String id_raw = request.getParameter("id");
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);
            if (evd.getEventByCode(id) == null) {
                throw new Exception("Loi");
            }
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        String startDate_raw = request.getParameter("startDate");
        String endDate_raw = request.getParameter("endDate");
        String content = request.getParameter("content");
        String dctype = request.getParameter("dctype");
        String dc = request.getParameter("dc");
        String fCode = request.getParameter("fCode");
        String applytype = request.getParameter("applytype");
        String mov = request.getParameter("mov");
        String price = request.getParameter("price");
        String cin_raw[] = request.getParameterValues("cin");
        String gN_raw = request.getParameter("gN");
        int gN = 0;
        try {
            gN = Integer.parseInt(gN_raw);
        } catch (Exception e) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
        
        System.out.println(startDate_raw + " " + endDate_raw + " " + content + " " + dctype + " " + dc + " " + fCode + " " + applytype + " " + mov + " " + price);        
        System.out.println(gN);
        int cin[] = new int[gN];
        for (int i = 0; i < cin.length; i++) {
            cin[i] = Integer.parseInt(cin_raw[i]);
            System.out.println(cin[i]);
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
