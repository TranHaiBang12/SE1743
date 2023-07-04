/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDAO;
import dal.PointDAO;
import dal.RateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.TIcketDate;

/**
 *
 * @author acer
 */
public class AccountReport extends HttpServlet {

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
            out.println("<title>Servlet AccountReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountReport at " + request.getContextPath() + "</h1>");
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
        AccountDAO acd = new AccountDAO();
        PointDAO pd = new PointDAO();
        RateDAO rd = new RateDAO();
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        List<Account> list = acd.getAllAcount();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setNumBuy(acd.getNumBuy(list.get(i).getUserName()));
            list.get(i).setNumBuyOnl(acd.getNumBuyOnl(list.get(i).getUserName()));
            list.get(i).setNumBuyOff(acd.getNumBuyOff(list.get(i).getUserName()));
            if (acd.getNumBuy(list.get(i).getUserName()) == 0) {
                list.get(i).setPcOnl("0");
                list.get(i).setPcOff("0");
            } else {
                String onl = decimalFormat.format((double) acd.getNumBuyOnl(list.get(i).getUserName()) / (double) acd.getNumBuy(list.get(i).getUserName()) * 100);
                String off = decimalFormat.format((double) acd.getNumBuyOff(list.get(i).getUserName()) / (double) acd.getNumBuy(list.get(i).getUserName()) * 100);
                list.get(i).setPcOnl(onl);
                list.get(i).setPcOff(off);
            }
            if (pd.getAccountPoint(list.get(i).getUserName()) != null) {
                list.get(i).setPoint(pd.getAccountPoint(list.get(i).getUserName()).getPoint());
            } else {
                list.get(i).setPoint(0);
            }
            list.get(i).setPointUse(pd.getAllAccountUsedPoint(list.get(i).getUserName()));
            list.get(i).setNumRate(rd.getNumRateByAcc(list.get(i).getUserName()));
            if (acd.getNumBuy(list.get(i).getUserName()) == 0) {
                list.get(i).setPcPointUse("0");
            } else {
                String PC = decimalFormat.format((double) pd.getNumAccountUsedPoint(list.get(i).getUserName()) / (double) acd.getNumBuy(list.get(i).getUserName()) * 100);
                list.get(i).setPcPointUse(PC);
            }
        }

        List<TIcketDate> listTID = new ArrayList<>();
        for (int i = 0; i < acd.getCusCity().size(); i++) {
            if (list.size() == 0) {
                listTID.add(new TIcketDate(acd.getCusCity().get(i), acd.getNumCusCity(acd.getCusCity().get(i)), "0"));
            }
            else {
                String PC = decimalFormat.format((double)acd.getNumCusCity(acd.getCusCity().get(i)) / (double)list.size() * 100);
                listTID.add(new TIcketDate(acd.getCusCity().get(i), acd.getNumCusCity(acd.getCusCity().get(i)), PC));
            }
        }
        request.setAttribute("listTID", listTID);
        request.setAttribute("list", list);
        request.getRequestDispatcher("aRp.jsp").forward(request, response);
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
