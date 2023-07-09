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
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Food;

/**
 *
 * @author acer
 */
public class AddEvent extends HttpServlet {

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
            out.println("<title>Servlet AddEvent</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddEvent at " + request.getContextPath() + "</h1>");
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
        EventDAO evd = new EventDAO();
        MovieDAO mvd = new MovieDAO();
        FoodDAO fd = new FoodDAO();
        CinemaDAO cnd = new CinemaDAO();
        request.setAttribute("mov", mvd.getAllMovies());

        List<String> otype = new ArrayList<>();
        otype.add("Vé");
        otype.add("Đồ Ăn");
        request.setAttribute("otype", otype);
        List<String> listTypeA = new ArrayList<>();
        listTypeA.add("Phim");
        listTypeA.add("Hóa Đơn");
        List<String> discontinued = new ArrayList<>();
        discontinued.add("Tiếp tục");
        discontinued.add("Không");
        List<String> appO = new ArrayList<>();
        appO.add("Có");
        appO.add("Không");
        request.setAttribute("allF", fd.getAllFood());
        request.setAttribute("appO", appO);
        request.setAttribute("discontinued", discontinued);
        request.setAttribute("listTypeA", listTypeA);
        request.setAttribute("type", evd.getAllEventType());
        request.setAttribute("cin", cnd.getAllCinema());
        request.getRequestDispatcher("addEv.jsp").forward(request, response);
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
        String id_raw = request.getParameter("ID");
        System.out.println(id_raw);
        int id = 0;
        try {
            id = Integer.parseInt(id_raw);

        } catch (Exception e) {
            System.out.println("m");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        String startDate_raw = request.getParameter("startDate");
        String endDate_raw = request.getParameter("endDate");
        String content = request.getParameter("content");
        String dctype_raw = request.getParameter("dctype");
        String dc_raw = request.getParameter("dc");
        String name = request.getParameter("name");
        String fCode = request.getParameter("fCode");
        String applytype = request.getParameter("applytype");
        String mov_raw = request.getParameter("mov");
        String price_raw = request.getParameter("price");
        String cin_raw[] = request.getParameterValues("cin");
        String gN_raw = request.getParameter("gN");
        String img = request.getParameter("img");
        String applyO = request.getParameter("appO");
        String otype_raw = request.getParameter("otype");
        String otype = "";
        double price = 0;
        if (price_raw != null) {
            try {
                price = Double.parseDouble(price_raw);
            } catch (Exception e) {
                System.out.println("a");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        if (otype_raw != null) {
            if (otype_raw.equals("Vé")) {
                otype = "TK";
            } else if (otype_raw.equals("Đồ Ăn")) {
                otype = "FD";
            }
        }
        int date = 0;
        if (request.getParameter("dte") != null) {
            String date_raw = request.getParameter("dte");
            date = Integer.parseInt(date_raw);
        }

        int movID = 0;
        if (mov_raw != null) {
            try {
                movID = Integer.parseInt(mov_raw);
            } catch (Exception e) {
                System.out.println("b");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }

        Timestamp start = null;
        Timestamp end = null;
        try {
            start = Timestamp.valueOf(startDate_raw + " " + "00:00:00");
            end = Timestamp.valueOf(endDate_raw + " " + "00:00:00");
            if (start.compareTo(end) >= 0) {
                throw new Exception("L");
            }
        } catch (Exception e) {
            System.out.println("c");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }

        int appO = 0;
        if (applyO != null) {
            if (applyO.equals("Có")) {
                appO = 1;
            } else {
                appO = 0;
            }
        }
        int dctype = 0;
        if (dctype_raw != null) {
            try {
                dctype = Integer.parseInt(dctype_raw);
            } catch (Exception e) {
                System.out.println("d");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        int discontinued = 0;

        int gN = 0;
        if (gN_raw != null) {
            try {
                gN = Integer.parseInt(gN_raw);
            } catch (Exception e) {
                System.out.println("e");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        int cin[] = new int[gN];
        int check;
        int cnt;
        int m = 0;
        for (int i = 0; i < cin_raw.length; i++) {
            check = 0;
            cnt = 0;
            for (int j = 0; j < cin.length; j++) {
                cnt = Integer.parseInt(cin_raw[i]);
                if (Integer.parseInt(cin_raw[i]) == cin[j]) {
                    check++;
                }
            }
            if (check == 0) {
                cin[m] = cnt;
                m++;
            }
        }
        model.Event e = evd.getEventByCode(id);
        if (e == null) {
            evd.insertEvent(id, name, content, dctype, start, end, appO, img, discontinued, date);
            if (dctype == 1) {
                if (dc_raw != null) {
                    double dc = 0;
                    try {
                        dc = Double.parseDouble(dc_raw);
                    } catch (Exception ep) {
                        System.out.println("f");
                        request.getRequestDispatcher("error.jsp").forward(request, response);
                    }
                    evd.insertEventDiscount(dc, id);
                } else {
                    System.out.println("g");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else if (dctype == 2) {
                if (fCode != null) {
                    Food f = fd.getFoodById(fCode);
                    evd.insertEventGift(id, f.getProductCode());
                } else {
                    System.out.println("h");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }

            if (applytype.equals("Phim")) {
                evd.insertEventMovie(id, movID);

            } else if (applytype.equals("Hóa Đơn")) {

                evd.insertEventOrder(id, price, otype);
            }
            for (int i = 0; i < cin.length; i++) {
                evd.insertCinApplyEvent(id, cin[i]);
            }
            e = evd.getEventByCode(id);

            String dateS = "", monthS = "", yearS = "";
            String dateE = "", monthE = "", yearE = "";
            String t = e.getStartDate().toString();
            cnt = 0;
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

            String typeApply = "";
            if (evd.getEventMov(id) != null) {
                typeApply = "Phim";
                movID = evd.getEventMov(id).getMovID();
                request.setAttribute("movID", movID);

            } else if (evd.getEventOrder(id) != null) {
                typeApply = "Hóa Đơn";
                price = evd.getEventOrder(id).getDiscount();
                String tst = decimalFormat.format(price);
                price = Double.parseDouble(tst);
                request.setAttribute("price", price);
            }

            List<Food> f = new ArrayList<>();
            String pc = "";
            double discount = 0;
            List<String> listTypeA = new ArrayList<>();
            listTypeA.add("Phim");
            listTypeA.add("Hóa Đơn");
            List<String> discontinued1 = new ArrayList<>();
            discontinued1.add("Tiếp tục");
            discontinued1.add("Không");
            List<String> appO1 = new ArrayList<>();
            appO1.add("Có");
            appO1.add("Không");
            request.setAttribute("appO", appO1);
            request.setAttribute("discontinued", discontinued1);
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
            model.Event e2 = evd.getEventOrder(id);
            if (e2 != null) {
                e.setType(e2.getType());
            }
            System.out.println(e.getType());
            List<String> otype1 = new ArrayList<>();
            otype1.add("Vé");
            otype1.add("Đồ Ăn");
            request.setAttribute("maxDate", e.getNumDateEx());
            request.setAttribute("startU", dateS + "-" + monthS + "-" + yearS);
            request.setAttribute("endU", dateE + "-" + monthE + "-" + yearE);
            request.setAttribute("ms", "Sửa thành công");
            request.setAttribute("otype", otype);
            request.setAttribute("type", evd.getAllEventType());
            request.setAttribute("cin", cnd.getAllCinema());
            request.getRequestDispatcher("addEv.jsp").forward(request, response);
        } else {
            request.setAttribute("ms", "Mã ưu đãi đã tồn tại");
            request.getRequestDispatcher("addEv.jsp").forward(request, response);
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
